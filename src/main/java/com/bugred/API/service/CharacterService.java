package com.bugred.API.service;

import com.bugred.API.model.Character;
import com.bugred.API.model.Character;
import com.bugred.API.repository.CharacterRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// Service é quem vai interagir com o repository para evitar expor codigos
public class CharacterService {

    // Criando uma instancia de service
    private static CharacterService instance;
    // Criando instancia de repository
    private final CharacterRepository repository = new CharacterRepository();


    // Id inicial para character
    private static AtomicInteger nextCharacterId = new AtomicInteger(1);


    /*
        FUNÇÕES PARA CHARACTER
    */

    // Recuperando a instancia ou criando se não houver
    public static CharacterService getInstance() {
        if (instance == null) {
            instance = new CharacterService();
        }
        return instance;
    }

    // Utilizando o repository para executar ações no banco

    public List<Character> findAll() {
        return repository.findAll();
    }

    public Character findById(int id) {
        return repository.findById(id);
    }

    // Criar character
    public Character create(Character character) {
        int id = nextCharacterId.getAndIncrement();
        character.setId(id);
        repository.save(character);
        return character;
    }

    public Character update(int id, Character updatedCharacter) {
        if (!repository.exists(id)) return null;
        updatedCharacter.setId(id);
        repository.save(updatedCharacter);
        return updatedCharacter;
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

}

