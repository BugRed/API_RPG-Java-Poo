package com.bugred.API.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


/**
 * Classe utilitária para manipulação de requisições e respostas HTTP.
 * Contém métodos para enviar respostas, limpar partes do caminho e verificar se uma string é um inteiro.
 */


public class UtilHandler {

    /**
     * Envio de resposta HTTP com o corpo e código de status especificados.
     */

    public static void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        // Definir o tipo de conteudo da resposta
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        // Convertendo a string response em bytes
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
        // Envia o header da resposta HTTP com o codigo de estatus e tamanho
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        // Escreve o corpo da resposta
        try (OutputStream os = exchange.getResponseBody()) {
            os.write((responseBytes));
        }
    }


    /**
     * Limpa partes do caminho, removendo entradas vazias.
     */
    public static String[] cleanPathParts(String[] parts) {
        return java.util.Arrays.stream(parts)
                .filter(part -> !part.isEmpty())
                .toArray(String[]::new);
    }


    public static boolean isValidUUID(String value) {

        if (value == null || value.isBlank()) return false;

        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
            }
        }

    /**
     * Verifica se uma string pode ser convertida para um inteiro.
     */
    // Função ficou obsoleta com o uso de UUID no lugar de Integer
//    public static boolean isInteger(String s) {
//        try {
//            Integer.parseInt(s);
//            return true;
//            // Mudar essa exception em breve
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }

    /**
     * Envia uma resposta de erro 404 (não encontrado) com uma mensagem personalizada.
     */
    public static void sendNotFound(HttpExchange exchange, String path) throws IOException {
        String response = "{\"error\": \"Rota: " + path + " não encontrada\"}";
        exchange.sendResponseHeaders(404, response.getBytes().length);
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }

//    public static <T> void loadFromFile(Class<T> clazz, String filePath, Map<Integer, Object> storage, Gson gson) {
//
//        try {
//                  if (dto instanceof PlayerDTO pd && clazz == Player.class) return clazz.cast(toPlayer(pd));
//
//
//            // Verifica se o arquivo existe, se não existir, cria um novo
//            File file = new File(filePath);
//            if (!file.exists()) {
//                file.getParentFile().mkdirs();
//                file.createNewFile();
//                // Cria um arquivo JSON vazio
//                try (FileWriter writer = new FileWriter(file)) {
//                    writer.write("[]"); // JSON vazio válido
//                }
//            }
//
//
//
//            try (FileReader reader = new FileReader(file)) {
//                if (type.equals("PLAYER")) {
//                    // Cria o type que será usado
//                    // O TypeToken é usado para capturar o tipo genérico List<Player>
//                    // Isso é necessário porque o Java não mantém informações de tipo em tempo de execução
//                    // Portanto, precisamos usar TypeToken para informar ao Gson qual tipo de lista estamos esperando
//                    // O TypeToken é uma classe do Gson que permite capturar tipos genéricos
//                    Type listType = new TypeToken<List<Player>>() {}.getType();
//                    List<Player> listFile = gson.fromJson(reader, listType);
//                } else if (type.equals("CHARACTER")) {
//                    Type listType = new TypeToken<List<Character>>() {
//                    }.getType();
//                    List<Character> listFile = gson.fromJson(reader, listType);
//                } else if (type.equals("STATUS")) {
//                    Type listType = new TypeToken<List<Status>>() {
//                    }.getType();
//                    List<Status> listFile = gson.fromJson(reader, listType);
//                }
//
//                if (listFile != null) {
//                    for (Object o : listFile) {
//                        storage.put(o.getId(), o);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Erro ao carregar jogadores do arquivo: " + e.getMessage());
//        }
//    }
}
