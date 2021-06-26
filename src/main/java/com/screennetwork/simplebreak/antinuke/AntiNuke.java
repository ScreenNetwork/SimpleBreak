package com.screennetwork.simplebreak.antinuke;

import com.screennetwork.simplebreak.utils.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AntiNuke {

    private final Map<UUID, Tuple<Long, Integer>> tupleMap = new HashMap<>();
    private final Map<UUID, Long> reportMap = new HashMap<>();

    public void add(UUID id) {
        final Tuple<Long, Integer> tuple = tupleMap.get(id);

        final long currentTimeMillis = System.currentTimeMillis();
        if (tuple == null || tuple.getKey() + 10000L < currentTimeMillis) tupleMap.put(id, new Tuple<>(currentTimeMillis, 1));
        else tuple.setValue(tuple.getValue() + 1);

    }

    public int get(UUID id) {
        final Tuple<Long, Integer> tuple = tupleMap.get(id);
        if (tuple == null) return 0;

        return tuple.getValue();
    }

    public void clear(UUID id) {
        reportMap.remove(id);
    }

    public void report(UUID id) {
        reportMap.put(id, System.currentTimeMillis());
    }

    public long warns(UUID id) {
        return reportMap.getOrDefault(id, 0L);
    }
}