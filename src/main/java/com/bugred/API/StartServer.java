package com.bugred.API;

import com.bugred.API.db.MockDataLoader;
import com.bugred.API.handler.RouterHandler;
import com.bugred.API.service.CharacterService;
import com.bugred.API.service.PlayerService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Serviço responsável por startar o server e instanciar os endpoints
 */

public class StartServer {

    public static void Start() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // Obtém a instância singleton do PlayerService
            PlayerService playerService = PlayerService.getInstance();
            CharacterService characterService = CharacterService.getInstance();

            // Carrega os dados do JSON
            MockDataLoader.loadMockData(playerService, characterService);

            // Criação dos endpoints
            // Aqui foi necessário criar um endpoint só para todas as hotas e um Router para manipula-lás
            server.createContext("/players", new RouterHandler());


            server.setExecutor(null); // Executor padrão
            server.start();

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
