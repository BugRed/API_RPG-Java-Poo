package com.bugred.API.handler;

import com.bugred.API.controller.PlayerController;
import com.bugred.API.utils.UtilHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

// implementa a classe HttpExchange
public class PlayerHandler implements HttpHandler {
    // Criando instancia de controller
    private final PlayerController controller;

    // Chamado sempre que o servidor recebe uma requisição HTTP para a rota /players
    public PlayerHandler(){
        this.controller = new PlayerController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Pegando qual foi a requisição (GET, POST, PUT, DELETE)
        String method = exchange.getRequestMethod();
        // Pega a URL da requisição
        String path = exchange.getRequestURI().getPath();
        // Divide a URL pelos / facilitando roteamento
        String[] parts = path.split("/");
        // Variavel que vai guardar a resposta
        String response = "";
        // Status codigo padrão 200 ok
        int statusCode = 200;

        try {
            // Instancia reader
            InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);

            // /players
            // Se houver algo depois do / e esse algo for players
            if (parts.length == 2 && parts[1].equals("players")) {
                // analisando qual metodo foi usado
                // atribuindo diretamente a escolha a uma variavel como no javascript
                response = switch (method) {
                    case "GET" ->
                        // caso de GET response recebe TODOS OS PLAYERS
                            controller.getAllPlayers();
                    case "POST" ->
                        // caso de POST response recebe um player criado usando o reader como input
                            controller.createPlayer(reader);
                    default -> {
                        // 405 significa que o metodo utilizado não corresponde a um metodo existente nessa rota
                        statusCode = 405;
                        // yield é retorno para default
                        yield "{\"error\": \"Método " + method + " não é permitido em /players\"}";
                    }
                };

                // /players/{id}
                // Se houver algo depois do /players e esse algo for um id valido
            } else if (parts.length == 3 && parts[1].equals("players")) {
                // pegando o que tem depois de /players e convertendo em inteiro
                int playerId = Integer.parseInt(parts[2]);
                response = switch (method) {
                    case "GET" -> controller.getPlayerById(playerId);
                    case "PUT" -> controller.updatePlayer(playerId, reader);
                    case "DELETE" -> controller.deletePlayer(playerId);
                    default -> {
                        statusCode = 405;
                        yield "{\"error\": \"Método " + method + " não é permitido em /players\"}";
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
