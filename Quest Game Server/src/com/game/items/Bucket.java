package com.game.items;

import com.game.Room;

import java.util.ArrayList;

import static com.game.Constants.*;

public class Bucket extends Item {

    private boolean bucketWithChain;
    private boolean bucketWithWater;

    public Bucket(String name, String description, boolean isStatic) {
        super(name, description, isStatic);
        this.bucketWithChain = false;
        this.bucketWithWater = false;
    }

    public Integer craft(Item item, Room room) {
        String roomName = room.getName();

        if (item instanceof Well && isBucketWithChain() && GARDEN_LOC.equals(roomName)) {
            bucketWithWater = true;
            setDescription(BUCKET_ITEM_WITH_WATER_DESCRIPTION);
            return FIRST_RESULT_OF_USE_CMD;
        }

        if (item instanceof Well && GARDEN_LOC.equals(roomName)) {
            return SECOND_RESULT_OF_USE_CMD;
        }

        if (item instanceof Wizard && isBucketWithWater() && LIVING_ROOM_LOC.equals(roomName)) {
            awakeWizard(room, (Wizard) item);
            return THIRD_RESULT_OF_USE_CMD;
        } else if (item instanceof Wizard && LIVING_ROOM_LOC.equals(roomName)) {
            return FOURTH_RESULT_OF_USE_CMD;
        }
        return FIFTH_RESULT_OF_USE_CMD;
    }

    private void awakeWizard(Room room, Wizard awakeWizard) {
        ArrayList<Item> roomInventoryItems = room.getRoomInventoryItems();
        roomInventoryItems.remove(awakeWizard);
        awakeWizard.setAwake(true);
        roomInventoryItems.add(awakeWizard);
    }

    public boolean isBucketWithChain() {
        return bucketWithChain;
    }

    public void setBucketWithChain(boolean bucketWithChain) {
        this.bucketWithChain = bucketWithChain;
    }

    public boolean isBucketWithWater() {
        return bucketWithWater;
    }
}
