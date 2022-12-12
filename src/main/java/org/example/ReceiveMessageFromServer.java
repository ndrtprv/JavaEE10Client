package org.example;

import java.io.*;

public class ReceiveMessageFromServer implements Runnable{
    private InputStream inputStreamServer;

    public ReceiveMessageFromServer(InputStream inputStreamServer) {
        this.inputStreamServer = inputStreamServer;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer))){
            String serverMessage;

            while (true) {
                serverMessage = in.readLine();
                if (serverMessage != null) {
                    System.out.print("\n" + serverMessage + "\nEnter the message: ");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }
}