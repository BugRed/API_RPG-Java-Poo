package com.bugred.API.controller;

import com.bugred.API.dto.CharacterDTO;
import com.bugred.API.dto.PlayerDTO;
import com.bugred.API.model.Character;
import com.bugred.API.model.Player;
import com.bugred.API.service.PlayerService;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.List;

public class PlayerController {

    private final PlayerService service = PlayerService.getInstance();
    private final Gson gson = new Gson();

    /*
        FUNÇÕES DE PLAYER
    */

    public String getAllPlayers() {
        List<Player> players = service.findAll();
        return gson.toJson(players);
    }

    public String getPlayerById(int id) {
        Player player = service.findById(id);
        if (player == null) {
            return "{\"error\": \"Jogador não encontrado\"}";
        }
        return gson.toJson(player);
    }

    // Usando InputStreamReader posso ler dados brutos no json
    public String createPlayer(InputStreamReader reader) {
        PlayerDTO dto = gson.fromJson(reader, PlayerDTO.class);
        Player player = new Player(0, dto.getPlayerName()); // ID será sobrescrito no service
        System.out.println(dto.getPlayerName());
        Player created = service.create(player);
        return gson.toJson(created);
    }

    public String updatePlayer(int id, InputStreamReader reader) {
        PlayerDTO dto = gson.fromJson(reader, PlayerDTO.class);
        Player player = new Player(0, dto.getPlayerName());
        Player updated = service.update(id, player);

        if (updated == null) {
            return "{\"error\": \"Jogador não encontrado para atualização\"}";
        }

        return gson.toJson(updated);
    }

    public String deletePlayer(int id) {
        boolean deleted = service.delete(id);
        return deleted ?
                "{\"message\": \"Jogador " + id + " deletado com sucesso\"}" :
                "{\"error\": \"Jogador não encontrado\"}";
    }

    /*
        FUNÇÕES DE CHARACTER (PERSONAGEM)
    */

    public String getAllCharactersOfPlayer(int playerId) {
        Player player = service.findById(playerId);
        if (player == null) {
            return "{\"error\": \"Jogador não encontrado\"}";
        }
        return gson.toJson(player.getCharacters());
    }

    public String getSpecificCharacterOfPlayer(int playerId, int characterId) {
        Character character = service.findCharacterInPlayer(playerId, characterId);
        if (character == null) {
            return "{\"error\": \"Personagem não encontrado\"}";
        }
        return gson.toJson(character);
    }

    public String getCharacterStatus(int playerId, int characterId) {
        Character character = service.findCharacterInPlayer(playerId, characterId);
        if (character == null) {
            return "{\"error\": \"Personagem não encontrado\"}";
        }
        return gson.toJson(character.getStatus());
    }

    public String createCharacter(int playerId, InputStreamReader reader) {
        Player player = service.findById(playerId);
        if (player == null) {
            return "{\"error\": \"Jogador não encontrado\"}";
        }

        CharacterDTO dto = gson.fromJson(reader, CharacterDTO.class);

        Character character = new Character(
                0, // ID será definido no service
                dto.getName(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getTypeClass(),
                dto.getlevel()
        );

        Character created = service.addCharacterToPlayer(playerId, character);

        return gson.toJson(created);
    }

    public String updateCharacter(int playerId, int characterId, InputStreamReader reader) {
        CharacterDTO dto = gson.fromJson(reader, CharacterDTO.class);

        Character updatedCharacter = new Character(
                0, // ID será ajustado no service
                dto.getName(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getTypeClass(),
                dto.getlevel()
        );

        Character result = service.updateCharacterInPlayer(playerId, characterId, updatedCharacter);
        if (result == null) {
            return "{\"error\": \"Personagem não encontrado para atualização\"}";
        }
        return gson.toJson(result);
    }

    public String deleteCharacter(int playerId, int characterId) {
        boolean removed = service.removeCharacterFromPlayer(playerId, characterId);
        return removed ?
                "{\"message\": \"Personagem " + characterId + " removido\"}" :
                "{\"error\": \"Personagem não encontrado\"}";
    }
}
