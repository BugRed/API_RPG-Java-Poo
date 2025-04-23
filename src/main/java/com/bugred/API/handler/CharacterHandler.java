package com.bugred.API.handler;

import com.bugred.API.controller.CharacterController;
import com.bugred.API.utils.UtilHandler;
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

                response = switch (method) {
                    case "GET" -> controller.getAllCharacters(playerId);
                    case "POST" -> controller.createCharacter(playerId, reader);
                    default -> {
                        statusCode = 405;
                        yield "{\"error\": \"Método " + method + " não é permitido em /players/{playerId}/characters\"}";
                    }
                };

                // /players/{playerId}/characters/{characterId}
            } else if (parts.length == 4 && parts[0].equals("players") && parts[2].equals("characters")) {
                int playerId = Integer.parseInt(parts[1]);
                int characterId = Integer.parseInt(parts[3]);

                response = switch (method) {
                    case "GET" -> controller.getCharacterById(playerId, characterId);
                    case "PUT" -> controller.updateCharacter(playerId, characterId, reader);
                    case "DELETE" -> controller.deleteCharacter(playerId, characterId);
                    default -> {
                        statusCode = 405;
                        yield "{\"error\": \"Método " + method + " não é permitido em /players/{playerId}/characters/{characterId}\"}";
                    }
                };

            } else {
                UtilHandler.sendNotFound(exchange, path);
            }

        } catch (Exception e) {
            statusCode = 500;
            response = "{\"error\": \"Erro interno: " + e.getMessage() + "\"}";
            e.printStackTrace();
        }

        UtilHandler.sendResponse(exchange, response, statusCode);
    }
}
