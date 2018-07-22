package com.game.server;

import com.game.Game;
import com.game.exceptions.ShowToUserException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import static com.game.Constants.*;

public class ClientHandler implements Runnable {

    private static final String CLIENT_TYPE = "Client:> ";
    private static final String SERVER_TYPE = "Server:> ";
    private List<PrintWriter> outputStreams;
    private BufferedReader reader;
    private Game game;

    public ClientHandler(Socket socket, List<PrintWriter> outputStreams) {
        this.outputStreams = outputStreams;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception ex) {
            new ShowToUserException(ex);
        }
    }

    @Override
    public void run() {
        String message;
        try {
            sendMessage(START_TYPE_SYMBOL + FIRST_ACTION);
            while ((message = reader.readLine()) != null) {
                if (START_CMD.equals(message)) {
                    startGame();
                    sendMessage(START_TYPE_SYMBOL + START_GAME_DESCRIPTION);
                    while (game.isRunning() && (message = reader.readLine()) != null) {
                        System.out.println(CLIENT_TYPE + message);
                        String result = game.letsPlay(message);
                        System.out.println(SERVER_TYPE + result);
                        sendMessage(START_TYPE_SYMBOL + result);
                    }
                    sendMessage(START_TYPE_SYMBOL + END_GAME_DESCRIPTION);
                } else if (EXIT_CMD.equals(message)) {
                    sendMessage(START_TYPE_SYMBOL + EXIT_GAME_DESCRIPTION);
                } else {
                    sendMessage(START_TYPE_SYMBOL + UNKNOWN_CMD);
                }
            }
        } catch (Exception ex) {
            new ShowToUserException(ex);
        }
    }

    private void startGame() {
        game = new Game();
        game.startGame();
    }

    private synchronized void sendMessage(String message) {
        for (PrintWriter writer : outputStreams) {
            writer.println(message);
            writer.flush();
        }
    }

}
