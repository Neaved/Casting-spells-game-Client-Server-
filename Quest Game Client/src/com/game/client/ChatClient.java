package com.game.client;

import com.game.exceptions.ShowToUserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 4444;
    private static final String CONNECT_MESSAGE = "Соединение установлено.";

    public static void main(String[] args) {
        new ChatClient().start();
    }

    private void start() {
        try {
            Socket socket = new Socket(HOST, PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new MessageReader(socket, reader));
            executor.shutdown();

            System.out.println(CONNECT_MESSAGE);

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

            while (!socket.isClosed()) {
                String message = inputReader.readLine();
                writer.println(message);
                writer.flush();
            }

        } catch (Exception ex) {
            new ShowToUserException(ex);
        }
    }

}
