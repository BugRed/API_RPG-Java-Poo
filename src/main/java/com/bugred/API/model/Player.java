package com.bugred.API.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private int id;

    @SerializedName("playerName")
    private String playerName;

    // A anotação é usada para mapear o campo "listCharacters" do JSON para a lista de personagens no Java
    @SerializedName("listCharacters")
    private List<Character> characters;

    // Construtores
    public Player() {
        this.characters = new ArrayList<>();  // Inicializa a lista para evitar problemas com null
    }

    public Player(int id, String playerName, List<Character> characters) {
        this.id = id;
        this.playerName = playerName;
        this.characters = characters != null ? characters : new ArrayList<>();
    }

    public Player(int id, String playerName) {
        this.id = id;
        this.playerName = playerName;
        this.characters = new ArrayList<>();
    }

    // Métodos getter e setter
    public int getId() {
        return id;
    }

    public Player setId(int id) {
        this.id = id;
        return this;
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

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Método toString
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", characters=" + characters +
                '}';
    }
}
