package com.game;

import com.game.items.Bucket;
import com.game.items.Chain;
import com.game.items.Item;

import java.util.ArrayList;

import static com.game.Constants.*;

public class Player {

    private Room room;
    private Inventory playerInventory;

    public Player() {
        playerInventory = new Inventory();
    }

    public String goToAnotherRoomCommand(String direction) {
        Room nextRoom = room.findNextRoom(direction);
        if (nextRoom != null) {
            this.room = nextRoom;
            return room.getDescription();
        } else {
            return INVALID_MOVE_ACTION;
        }
    }

    public String getItemsCommand(String itemName) {
        Item item = room.getRoomItemByName(itemName);
        if (item != null) {
            if (item.isStatic()) {
                return showCanNotTakeActionMessage(item.getName());
            } else {
                return replaceItem(item);
            }
        } else {
            return showNoSubjectActionMessage(itemName);
        }
    }

    private String showCanNotTakeActionMessage(String itemName) {
        return CAN_NOT_TAKE_ACTION + itemName + DOT_SYMBOL;
    }

    private String showNoSubjectActionMessage(String itemName) {
        return NO_SUBJECT_ACTION + COMMAS_SYMBOL + itemName + COMMAS_SYMBOL + DOT_SYMBOL;
    }

    private String replaceItem(Item item) {
        addItemToInventory(item);
        room.removeItemFromRoomInventory(item);
        return YOU_HAVE_ACTION + item.getName() + DOT_SYMBOL;
    }

    public Integer useItemsCommand(String firstItemName, String secondItemName) {
        Item firstItem = getItemByFlag(firstItemName, true);
        Item secondItem = getItemByFlag(secondItemName, false);
        if (firstItem != null && secondItem != null) {
            if (firstItem instanceof Bucket) {
                return ((Bucket) firstItem).craft(secondItem, room);
            } else if (firstItem instanceof Chain) {
                return ((Chain) firstItem).craft(secondItem, room, playerInventory);
            }
        } else {
            return SEVENTH_RESULT_OF_USE_CMD;
        }
        return SEVENTH_RESULT_OF_USE_CMD;
    }

    private Item getItemByFlag(String itemName, boolean isFirstItem) {
        if (AVAILABLE_FIRST_ITEM.contains(itemName) && isFirstItem) {
            return getItemFromInventoryByName(itemName);
        }
        if (AVAILABLE_SECOND_ITEM.contains(itemName) && !isFirstItem) {
            if (BUCKET_ITEM_NAME.equals(itemName)) {
                return getItemFromInventoryByName(itemName);
            }
            return room.getRoomItemByName(itemName);
        }
        return null;
    }

    private Item getItemFromInventoryByName(String itemName) {
        return playerInventory.getItemByName(itemName);
    }

    public String showInventoryCommand() {
        if (playerInventory.isEmpty()) {
            return EMPTY_INVENTORY_STUFF;
        } else {
            return INVENTORY_STUFF + playerInventory.toString();
        }
    }

    public String getDescriptionOfCurrentRoomCommand() {
        return room.getDescription();
    }

    private void addItemToInventory(Item item) {
        playerInventory.addToInventory(item);
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String showItemDescriptionCommand(String itemName) {
        Item item = getItemToShow(itemName);
        if (item != null) {
            return item.getDescription();
        } else {
            return showNoSubjectActionMessage(itemName);
        }
    }

    private Item getItemToShow(String itemName) {
        Item item = getItemFromInventoryByName(itemName);
        return item == null ? getRoomInventoryByName(itemName) : item;
    }

    private Item getRoomInventoryByName(String itemName) {
        return room.getRoomItemByName(itemName);
    }
}
