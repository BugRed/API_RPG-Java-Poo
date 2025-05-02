package com.bugred.API.controller;

import com.bugred.API.dto.request.CharacterRequestDTO;
import com.bugred.API.dto.DTOFactory;
import com.bugred.API.model.Character;
import com.bugred.API.model.Player;
import com.bugred.API.service.CharacterService;
import com.bugred.API.service.PlayerService;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CharacterController {

    // Sendo character dependente de player, necessita das duas instâncias
    private final CharacterService characterService = CharacterService.getInstance();
    private final PlayerService playerService = PlayerService.getInstance();

    private final Gson gson = new Gson();

    /*
        FUNÇÕES DE CHARACTER
    */

    // Retorna todos os personagens associados a um jogador específico
    public String getAllCharacters(UUID playerId) {
        // Verifica se o jogador existe
        Player player = playerService.findById(playerId);

        if (player == null) {
            // Retorna erro se o jogador não for encontrado
            return "{\"error\": \"Jogador não encontrado\"}";
        }

        // Busca todos os personagens associados ao jogador
        List<Character> characters = characterService.findAll(player);

        // Converte os personagens encontrados em DTOs
        // Isso garante que apenas os dados relevantes sejam enviados para o cliente
        List<CharacterRequestDTO> characterRequestDTOS = characters.stream()
                .map(DTOFactory::toCharacterDTO) // Converte cada entidade Character em um CharacterDTO
                .collect(Collectors.toList());

        // Retorna a lista de personagens como JSON
        return gson.toJson(characterRequestDTOS);
    }


    // Retorna um personagem específico pelo seu ID
    public String getCharacterById(UUID playerId, UUID characterId) {
        Character character = characterService.findById(playerId, characterId);
        if (character == null) {
            return "{\"error\": \"Personagem não encontrado\"}";
        }
        return gson.toJson(DTOFactory.toCharacterDTO(character));
    }

    // Cria um novo personagem para um jogador
    public String createCharacter(UUID playerId, InputStreamReader reader) {
        CharacterRequestDTO dto = gson.fromJson(reader, CharacterRequestDTO.class);
        Character character = characterService.create(playerId, dto);

        if (character == null) {
            return "{\"error\": \"Jogador não encontrado para criar o personagem\"}";
        }

        return gson.toJson(DTOFactory.toCharacterDTO(character));
    }

    // Atualiza os dados de um personagem específico
    public String updateCharacter(UUID playerId, UUID characterId, InputStreamReader reader) {
        CharacterRequestDTO dto = gson.fromJson(reader, CharacterRequestDTO.class);
        Character character = characterService.update(playerId, characterId, dto);

        if (character == null) {
            return "{\"error\": \"Personagem não encontrado para atualização\"}";
        }

        return gson.toJson(DTOFactory.toCharacterDTO(character));
    }

    // Deleta um personagem específico
    public String deleteCharacter(UUID playerId, UUID characterId) {
        boolean deleted = characterService.delete(playerId, characterId);
        return deleted ?
                "{\"message\": \"Personagem " + characterId + " deletado com sucesso\"}" :
                "{\"error\": \"Personagem não encontrado\"}";
    }
}
