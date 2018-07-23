package com.manparvesh.udpj;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.SocketException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UDPJServerTest {

    private UDPClient client;

    @Before
    public void setUp() throws SocketException {
        new UDPJServer().start();
        client = new UDPClient();
    }

    @Test
    public void testUDPServerImplementation() throws IOException {
        String sentMessage = "Hello server ji!";
        String echo = client.sendEcho(sentMessage);
        assertEquals(sentMessage, echo);

        echo = client.sendEcho("some other message!");
        assertNotEquals(sentMessage, echo);
    }

    @After
    public void tearDown() throws IOException {
        client.sendEcho("end");
        client.close();
    }
}
