package com.bugred.API.service;

import com.bugred.API.model.Character;
import com.bugred.API.model.Player;
import com.bugred.API.repository.PlayerRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// Service é quem vai interagir com o repository para evitar expor codigos
public class PlayerService {

    // Criando uma instancia de service
    private static PlayerService instance;
    // Criando instancia de repository
    private final PlayerRepository repository = new PlayerRepository();


    // Id inicial para player e character
    // Usando AtomicInteger para garantir gerar o ID automatico
    private static AtomicInteger nextPlayerId = new AtomicInteger(1);
    private static AtomicInteger nextCharacterId = new AtomicInteger(1);

    /*
        FUNÇÕES PARA PLAYER
    */

    // Recuperando a instancia ou criando se não houver
    public static PlayerService getInstance() {
        if (instance == null) {
            instance = new PlayerService();
        }
        return instance;
    }

    // Utilizando o repository para executar ações no banco

    public List<Player> findAll() {
        return repository.findAll();
    }

    public Player findById(int id) {
        return repository.findById(id);
    }

    // Criar player
    public Player create(Player player) {
        int id = nextPlayerId.getAndIncrement();
        player.setId(id);
        repository.save(player);
        return player;
    }

    public Player update(int id, Player updatedPlayer) {
        if (!repository.exists(id)) return null;
        updatedPlayer.setId(id);
        repository.save(updatedPlayer);
        return updatedPlayer;
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    /*
       FUNÇÕES PARA CHARACTERS DENTRO DE PLAYER
    */

    // Adicionando um personagem a um player
    public Character addCharacterToPlayer(int playerId, Character character) {
        Player player = repository.findById(playerId);
        if (player != null) {
            int id = nextCharacterId.getAndIncrement();
            character.setId(id);
            player.addCharacter(character);
            repository.save(player);
            return character;
        }
        return null;
    }

    // Achar um character usando um player
    public Character findCharacterInPlayer(int playerId, int characterId) {
        Player player = repository.findById(playerId);
        if (player == null) return null;
        return player.getCharacters().stream()
                .filter(p -> p.getId() == characterId)
                .findFirst()
                .orElse(null);
    }


    // update de character usando player
    public Character updateCharacterInPlayer(int playerId, int characterId, Character updatedCharacter) {
        Player player = repository.findById(playerId);
        if (player == null) return null;

        List<Character> characters = player.getCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getId() == characterId) {
                updatedCharacter.setId(characterId);
                characters.set(i, updatedCharacter);
                repository.save(player);
                return updatedCharacter;
            }
        }
        return null;
    }


    // remover um character de um player
    public boolean removeCharacterFromPlayer(int playerId, int characterId) {
        Player player = repository.findById(playerId);
        if (player == null) return false;

        boolean removed = player.getCharacters().removeIf(p -> p.getId() == characterId);
        if (removed) {
            repository.save(player);
        }
        return removed;
    }
}

