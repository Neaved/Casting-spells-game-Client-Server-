package com.game.items;

import com.game.Inventory;
import com.game.Room;

import java.util.ArrayList;

import static com.game.Constants.*;

public class Chain extends Item {

    public Chain(String name, String description, boolean isStatic) {
        super(name, description, isStatic);
    }

    public Integer craft(Item item, Room room, Inventory inventory) {
        String roomName = room.getName();
        if (item instanceof Bucket
                && ATTIC_LOC.equals(roomName)) {

            ArrayList<Item> playerInventory = inventory.getInventory();
            Chain chain = null;
            for (Item playerItem : playerInventory) {
                if (playerItem.getName().equals(CHAIN_ITEM_NAME)) {
                    chain = (Chain) playerItem;
                }
            }
            if (chain != null) {
                playerInventory.remove(chain);
            }
            Bucket newBucket = (Bucket) item;
            playerInventory.remove(item);
            newBucket.setBucketWithChain(true);
            playerInventory.add(newBucket);
            return 6;
        }
    return 7;
    }


/*
    public void craft(Item item, Room room, Inventory playerInventory) {
        if (item instanceof Bucket && ATTIC_LOC.equals(room.getName())) {
            ArrayList<Item> playerInventoryItems = playerInventory.getInventoryItems();
            playerInventoryItems.remove(getChainFromPlayerInventory(playerInventoryItems));
            ((Bucket) item).setBucketWithChain(true);
            item.setDescription(BUCKET_ITEM_WITH_CHAIN_DESCRIPTION);
            playerInventoryItems.add(item);
            System.out.println(BUCKET_ITEM_WITH_CHAIN_ACTION_DESCRIPTION);
        }
    }

    private Chain getChainFromPlayerInventory(ArrayList<Item> playerInventoryItems) {
        return (Chain) playerInventoryItems
                .stream()
                .filter(playerItem -> CHAIN_ITEM_NAME.equals(playerItem.getName()))
                .findFirst().get();
    }
*/
}
