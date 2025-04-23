package com.bugred.API.handler;

import com.bugred.API.utils.UtilHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Criando um Handler que faça a rotação entre os endpoints
 */

public class RouterHandler implements HttpHandler {

    private final PlayerHandler playerHandler = new PlayerHandler();
    private final CharacterHandler characterHandler = new CharacterHandler();
    private final StatusHandler statusHandler = new StatusHandler();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Pegando a url e dividindo em uma lista
        String path = exchange.getRequestURI().getPath();
        String[] parts = path.split("/");

        // Remove elementos vazios (causados por "/" inicial)
        parts = UtilHandler.cleanPathParts(parts);

        try {
            // /players ou /players/{id}
            if (parts.length == 1 || (parts.length == 2 && UtilHandler.isInteger(parts[1]))) {
                playerHandler.handle(exchange);
            }

            // /players/{playerId}/characters
            else if (parts.length == 3 && parts[2].equals("characters") && UtilHandler.isInteger(parts[1])) {
                characterHandler.handle(exchange);
            }

            // /players/{playerId}/characters/{characterId}
            else if (parts.length == 4 && parts[2].equals("characters") &&
                    UtilHandler.isInteger(parts[1]) && UtilHandler.isInteger(parts[3])) {
                characterHandler.handle(exchange);
            }

            // /players/{playerId}/characters/{characterId}/status
            else if (parts.length == 5 && parts[2].equals("characters") && parts[4].equals("status") &&
                    UtilHandler.isInteger(parts[1]) && UtilHandler.isInteger(parts[3])) {
                statusHandler.handle(exchange);
            }

            else {
                // Rota não reconhecida
                UtilHandler.sendNotFound(exchange, path);
            }

        } catch (Exception e) {
            // Fallback de erro inesperado
            String response = "{\"error\": \"Erro interno: " + e.getMessage() + "\"}";
            exchange.sendResponseHeaders(500, response.getBytes().length);
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
