package com.game.client;

import com.game.exceptions.ShowToUserException;

import java.io.BufferedReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class MessageReader implements Runnable {

    private static final String END_GAME_DESCRIPTION = "Игра окончена. Нажмите Ввод, чтобы выйти.";
    private static final String EXIT_GAME_DESCRIPTION = "Нажмите Ввод, чтобы выйти.";
    private static final List<String> EXIT_GAME_MESSAGE = Arrays.asList(
            END_GAME_DESCRIPTION,
            EXIT_GAME_DESCRIPTION
    );

    private final BufferedReader reader;
    private Socket socket;

    public MessageReader(Socket socket, BufferedReader reader) {
        this.reader = reader;
        this.socket = socket;
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
                if (EXIT_GAME_MESSAGE.contains(message)) {
                    socket.close();
                    break;
                }
            }
        } catch (Exception ex) {
            new ShowToUserException(ex);
        }
    }
}
