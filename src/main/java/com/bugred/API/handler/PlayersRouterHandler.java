package com.bugred.API.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class PlayersRouterHandler implements HttpHandler {

    private final PlayerHandler playerHandler = new PlayerHandler();
    private final CharacterHandler characterHandler = new CharacterHandler();
    private final StatusHandler statusHandler = new StatusHandler();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] parts = path.split("/");

        // Remove elementos vazios (causados por "/" inicial)
        parts = cleanPathParts(parts);

        try {
            // /players ou /players/{id}
            if (parts.length == 1 || (parts.length == 2 && isInteger(parts[1]))) {
                playerHandler.handle(exchange);
            }

            // /players/{playerId}/characters
            else if (parts.length == 3 && parts[2].equals("characters") && isInteger(parts[1])) {
                characterHandler.handle(exchange);
            }

            // /players/{playerId}/characters/{characterId}
            else if (parts.length == 4 && parts[2].equals("characters") && isInteger(parts[1]) && isInteger(parts[3])) {
                characterHandler.handle(exchange);
            }

            // /players/{playerId}/characters/{characterId}/status
            else if (parts.length == 5 && parts[2].equals("characters") && parts[4].equals("status") &&
                    isInteger(parts[1]) && isInteger(parts[3])) {
                statusHandler.handle(exchange);
            }

            else {
                // Rota não reconhecida
                sendNotFound(exchange, path);
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

    private String[] cleanPathParts(String[] parts) {
        return java.util.Arrays.stream(parts)
                .filter(part -> !part.isEmpty())
                .toArray(String[]::new);
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void sendNotFound(HttpExchange exchange, String path) throws IOException {
        String response = "{\"error\": \"Rota: " + path + " não encontrada\"}";
        exchange.sendResponseHeaders(404, response.getBytes().length);
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }
}
