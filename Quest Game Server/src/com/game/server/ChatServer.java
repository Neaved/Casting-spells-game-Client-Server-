package com.game.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

    private static final int PORT = 4444;

    public static void main(String[] args) {
        new ChatServer().start();
    }

    private void start() {
        List<PrintWriter> outputStreams = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            ExecutorService executor = Executors.newCachedThreadPool();
            System.out.println("Сервер запущен.");
            while (true) {
                Socket socket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                outputStreams.add(writer);
                executor.execute(new ClientHandler(socket, outputStreams));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
