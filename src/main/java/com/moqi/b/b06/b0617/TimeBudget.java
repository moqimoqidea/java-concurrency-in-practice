package com.moqi.b.b06.b0617;

import java.util.*;
import java.util.concurrent.*;

interface TravelCompany {
    TravelQuote solicitQuote(TravelInfo travelInfo) throws Exception;
}

interface TravelQuote {
}

interface TravelInfo {
}

/**
 * QuoteTask
 * <p/>
 * Requesting travel quotes under a time budget
 * 在预定时间内请求旅游报价
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TimeBudget {
    private static final ExecutorService EXEC = Executors.newCachedThreadPool();

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies,
                                                   Comparator<TravelQuote> ranking, long time, TimeUnit unit)
            throws InterruptedException {
        List<QuoteTask> tasks = new ArrayList<QuoteTask>();
        for (TravelCompany company : companies)
            tasks.add(new QuoteTask(company, travelInfo));

        List<Future<TravelQuote>> futures = EXEC.invokeAll(tasks, time, unit);

        List<TravelQuote> quotes =
                new ArrayList<TravelQuote>(tasks.size());
        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> f : futures) {
            QuoteTask task = taskIter.next();
            try {
                quotes.add(f.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e) {
                quotes.add(task.getTimeoutQuote(e));
            }
        }

        quotes.sort(ranking);
        return quotes;
    }

}

class QuoteTask implements Callable<TravelQuote> {
    private final TravelCompany company;
    private final TravelInfo travelInfo;

    public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
        this.company = company;
        this.travelInfo = travelInfo;
    }

    TravelQuote getFailureQuote(Throwable t) {
        return null;
    }

    TravelQuote getTimeoutQuote(CancellationException e) {
        return null;
    }

    public TravelQuote call() throws Exception {
        return company.solicitQuote(travelInfo);
    }
}
