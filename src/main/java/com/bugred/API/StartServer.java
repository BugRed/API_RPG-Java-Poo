package com.bugred.API;


import com.bugred.API.db.MockDataLoader;
import com.bugred.API.handler.PlayerHandler;
import com.bugred.API.service.PlayerService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

// Essa classe serve para manter encapsulada
// a lógica de inicialização do servidor
public class StartServer {
    public static void Start() {
        try {
            // Criando a instancia de service que será usada
            HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);
            // criando uma instancia singleton
            PlayerService playerService = PlayerService.getInstance();

            // Carregando arquivos usando o loader
            MockDataLoader.loadMockData(playerService);

            // Criação de endpoints

            // /players
            server.createContext("/players", new PlayerHandler());


            server.setExecutor(null); // Executor padrão
            server.start(); // Startando server

            System.out.println("✅ Servidor iniciado na porta: " + server.getAddress().getPort());

        } catch (IOException e) {
            System.err.println("❌ Erro de IO ao iniciar o servidor: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
