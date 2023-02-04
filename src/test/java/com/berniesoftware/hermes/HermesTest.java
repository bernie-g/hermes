package com.berniesoftware.hermes;

import org.junit.jupiter.api.Test;

class HermesTest {
    @Test
    public void testHermes() throws InterruptedException {
        System.out.println(Hermes.getString("String Test Property"));
        System.out.println(Hermes.getInt("Int Test Property"));
        System.out.println(Hermes.getBool("Bool Test Property"));
        System.out.println(Hermes.getDouble("Double Test Property"));
        Thread.sleep(100000);
    }

}