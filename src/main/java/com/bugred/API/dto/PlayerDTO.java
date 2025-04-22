package com.bugred.API.dto;

import java.util.ArrayList;
import java.util.List;

public class PlayerDTO {

    // Novo campo adicionado para expor o ID do jogador no JSON
    private int id;

    // Nome do jogador
    private String playerName;

    // Lista de personagens associados a esse jogador
    private List<CharacterDTO> listCharacter;

    // Construtor padrão inicializa a lista
    public PlayerDTO() {
        this.listCharacter = new ArrayList<>();
    }

    // Construtor com nome
    public PlayerDTO(String playerName) {
        this.playerName = playerName;
        this.listCharacter = new ArrayList<>();
    }

    public PlayerDTO(int id, String playerName, List<CharacterDTO> characterDTOs) {
        this.id = id;
        this.playerName = playerName;
        this.listCharacter = new ArrayList<>();
    }

    // Getter e Setter para o ID
    public int getId() {
        return id;
    }

    public PlayerDTO setId(int id) {
        this.id = id;
        return this;
    }

    // Getter e Setter para o nome do jogador
    public String getPlayerName() {
        return playerName;
    }

    public PlayerDTO setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    // Getter e Setter antigo com nome errado (mantido por compatibilidade)
    public List<CharacterDTO> getListPerson() {
        return listCharacter;
    }

    public PlayerDTO setListPerson(List<CharacterDTO> listCharacter) {
        this.listCharacter = listCharacter;
        return this;
    }

    // Getter correto para lista de personagens
    public List<CharacterDTO> getListCharacter() {
        return listCharacter;
    }

    // Setter correto para lista de personagens
    public PlayerDTO setListCharacter(List<CharacterDTO> listCharacter) {
        this.listCharacter = listCharacter;
        return this;
    }

    // Representação em string do DTO
    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", listCharacter=" + listCharacter +
                '}';
    }
}
