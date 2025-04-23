package com.bugred.API.handler;

import com.bugred.API.controller.StatusController;
import com.bugred.API.utils.UtilHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

// Implementa a classe HttpHandler para tratar rotas de /players/{playerId}/characters/{characterId}/status
public class StatusHandler implements HttpHandler {

    // Instância do controller
    private final StatusController controller;

    // Chamado sempre que o servidor recebe uma requisição HTTP para a rota de status
    public StatusHandler() {
        this.controller = new StatusController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Pegando qual foi a requisição (GET, PUT)
        String method = exchange.getRequestMethod();
        // Pega a URL da requisição
        String path = exchange.getRequestURI().getPath();
        // Divide a URL pelos / facilitando roteamento
        String[] parts = path.split("/");
        // Variável que vai guardar a resposta
        String response = "";
        // Código de status padrão 200 OK
        int statusCode = 200;

        try {
            InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);

            // /players/{playerId}/characters/{characterId}/status
            if (parts.length == 6 && parts[1].equals("players") && parts[3].equals("characters") && parts[5].equals("status")) {
                int playerId = Integer.parseInt(parts[2]);
                int characterId = Integer.parseInt(parts[4]);

                response = switch (method) {
                    case "GET" -> controller.getStatus(playerId, characterId);
                    case "PUT" -> controller.updateStatus(playerId, characterId, reader);
                    default -> {
                        statusCode = 405;
                        yield "{\"error\": \"Método " + method + " não é permitido em /players/{playerId}/characters/{characterId}/status\"}";
                    }
                };
            } else {
                // Caso nenhuma rota seja compatível
                UtilHandler.sendNotFound(exchange, path);
            }

        } catch (Exception e) {
            // Erro interno do servidor
            statusCode = 500;
            response = "{\"error\": \"Erro interno: " + e.getMessage() + "\"}";
            e.printStackTrace();
        }

        UtilHandler.sendResponse(exchange, response, statusCode);
    }
}
