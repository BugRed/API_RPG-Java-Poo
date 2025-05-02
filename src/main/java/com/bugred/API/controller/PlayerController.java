package com.bugred.API.controller;

import com.bugred.API.dto.DTOFactory;
import com.bugred.API.dto.request.PlayerRequestRequestDTO;
import com.bugred.API.model.Player;
import com.bugred.API.service.PlayerService;
import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlayerController {

    private final PlayerService service = PlayerService.getInstance();
    private final Gson gson = new Gson();

    public String getAllPlayers() {
        List<PlayerRequestRequestDTO> dtos = service.findAll().stream()
                .map(p -> (PlayerRequestRequestDTO) DTOFactory.toDTO(p))
                .collect(Collectors.toList());
        return gson.toJson(dtos);
    }

    public String getPlayerById(UUID id) {
        Player player = service.findById(id);
        if (player == null) {
            return "{\"error\": \"Jogador não encontrado\"}";
        }
        return gson.toJson(DTOFactory.toDTO(player));
    }

    public String createPlayer(InputStreamReader reader) {
        PlayerRequestRequestDTO dto = gson.fromJson(reader, PlayerRequestRequestDTO.class);
        Player player = DTOFactory.fromDTO(dto, Player.class);
        Player created = service.create(player);
        return gson.toJson(DTOFactory.toDTO(created));
    }

    public String updatePlayer(UUID id, InputStreamReader reader) {
        PlayerRequestRequestDTO dto = gson.fromJson(reader, PlayerRequestRequestDTO.class);
        Player updatedEntity = DTOFactory.fromDTO(dto, Player.class);
        Player updated = service.update(id, updatedEntity);
        if (updated == null) {
            return "{\"error\": \"Jogador não encontrado para atualização\"}";
        }
        return gson.toJson(DTOFactory.toDTO(updated));
    }

    public String deletePlayer(UUID id) {
        boolean deleted = service.delete(id);
        return deleted ?
                "{\"message\": \"Jogador " + id + " deletado com sucesso\"}" :
                "{\"error\": \"Jogador não encontrado\"}";
    }
}