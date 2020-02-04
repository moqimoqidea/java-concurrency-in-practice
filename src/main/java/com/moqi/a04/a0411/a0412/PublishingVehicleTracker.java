package com.moqi.a04.a0411.a0412;

import java.util.*;
import java.util.concurrent.*;

import com.moqi.a04.a0411.SafePoint;
import net.jcip.annotations.*;

/**
 * PublishingVehicleTracker
 * <p/>
 * Vehicle tracker that safely publishes underlying state
 * 安全发布底层状态的车辆追踪器
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id)) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
        locations.get(id).set(x, y);
    }
}
