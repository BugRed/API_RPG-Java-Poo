package com.bugred.API.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private int id;
    private String playerName;


    // Usar essa anotação para converter o nome do campo no JSON para o nome correto
    // Precisa usar exatamente o mesmo nome do campo que está no JSON
    // Isso é necessário para que o Gson consiga fazer a conversão corretamente
    @SerializedName("listCharacters")
    private List<Character> characters;

    public Player() {
    }

    public Player(int id, String playerName, List<Character> characters) {
        this.id = id;
        this.playerName = playerName;
        // caso não mandem a lista de characteragens inicializa uma nova lista
        // Evitando erro de null pointer e podendo utilizar o construtor de formas diferentes
        this.characters = characters != null ? characters : new ArrayList<>();
    }


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


    // Adicionar um único personagem a lista
    public Player addCharacter(Character character) {
        // checando se existe lista
        if (this.characters == null) {
            this.characters = new ArrayList<>();
        }
        this.characters.add(character);
        return this;
    }

    // Adicionando uma lista de personagens a lista
    public Player addAllPersons(List<Character> charactersToAdd) {
        if (this.characters == null) {
            this.characters = new ArrayList<>();
        }
        this.characters.addAll(charactersToAdd);
        return this;
    }

    // Setando uma nova lista sempre que o metodo for chamado
    public Player setPersons(List<Character> characters) {
        this.characters = new ArrayList<>(characters);
        return this;
    }


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


    // todo fazer melhor aqui
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", characters=" + characters +
                '}';
    }
}
