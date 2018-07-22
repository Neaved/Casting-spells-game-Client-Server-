package com.game.server;

import com.game.exceptions.ShowToUserException;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

    private static final int PORT = 4444;
    private static final String START_SERVER_MESSAGE = "Сервер запущен.";
    private static final String CLIENT_ACCEPT_MESSAGE = "Клиент подключен.";

    public static void main(String[] args) {
        new ChatServer().start();
    }

    private void start() {
        List<PrintWriter> outputStreams = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            ExecutorService executor = Executors.newCachedThreadPool();
            System.out.println(START_SERVER_MESSAGE);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(CLIENT_ACCEPT_MESSAGE);
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                outputStreams.add(writer);
                executor.execute(new ClientHandler(socket, outputStreams));
            }
        } catch (Exception ex) {
            new ShowToUserException(ex);
        }
    }
}
