package com.bugred.API.handler;

import com.bugred.API.controller.CharacterController;
import com.bugred.API.utils.SendResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

// Implementa a classe HttpHandler para tratar rotas de /players/{playerId}/characters
public class CharacterHandler implements HttpHandler {

    private final CharacterController controller;

    public CharacterHandler() {
        this.controller = new CharacterController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] rawParts = path.split("/");
        String[] parts = java.util.Arrays.stream(rawParts).filter(p -> !p.isEmpty()).toArray(String[]::new);

        String response = "";
        int statusCode = 200;

        try {
            InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);

            // /players/{playerId}/characters
            if (parts.length == 3 && parts[0].equals("players") && parts[2].equals("characters")) {
                int playerId = Integer.parseInt(parts[1]);

                switch (method) {
                    case "GET":
                        response = controller.getAllCharacters(playerId);
                        break;
                    case "POST":
                        response = controller.createCharacter(playerId, reader);
                        break;
                    default:
                        statusCode = 405;
                        response = "{\"error\": \"Método " + method + " não é permitido em /players/{playerId}/characters\"}";
                        break;
                }

                // /players/{playerId}/characters/{characterId}
            } else if (parts.length == 4 && parts[0].equals("players") && parts[2].equals("characters")) {
                int playerId = Integer.parseInt(parts[1]);
                int characterId = Integer.parseInt(parts[3]);

                switch (method) {
                    case "GET":
                        response = controller.getCharacterById(playerId, characterId);
                        break;
                    case "PUT":
                        response = controller.updateCharacter(playerId, characterId, reader);
                        break;
                    case "DELETE":
                        response = controller.deleteCharacter(playerId, characterId);
                        break;
                    default:
                        statusCode = 405;
                        response = "{\"error\": \"Método " + method + " não é permitido em /players/{playerId}/characters/{characterId}\"}";
                        break;
                }

            } else {
                statusCode = 404;
                response = "{\"error\": \"Rota: " + path + " não encontrada\"}";
            }

        } catch (Exception e) {
            statusCode = 500;
            response = "{\"error\": \"Erro interno: " + e.getMessage() + "\"}";
            e.printStackTrace();
        }

        SendResponse.sendResponse(exchange, response, statusCode);
    }
}
