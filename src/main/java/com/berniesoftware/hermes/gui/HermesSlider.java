package com.berniesoftware.hermes.gui;

import io.github.palexdev.materialfx.controls.MFXSlider;
import lombok.Getter;

public class HermesSlider extends MFXSlider {
    @Getter
    private final String name;

    public HermesSlider(String name) {
        this.name = name;
    }

    public HermesSlider(String name, double initialValue) {
        super(initialValue);
        this.name = name;
    }

    public HermesSlider(String name, double min, double max, double initialValue) {
        super(min, max, initialValue);
        this.name = name;
    }

}
