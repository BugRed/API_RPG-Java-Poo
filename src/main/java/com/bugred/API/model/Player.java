package com.bugred.API.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Player extends User{

    @SerializedName("playerName")
    private String playerName;

    // todo Adicionar nova caracteristica de ROLE: NormalPlayer or DungeonMaster

    // A anotação é usada para mapear o campo "listCharacters" do JSON para a lista de personagens no Java
    @SerializedName("listCharacters")
    private List<Character> characters;

    // Construtores
    public Player() {
        super();
        this.characters = new ArrayList<>();  // Inicializa a lista para evitar problemas com null
    }

    public Player(String playerName) {
        super();
        this.playerName = playerName;
    }


    public Player(UUID id, String playerName, List<Character> characters) {
        super(id);
        this.playerName = playerName;
        this.characters = characters != null ? characters : new ArrayList<>();
    }

    public Player(UUID id, String playerName) {
        super(id);
        this.characters = new ArrayList<>();
    }

    public Player(String playerName, List<Character> chars) {
        super();
        this.playerName = playerName;
        this.characters = chars != null ? chars : new ArrayList<>();
    }


    public String getPlayerName() {
        return playerName;
    }

    public Player setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    // Adiciona um único personagem à lista
    public Player addCharacter(Character character) {
        if (this.characters == null) {
            this.characters = new ArrayList<>();
        }
        this.characters.add(character);
        return this;
    }

    // Adiciona uma lista de personagens
    public Player addAllCharacters(List<Character> charactersToAdd) {
        if (this.characters == null) {
            this.characters = new ArrayList<>();
        }
        this.characters.addAll(charactersToAdd);
        return this;
    }

    // Substitui a lista de personagens com uma nova lista
    public Player setCharacters(List<Character> characters) {
        this.characters = new ArrayList<>(characters);
        return this;
    }

    // Método toString
    @Override
    public String toString() {
        return "Player{" +
                "id=" + getId() +
                ", playerName='" + playerName + '\'' +
                ", characters=" + characters +
                '}';
    }
}
