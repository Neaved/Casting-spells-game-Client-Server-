package com.game.server;

import com.game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import static com.game.Constants.*;

public class ClientHandler implements Runnable {

    private List<PrintWriter> outputStreams;
    private BufferedReader reader;
    private Game game;

    public ClientHandler(Socket socket, List<PrintWriter> outputStreams) {
        this.outputStreams = outputStreams;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override public void run() {
        String message;
        try {
            sendMessage(FIRST_ACTION);
            while ((message = reader.readLine()) != null) {
                if (START.equals(message)) {
                    game = new Game();
                    game.startGame();
                    sendMessage(START_GAME_DESCRIPTION);
                    while (game.isRunning() && (message = reader.readLine()) != null) {
                        System.out.println("Client:> " + message);
                        String result = game.letsPlay(message);
                        System.out.println("Server:> " + result);
                        sendMessage(result);
                    }
                    sendMessage(END_GAME_DESCRIPTION);
                } else if (EXIT.equals(message)) {
                    sendMessage(EXIT_GAME_DESCRIPTION);
                } else {
                    sendMessage(UNKNOWN_CMD);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private synchronized void sendMessage(String message) {
        for (PrintWriter writer : outputStreams) {
            writer.println(message);
            writer.flush();
        }
    }

}
