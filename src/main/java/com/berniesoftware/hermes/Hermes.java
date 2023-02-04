package com.berniesoftware.hermes;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

public class Hermes {
    private static final int WEBSOCKET_PORT = 9005;
    private static final int GUI_PORT = 6590;
    private static boolean init = false;
    private static HermesWebsocketServer server;

    private static HermesCache hermesCache = new HermesCache();

    public static String getString(String key) {
        Hermes.init();
        return hermesCache.get(key, String.class, "Lorem Ipsum");
    }

    public static boolean getBool(String key) {
        Hermes.init();
        return hermesCache.get(key, Boolean.class, false);
    }

    public static int getInt(String key) {
        Hermes.init();
        return hermesCache.get(key, Integer.class, 0);
    }

    public static double getDouble(String key) {
        Hermes.init();
        return hermesCache.get(key, Double.class, 0d);
    }

    public static <E extends Enum> E getEnum(String key, Class<E> enumClass) {
        Hermes.init();
        return hermesCache.get(key, enumClass, enumClass.getEnumConstants()[0]);
    }

    private static void init() {
        if (init) {
            return;
        }

        init = true;
        server = new HermesWebsocketServer(new InetSocketAddress(WEBSOCKET_PORT));
        server.start();
        Javalin app = Javalin.create((config) -> config.staticFiles.add("/public", Location.CLASSPATH));
        //config.server(() -> ServerUtil.createHttp2Server(new QueuedThreadPool(10, 2, 60_000)));
        app.start(GUI_PORT);

        try {
            Desktop.getDesktop().browse(URI.create("http://localhost:" + GUI_PORT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
