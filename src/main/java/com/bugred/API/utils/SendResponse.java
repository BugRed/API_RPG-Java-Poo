package com.bugred.API.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SendResponse {

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
}
