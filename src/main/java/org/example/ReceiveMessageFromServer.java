package org.example;

import java.io.*;

public class ReceiveMessageFromServer implements Runnable{
    private final InputStream inputStreamServer;

    public ReceiveMessageFromServer(InputStream inputStreamServer) {
        this.inputStreamServer = inputStreamServer;
    }

    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer));
        String serverMessage = null;

        while (!"exit".equalsIgnoreCase(serverMessage)) {
            try {
                serverMessage = in.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (serverMessage != null) {
                System.out.print("\n" + serverMessage + "\nEnter the message: ");
            }
        }

    }
}