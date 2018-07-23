package com.manparvesh.udpj;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buffer;

    public UDPClient() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            // todo handle this exception
            e.printStackTrace();
        }
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            // todo handle this exception
            e.printStackTrace();
        }
    }

    // todo do something about the exception
    public String sendEcho(String message) throws IOException {
        buffer = message.getBytes();
        // todo add option to change port
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4445);

        socket.send(packet);

        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        return new String(packet.getData(), 0, packet.getLength());
    }

    public void close() {
        socket.close();
    }
}
