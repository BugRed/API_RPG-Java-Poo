package com.bugred.API.dto;

import java.util.ArrayList;
import java.util.List;

public class PlayerDTO {
    private String playerName;
    private List<CharacterDTO> listCharacter;

    public PlayerDTO() {
        this.listCharacter = new ArrayList<>();
    }

    public PlayerDTO(String playerName) {
        this.playerName = playerName;
        this.listCharacter = new ArrayList<>();
    }

    public String getPlayerName() {
        return playerName;
    }

    public PlayerDTO setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }


    public List<CharacterDTO> getListPerson() {
        return listCharacter;
    }

    public PlayerDTO setListPerson(List<CharacterDTO> listCharacter) {
        this.listCharacter = listCharacter;
        return this;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "playerName='" + playerName + '\'' +
                ", listCharacter=" + listCharacter +
                '}';
    }
}