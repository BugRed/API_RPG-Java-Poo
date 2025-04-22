package com.bugred.API.controller;

import com.bugred.API.dto.DTOFactory;
import com.bugred.API.dto.PlayerDTO;
import com.bugred.API.model.Player;
import com.bugred.API.service.PlayerService;
import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerController {

    private final PlayerService service = PlayerService.getInstance();
    private final Gson gson = new Gson();

    public String getAllPlayers() {
        List<PlayerDTO> dtos = service.findAll().stream()
                .map(p -> (PlayerDTO) DTOFactory.toDTO(p))
                .collect(Collectors.toList());
        return gson.toJson(dtos);
    }

    public String getPlayerById(int id) {
        Player player = service.findById(id);
        if (player == null) {
            return "{\"error\": \"Jogador não encontrado\"}";
        }
        return gson.toJson(DTOFactory.toDTO(player));
    }

    public String createPlayer(InputStreamReader reader) {
        PlayerDTO dto = gson.fromJson(reader, PlayerDTO.class);
        Player player = DTOFactory.fromDTO(dto, Player.class);
        Player created = service.create(player);
        return gson.toJson(DTOFactory.toDTO(created));
    }

    public String updatePlayer(int id, InputStreamReader reader) {
        PlayerDTO dto = gson.fromJson(reader, PlayerDTO.class);
        Player updatedEntity = DTOFactory.fromDTO(dto, Player.class);
        Player updated = service.update(id, updatedEntity);
        if (updated == null) {
            return "{\"error\": \"Jogador não encontrado para atualização\"}";
        }
        return gson.toJson(DTOFactory.toDTO(updated));
    }

    public String deletePlayer(int id) {
        boolean deleted = service.delete(id);
        return deleted ?
                "{\"message\": \"Jogador " + id + " deletado com sucesso\"}" :
                "{\"error\": \"Jogador não encontrado\"}";
    }
}