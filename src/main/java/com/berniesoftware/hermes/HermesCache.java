package com.berniesoftware.hermes;

import com.berniesoftware.hermes.container.HermesContainer;
import com.berniesoftware.hermes.container.HermesNumberContainer;
import javafx.application.Platform;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HermesCache {
    private Map<String, HermesContainer<?>> map = new ConcurrentHashMap<>();

    public <T> T get(String key, Class<T> clazz, T defaultValue) {
        HermesContainer<T> value = (HermesContainer<T>) map
                .putIfAbsent(key, new HermesContainer<>(clazz, defaultValue));
        if (value == null) {
            Platform.runLater(() -> Hermes.getHermesGUI().add(key, map.get(key)));
        }
        return value == null ? defaultValue : value.getValue();
    }

    public <T extends Number> T get(String key, Class<T> clazz, T defaultValue, T min, T max) {
        HermesNumberContainer<T> value = (HermesNumberContainer<T>) map
                .putIfAbsent(key, new HermesNumberContainer<>(clazz, defaultValue, min, max));
        if (value == null) {
            Platform.runLater(() -> Hermes.getHermesGUI().add(key, map.get(key)));
        }
        return value == null ? defaultValue : value.getValue();
    }

    <T> void update(String key, Class<T> clazz, T value){
        HermesContainer<T> hermesContainer = (HermesContainer<T>) map.get(key);
        if(hermesContainer.getClazz() == clazz){
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
