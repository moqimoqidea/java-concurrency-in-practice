package com.moqi.b.b09.b0904;

import com.moqi.b.b09.b0902.GuiExecutor;
import com.moqi.b.b09.b0907.BackgroundTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ListenerExamples
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ListenerExamples {
    /**
     * 将一个长时间任务绑定到一个可视化组件
     */
    private static final ExecutorService EXEC = Executors.newCachedThreadPool();

    private final JButton colorButton = new JButton("Change color");
    private final Random random = new Random();
    private final JButton computeButton = new JButton("Big computation");
    private final JButton button = new JButton("Do");
    private final JLabel label = new JLabel("idle");
    private final JButton startButton = new JButton("Start");
    private final JButton cancelButton = new JButton("Cancel");
    /**
     * 取消一个长时间任务
     */
    private Future<?> runningTask = null; // thread-confined

    private void backgroundRandom() {
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                colorButton.setBackground(new Color(random.nextInt()));
            }
        });
    }

    private void longRunningTask() {
        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EXEC.execute(new Runnable() {
                    public void run() {
                        /* Do big computation */
                    }
                });
            }
        });
    }

    private void longRunningTaskWithFeedback() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(false);
                label.setText("busy");
                EXEC.execute(new Runnable() {
                    public void run() {
                        try {
                            /* Do big computation */
                        } finally {
                            GuiExecutor.instance().execute(new Runnable() {
                                public void run() {
                                    button.setEnabled(true);
                                    label.setText("idle");
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void taskWithCancellation() {
        /*
         * 通过 BackgroundTask 来执行长时间的并且可取消的任务
         */
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (runningTask != null) {
                    runningTask = EXEC.submit(new Runnable() {
                        public void run() {
                            while (moreWork()) {
                                if (Thread.currentThread().isInterrupted()) {
                                    cleanUpPartialWork();
                                    break;
                                }
                                doSomeWork();
                            }
                        }

                        private boolean moreWork() {
                            return false;
                        }

                        private void cleanUpPartialWork() {
                        }

                        private void doSomeWork() {
                        }

                    });
                }
                ;
            }
        });

        /*
         * 支持用户反馈的长时间任务
         */
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (runningTask != null)
                    runningTask.cancel(true);
            }
        });
    }


    private void runInBackground(final Runnable task) {
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                class CancelListener implements ActionListener {
                    BackgroundTask<?> task;

                    public void actionPerformed(ActionEvent event) {
                        if (task != null)
                            task.cancel(true);
                    }
                }
                final CancelListener listener = new CancelListener();
                listener.task = new BackgroundTask<Void>() {
                    public Void compute() {
                        while (moreWork() && !isCancelled())
                            doSomeWork();
                        return null;
                    }

                    private boolean moreWork() {
                        return false;
                    }

                    private void doSomeWork() {
                    }

                    public void onCompletion(boolean cancelled, String s, Throwable exception) {
                        cancelButton.removeActionListener(listener);
                        label.setText("done");
                    }
                };
                cancelButton.addActionListener(listener);
                EXEC.execute(task);
            }
        });
    }
}
