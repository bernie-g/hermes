package com.berniesoftware.hermes;

import com.berniesoftware.hermes.container.HermesContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HermesCache {
    private Map<String, HermesContainer<?>> map = new ConcurrentHashMap<>();

    public <T> T get(String key, Class<T> clazz, T defaultValue) {
        HermesContainer<?> container = map.computeIfAbsent(key, k -> new HermesContainer<T>(clazz, defaultValue));

        Object value = container.getValue();
        if (value.getClass() != clazz) {
            throw new IllegalStateException("Hermes container type was not correct, something is wrong");
        }
        return (T) value;
    }


    <T> void update(String key, Class<T> clazz, T value) {
        HermesContainer<T> hermesContainer = (HermesContainer<T>) map.get(key);
        if (hermesContainer.getClazz() == clazz) {
            hermesContainer.setValue(value);
        }
    }

    @Override
    protected Object clone() {
        HermesCache hermesCache = new HermesCache();
        hermesCache.map = new HashMap<>(map);
        return hermesCache;
    }
}
