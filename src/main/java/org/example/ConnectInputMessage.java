package org.example;

import java.io.*;
import java.net.Socket;

public class ConnectInputMessage implements Runnable{
    private Socket serverConnect;
    private InputStream inputStreamServer;

    public ConnectInputMessage() {
        try {
            serverConnect = new Socket("localhost",8887);
            inputStreamServer = serverConnect.getInputStream();
        } catch (IOException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public InputStream getInputStreamServer() {
        return inputStreamServer;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer))){
            String serverMessage;

            while (true) {
                serverMessage = in.readLine();
                if (serverMessage != null) {
                    System.out.println(serverMessage);
                    break;
                }
            }

            PrintWriter out;
            BufferedReader inUser = new BufferedReader(new InputStreamReader(System.in));
            String userMessage;

            while (true) {
                System.out.print("Enter the message: ");
                userMessage = inUser.readLine();
                out = new PrintWriter(serverConnect.getOutputStream(), true);
                out.println(userMessage);
                if ("exit".equalsIgnoreCase(userMessage)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }
}