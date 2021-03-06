package com.berniesoftware.hermes;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

class HermesTest {
    @Test
    public void testHermes() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            System.out.println(Hermes.getString("String Test Property"));
            System.out.println(Hermes.getInt("Int Test Property", 1, 100));
            System.out.println(Hermes.getBool("Bool Test Property"));
            System.out.println(Hermes.getDouble("Double Test Property", -5.4, 5.90));
            Thread.sleep(1000);
        }
    }

}