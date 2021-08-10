package com.berniesoftware.hermes.container;

import lombok.Getter;
import lombok.Setter;

public class HermesContainer<T> {
    @Getter
    private final Class<T> clazz;
    @Getter
    private final T defaultValue;
    @Getter
    @Setter
    private T value;

    public HermesContainer(Class<T> clazz, T defaultValue) {
        this.clazz = clazz;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }
}
