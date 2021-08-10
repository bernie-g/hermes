package com.berniesoftware.hermes.container;

import lombok.Getter;

@Getter
public class HermesNumberContainer<T extends Number> extends HermesContainer<T> {
    private final T min;
    private final T max;

    public HermesNumberContainer(Class<T> clazz, T defaultValue, T min, T max) {
        super(clazz, defaultValue);
        this.min = min;
        this.max = max;
    }
}
