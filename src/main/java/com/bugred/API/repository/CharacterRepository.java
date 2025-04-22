package com.bugred.API.repository;

import com.bugred.API.model.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterRepository {

    // Criando a instancia que será usada
    // Todos o Repository vai funcionar como um banco de dados usando MAP para executar funções
    private final Map<Integer, Character> characterStorage = new HashMap<>();


    // Funções basicas de CRUD
    public List<Character> findAll() {
        return new ArrayList<>(characterStorage.values());
    }

    public Character findById(int id) {
        return characterStorage.get(id);
    }

    public void save(Character character) {
        characterStorage.put(character.getId(), character);
    }

    public boolean delete(int id) {
        return characterStorage.remove(id) != null;
    }

    public boolean exists(int id) {
        return characterStorage.containsKey(id);
    }

    public Map<Integer, Character> getStorage() {
        return characterStorage;
    }
}
