package com.bugred.API.handler;

import com.bugred.API.controller.PlayerController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

// implementa a classe HttpExchenge
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

            InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                // /players
                // Se houver algo depois do / e esse algo for players
            if (parts.length == 2 && parts[1].equals("players")){
                // analisando qual metodo foi usado
                switch (method) {
                    case "GET":
                        // caso de GET response recebe TODOS OS PLAYERS
                        response = controller.getAllPlayers();
                        break;
                    case "POST":
                        // caso de POST response recebe um player criado usando o reader como input
                        response = controller.createPlayer(reader);
                        break;
                    default:
                        // 405 significa que o metodo utilizado não corresponde a um metodo existente nessa rota
                        statusCode = 405;
                        response = "{\"error\": \"Método " + method + " não é permitido em /players\"}";
                        break;
                }
            // /players/{id}
            // Se houver algo depois do /players e esse algo for um id valido
            } else if (parts.length == 3 && parts[1].equals("players")){
                // pegando o que tem depois de /players e convertando em inteiro
                int playerId = Integer.parseInt(parts[2]);
                switch (method){
                    case "GET":
                        response = controller.getPlayerById(playerId);
                        break;
                    case "PUT":
                        response = controller.updatePlayer(playerId, reader);
                        break;
                    case "DELETE":
                        response = controller.deletePlayer(playerId);
                        break;
                    default:
                        statusCode = 405;
                        response = "{\"error\": \"Método " + method + " não é permitido em /players\"}";
                        break;
                }
            // /players/{id}/characters
            } else if (parts.length == 4 && parts[1].equals("players") && parts[3].equals("characters")){
                int playerId = Integer.parseInt(parts[2]);

                switch (method) {
                    case "GET":
                        response = controller.getAllCharactersOfPlayer(playerId);
                        break;
                    case "POST":
                        response = controller.createCharacter(playerId, reader);
                        break;
                    default:
                        statusCode = 405;
                        response = "{\"error\": \"Método " + method + " não é permitido em /players\"}";
                        break;
                }
            // /player/{id}/characters/{characterId}
            } else if (parts.length == 5 && parts[1].equals("players") && parts[3].equals("characters")){
                    int playerId = Integer.parseInt(parts[2]);
                    // pegado o id do character
                    int characterId = Integer.parseInt(parts[4]);

                    // Como so tem get por hora será apenas if
                    if (method.equals("GET")){
                        response = controller.getSpecificCharacterOfPlayer(playerId, characterId);
                    } else {
                        statusCode = 405;
                        response = "{\"error\": \"Método " + method + " não é permitido em /players\"}";
                    }
                // /players/{id}/characters/{characterId}/tatus
                } else if (parts.length == 6 && parts[1].equals("players") && parts[3].equals("characters") && parts[5].equals("status")) {
                    int playerId = Integer.parseInt(parts[2]);
                    int characterId = Integer.parseInt(parts[4]);

                    if (method.equals("GET")){
                      response = controller.getCharacterStatus(playerId, characterId);
                    } else {
                        statusCode = 405;
                        response = "{\"error\": \"Método " + method + " não é permitido em /players\"}";
                    }
                } else {
                    statusCode = 404;
                    response = "{\"error\": \"Rota: " + path +" não encontrada\"}";
                }

            } catch (Exception e){
            // Erro interno do servidor
            statusCode = 500;
            response = "{\"error\": \"Erro interno: " + e.getMessage() + "\"}";
            e.printStackTrace();
        }
        sendResponse(exchange, response, statusCode);

        }

    private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        // Definir o tipo de conteudo da resposta
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        // Convertendo a string response em bytes
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
        // Envia o header da resposta HTTP com o codigo de estatus e tamanho
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        // Escreve o corpo da resposta
        try (OutputStream os = exchange.getResponseBody()){
            os.write((responseBytes));
        }
    }
}
