package com.game.items;

import com.game.Inventory;
import com.game.Room;

import java.util.ArrayList;

import static com.game.Constants.*;

public class Chain extends Item {

    public Chain(String name, String description, boolean isStatic) {
        super(name, description, isStatic);
    }

    public Integer craft(Item item, Room room, Inventory playerInventory) {
        String roomName = room.getName();
        if (item instanceof Bucket && ATTIC_LOC.equals(roomName)) {
            ArrayList<Item> playerInventoryItems = playerInventory.getInventoryItems();
            playerInventoryItems.remove(getChainFromPlayerInventory(playerInventoryItems));
            ((Bucket) item).setBucketWithChain(true);
            item.setDescription(BUCKET_ITEM_WITH_CHAIN_DESCRIPTION);
            playerInventoryItems.add(item);
            return SIXTH_RESULT_OF_USE_CMD;
        }
        return SEVENTH_RESULT_OF_USE_CMD;
    }

    private Chain getChainFromPlayerInventory(ArrayList<Item> playerInventoryItems) {
        return (Chain) playerInventoryItems
                .stream()
                .filter(playerItem -> CHAIN_ITEM_NAME.equals(playerItem.getName()))
                .findFirst().orElse(null);
    }
}
