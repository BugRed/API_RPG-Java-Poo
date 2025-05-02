package com.bugred.API.controller;

import com.bugred.API.dto.DTOFactory;
import com.bugred.API.dto.request.StatusRequestDTO;
import com.bugred.API.model.Character;
import com.bugred.API.model.Status;
import com.bugred.API.service.CharacterService;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.UUID;

public class StatusController {

    private final CharacterService characterService = CharacterService.getInstance();
    private final Gson gson = new Gson();

    /*
        FUNÇÕES DE STATUS
    */

    // Retorna o status de um personagem específico
    public String getStatus(UUID playerId, UUID characterId) {
        Character character = characterService.findById(playerId, characterId);

        if (character == null || character.getStatus() == null) {
            return "{\"error\": \"Personagem ou status não encontrado\"}";
        }

        StatusRequestDTO statusRequestDTO = DTOFactory.toStatusDTO(character.getStatus());
        return gson.toJson(statusRequestDTO);
    }

    // Atualiza o status de um personagem
    public String updateStatus(UUID playerId, UUID characterId, InputStreamReader reader) {
        StatusRequestDTO statusRequestDTO = gson.fromJson(reader, StatusRequestDTO.class);
        Status updatedStatus = characterService.updateStatus(playerId, characterId, statusRequestDTO);

        if (updatedStatus == null) {
            return "{\"error\": \"Não foi possível atualizar o status\"}";
        }

        return gson.toJson(DTOFactory.toStatusDTO(updatedStatus));
    }
}
