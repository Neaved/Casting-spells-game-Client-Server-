package com.game.exceptions;

import java.net.SocketException;

public class ShowToUserException {

    private static final String DOT_SYMBOL = ".";
    private static final String ERROR_OCCURRED = "Ошибка: ";
    private static final String SOCKET_EXCEPTION_MESSAGE = "Соединение с сервером разорвано.";

    public ShowToUserException(Exception exception) {
        if (exception instanceof SocketException) {
            printSocketException();
        } else {
            printException(exception);
        }
    }

    private void printSocketException() {
        System.out.println(ERROR_OCCURRED + SOCKET_EXCEPTION_MESSAGE);
    }

    private void printException(Exception exception) {
        System.out.println(ERROR_OCCURRED + exception.getMessage() + DOT_SYMBOL);
    }
}
