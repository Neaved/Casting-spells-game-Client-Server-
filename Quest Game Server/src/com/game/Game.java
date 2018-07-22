package com.game;

import com.game.items.*;

import java.util.HashMap;
import java.util.Map;

import static com.game.Constants.*;

public class Game {

    private Map<String, Room> rooms;

    private boolean isRunning = true;
    private Player player;

    public void startGame() {
        createWorld();
        createPlayer();
    }

    private void createPlayer() {
        player = new Player();
        player.setRoom(rooms.get(LIVING_ROOM_LOC));
    }

    private void createWorld() {
        Room livingRoom = new Room(LIVING_ROOM_LOC, DEFAULT_LIVING_ROOM_DESCRIPTION);
        addItemToLivingRoom(livingRoom);
        Room attic = new Room(ATTIC_LOC, DEFAULT_ATTIC_DESCRIPTION);
        addItemToAttic(attic);
        Room garden = new Room(GARDEN_LOC, DEFAULT_GARDEN_DESCRIPTION);
        addItemToGarden(garden);
        addPathsToRooms(livingRoom, attic, garden);
    }

    private void addItemToLivingRoom(Room livingRoom) {
        livingRoom.addItemToRoomInventory(new Item(WHISKY_ITEM_NAME, WHISKY_ITEM_DESCRIPTION, false));
        livingRoom.addItemToRoomInventory(new Wizard(WIZARD_ITEM_NAME, WIZARD_ITEM_DESCRIPTION, true));
        livingRoom.addItemToRoomInventory(new Bucket(BUCKET_ITEM_NAME, BUCKET_ITEM_DESCRIPTION, false));
    }

    private void addItemToAttic(Room attic) {
        attic.addItemToRoomInventory(new Item(BURNER_ITEM_NAME, BURNER_ITEM_DESCRIPTION, true));
    }

    private void addItemToGarden(Room garden) {
        garden.addItemToRoomInventory(new Item(FROG_ITEM_NAME, FROG_ITEM_DESCRIPTION, false));
        garden.addItemToRoomInventory(new Well(WELL_ITEM_NAME, WELL_ITEM_DESCRIPTION, true));
        garden.addItemToRoomInventory(new Chain(CHAIN_ITEM_NAME, CHAIN_ITEM_DESCRIPTION, false));
    }

    private void addPathsToRooms(Room livingRoom, Room attic, Room garden) {
        livingRoom.addPaths(WEST_DIRECTION, garden);
        livingRoom.addPaths(UPWARD_DIRECTION, attic);
        attic.addPaths(DOWNWARD_DIRECTION, livingRoom);
        garden.addPaths(EASTERN_DIRECTION, livingRoom);

        addRooms(livingRoom, attic, garden);
    }

    private void addRooms(Room livingRoom, Room attic, Room garden) {
        rooms = new HashMap<>(3);
        rooms.put(livingRoom.getName(), livingRoom);
        rooms.put(attic.getName(), attic);
        rooms.put(garden.getName(), garden);
    }

    public String letsPlay(String message) {
        String[] commands = message.split(SPACE_SYMBOL);
        if (commands.length < 4) {
            String command = commands[0];
            switch (command) {
                case GO_CMD:
                    return player.goToAnotherRoomCommand(commands[1]);
                case GET_CMD:
                    return player.getItemsCommand(commands[1]);
                case USE_CMD:
                    Integer intResult = player.useItemsCommand(commands[1], commands[2]);
                    isEndGame(THIRD_RESULT_OF_USE_CMD.equals(intResult));
                    return RESULT_OF_USE_CMD_MAPPING.get(intResult);
                case INVENTORY_CMD:
                    return player.showInventoryCommand();
                case LOOK_CMD:
                    return player.getDescriptionOfCurrentRoomCommand();
                case DESCR_CMD:
                    return player.showItemDescriptionCommand(commands[1]);
                case EXIT_CMD:
                    isEndGame(true);
                    return GAME_OVER_DESCRIPTION;
            }
        }
        return UNKNOWN_CMD;
    }

    public boolean isRunning() {
        return isRunning;
    }

    private void isEndGame(boolean endGame) {
        if (endGame) {
            isRunning = false;
        }
    }
}
