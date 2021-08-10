package com.berniesoftware.hermes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HermesCache {
    private Map<String, Integer> intMap = new ConcurrentHashMap<>();
    private Map<String, Float> floatMap = new ConcurrentHashMap<>();
    private Map<String, Double> doubleMap = new ConcurrentHashMap<>();
    private Map<String, Boolean> booleanMap = new ConcurrentHashMap<>();
    private Map<String, String> stringMap = new ConcurrentHashMap<>();

    public int getInt(String key, int defaultValue) {
        return insertAndReturn(key, defaultValue, intMap);
    }

    public float getFloat(String key, float defaultValue) {
        return insertAndReturn(key, defaultValue, floatMap);
    }

    public double getDouble(String key, double defaultValue) {
        return insertAndReturn(key, defaultValue, doubleMap);
    }

    public boolean getBool(String key, boolean defaultValue) {
        return insertAndReturn(key, defaultValue, booleanMap);
    }

    public String getString(String key, String defaultValue) {
        return insertAndReturn(key, defaultValue, stringMap);
    }

    private <T> T insertAndReturn(String key, T defaultValue, Map<String, T> map) {
        T value = map.putIfAbsent(key, defaultValue);
        return value == null ? defaultValue : value;
    }

    @Override
    protected Object clone() {
        HermesCache hermesCache = new HermesCache();
        hermesCache.intMap = new HashMap<>(intMap);
        hermesCache.floatMap = new HashMap<>(floatMap);
        hermesCache.doubleMap = new HashMap<>(doubleMap);
        hermesCache.booleanMap = new HashMap<>(booleanMap);
        hermesCache.stringMap = new HashMap<>(stringMap);
        return hermesCache;
    }
}
