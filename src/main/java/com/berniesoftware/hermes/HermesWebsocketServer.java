package com.berniesoftware.hermes;

import com.berniesoftware.hermes.container.HermesContainer;
import com.google.gson.JsonObject;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class HermesWebsocketServer extends WebSocketServer {

    public HermesWebsocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("Hermes UI connected");
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean b) {
        System.out.println("Hermes UI disconnected with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        throw new RuntimeException(e);
    }

    @Override
    public void onStart() {
    }

    public <T> void addNewComponent(String key, HermesContainer<T> container) {
        Class<T> clazz = container.getClazz();
        String type = "unknown";

        if (clazz == String.class) {
            type = "string";
        } else if (clazz == Integer.class) {
            type = "int";
        } else if (clazz == Float.class || clazz == Double.class) {
            type = "decimal";
        } else if (clazz == Boolean.class) {
            type = "boolean";
        } else if (clazz.isEnum()) {
            type = "enum";
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key", key);
        jsonObject.addProperty("type", type);
        jsonObject.addProperty("default", String.valueOf(container.getValue()));
        this.broadcast(jsonObject.toString());
    }
}
