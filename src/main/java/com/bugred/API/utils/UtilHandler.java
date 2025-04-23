package com.bugred.API.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


/**
 * Classe utilitária para manipulação de requisições e respostas HTTP.
 * Contém métodos para enviar respostas, limpar partes do caminho e verificar se uma string é um inteiro.
 */


public class UtilHandler {

    public static void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
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


    // Metodo para limpar partes do caminho, removendo elementos vazios
    public static String[] cleanPathParts(String[] parts) {
        return java.util.Arrays.stream(parts)
                .filter(part -> !part.isEmpty())
                .toArray(String[]::new);
    }

    // Metodo para verificar se uma string é um inteiro
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
            // Mudar essa exception em breve
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Metodo para enviar uma resposta de erro 404
    public static void sendNotFound(HttpExchange exchange, String path) throws IOException {
        String response = "{\"error\": \"Rota: " + path + " não encontrada\"}";
        exchange.sendResponseHeaders(404, response.getBytes().length);
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }
}
