package com.bugred.API.model;

import java.util.List;
import java.util.UUID;

public class DungeonMaster extends User{

    private List<Character> characters;
    private List<Room> rooms;

    public DungeonMaster(UUID id, String username, String email) {
        super(id, username, email);
    }

    public DungeonMaster() {
    }

    public DungeonMaster(UUID id) {
        super(id);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "DungeonMaster{" +
                "characters=" + characters +
                ", rooms=" + rooms +
                '}';
    }
}
