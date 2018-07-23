package com.manparvesh.udpj;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPJServer extends Thread {
    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];

    public UDPJServer() throws SocketException {
        // todo add application properties file or take from running command
        datagramSocket = new DatagramSocket(4445);
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try {
                datagramSocket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress inetAddress = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);

            String responseString = new String(packet.getData(), 0, packet.getLength());

            if ("end".equals(responseString)) {
                isRunning = false;
                continue;
            }

            try {
                datagramSocket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        datagramSocket.close();
    }
}
