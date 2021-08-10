package com.berniesoftware.hermes;

import javafx.application.Application;
import javafx.application.Platform;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class Hermes {
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private static HermesGUI hermesGUI;
    private static boolean initializedGUI = false;
    static HermesCache hermesCache = new HermesCache();

    public static String getString(String key) {
        updateHermesGUI();
        return getString(key, "Lorem Ipsum");
    }

    public static String getString(String key, String defaultValue) {
        updateHermesGUI();
        return hermesCache.get(key, String.class, defaultValue);
    }

    public static boolean getBool(String key) {
        updateHermesGUI();
        return getBool(key, false);
    }

    public static boolean getBool(String key, boolean defaultValue) {
        updateHermesGUI();
        return hermesCache.get(key, boolean.class, defaultValue);
    }

    public static int getInt(String key, int min, int max) {
        updateHermesGUI();
        return hermesCache.get(key, int.class, (min + max) / 2, min, max);
    }

    public static double getDouble(String key, double min, double max) {
        updateHermesGUI();
        return hermesCache.get(key, double.class, (min + max) / 2, min, max);
    }

    @SneakyThrows
    public static void updateHermesGUI() {
        if (!initializedGUI) {
            new Thread(() -> Application.launch(HermesGUI.class)).start();
            Platform.startup(() -> { });
            while(Hermes.getHermesGUI() == null){
                Thread.sleep(1);
            }
            initializedGUI = true;
        }
    }
}
