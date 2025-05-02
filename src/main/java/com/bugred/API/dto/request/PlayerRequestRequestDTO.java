package com.bugred.API.dto.request;

import java.util.ArrayList;
import java.util.List;

public class PlayerRequestRequestDTO extends UserRequestDTO {

    private String playerName;

    private List<CharacterRequestDTO> listCharacter;

    public PlayerRequestRequestDTO() {
        this.listCharacter = new ArrayList<>();
    }

    public PlayerRequestRequestDTO(String playerName) {
        this.playerName = playerName;
        this.listCharacter = new ArrayList<>();
    }

    public PlayerRequestRequestDTO(String playerName, List<CharacterRequestDTO> characterRequestDTOS) {
        this.playerName = playerName;
        this.listCharacter = characterRequestDTOS;
    }

    // Getter e Setter para o nome do jogador
    public String getPlayerName() {
        return playerName;
    }

    public PlayerRequestRequestDTO setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    // Getter e Setter antigo com nome errado (mantido por compatibilidade)
    public List<CharacterRequestDTO> getListPerson() {
        return listCharacter;
    }

    public PlayerRequestRequestDTO setListPerson(List<CharacterRequestDTO> listCharacter) {
        this.listCharacter = listCharacter;
        return this;
    }

    // Getter correto para lista de personagens
    public List<CharacterRequestDTO> getListCharacter() {
        return listCharacter;
    }

    // Setter correto para lista de personagens
    public PlayerRequestRequestDTO setListCharacter(List<CharacterRequestDTO> listCharacter) {
        this.listCharacter = listCharacter;
        return this;
    }

    // Representação em string do DTO
    @Override
    public String toString() {
        return "PlayerDTO{" +
                ", playerName='" + playerName + '\'' +
                ", listCharacter=" + listCharacter +
                '}';
    }
}
