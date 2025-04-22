package com.bugred.API.db;

import com.bugred.API.model.Player;
import com.bugred.API.service.PlayerService;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.nio.charset.StandardCharsets;
import java.util.List;

// Essa classe faz o mock de dados para criar
// um banco de dados para testes na memoria
// nesse caso em um json chamado mockData em resources
public class MockDataLoader {

    // Criando uma instância di Gson para ser a única
    // usada na classe toda
    private static final Gson gson = new Gson();

    public static void loadMockData(PlayerService playerService){

        // Tentativa de carregar o mockData.json
        try (InputStream inputStream = MockDataLoader.class.getClassLoader().getResourceAsStream("mockData.json");
             InputStreamReader reader = inputStream != null
                     ? new InputStreamReader(inputStream, StandardCharsets.UTF_8)
                     : null){

            // Checando se o arquivo tem algo dentro
            if (reader == null){
                System.err.println("❌ mockData.json não encontrado. Verifique se está em src/main/resources.");
                return;
            }

            // Criando um Type para a Lista de objetos
            Type playerListType = new TypeToken<List<Player>>(){}.getType();
            List<Player> players = gson.fromJson(reader, playerListType);

            // Checando se ele conseguiu carregar o arquivo
            if(players == null || players.isEmpty()){
                System.err.println("⚠️ mockData.json está vazio ou mal formatado.");
                return;
            }

            // Criando um player para cada um player dentro do arquivo
            for (Player p : players){
                playerService.create(p);
            }
            System.out.println("✅ Mock data carregado com sucesso.");


        // Solucionar as excessões depois
        } catch (Exception e){
            System.err.println("❌ Erro ao carregar mock data: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
