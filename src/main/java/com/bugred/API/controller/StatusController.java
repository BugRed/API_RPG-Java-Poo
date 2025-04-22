package com.bugred.API.controller;

import com.bugred.API.dto.DTOFactory;
import com.bugred.API.dto.StatusDTO;
import com.bugred.API.dto.DTOFactory;
import com.bugred.API.model.Character;
import com.bugred.API.model.Status;
import com.bugred.API.service.CharacterService;
import com.google.gson.Gson;

import java.io.InputStreamReader;

public class StatusController {

    private final CharacterService characterService = CharacterService.getInstance();
    private final Gson gson = new Gson();

    /*
        FUNÇÕES DE STATUS
    */

    // Retorna o status de um personagem específico
    public String getStatus(int playerId, int characterId) {
        Character character = characterService.findById(playerId, characterId);

        if (character == null || character.getStatus() == null) {
            return "{\"error\": \"Personagem ou status não encontrado\"}";
        }

        StatusDTO statusDTO = DTOFactory.toStatusDTO(character.getStatus());
        return gson.toJson(statusDTO);
    }

    // Atualiza o status de um personagem
    public String updateStatus(int playerId, int characterId, InputStreamReader reader) {
        StatusDTO statusDTO = gson.fromJson(reader, StatusDTO.class);
        Status updatedStatus = characterService.updateStatus(playerId, characterId, statusDTO);

        if (updatedStatus == null) {
            return "{\"error\": \"Não foi possível atualizar o status\"}";
        }

        return gson.toJson(DTOFactory.toStatusDTO(updatedStatus));
    }
}
