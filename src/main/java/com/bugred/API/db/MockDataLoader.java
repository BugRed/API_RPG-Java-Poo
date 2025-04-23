package com.bugred.API.db;

import com.bugred.API.model.Character;
import com.bugred.API.model.Player;
import com.bugred.API.model.Status;
import com.bugred.API.service.CharacterService;
import com.bugred.API.service.PlayerService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Classe responsável por carregar os dados mock do arquivo JSON localizado em resources.
 */
public class MockDataLoader {

    private static final Gson gson = new Gson();

    /**
     * Lê o arquivo mockData.json e adiciona os jogadores, personagens e status ao serviço correspondente.
     */
    public static void loadMockData(PlayerService playerService, CharacterService characterService) {
        try (InputStream inputStream = MockDataLoader.class.getClassLoader().getResourceAsStream("mockData.json");
             InputStreamReader reader = inputStream != null
                     ? new InputStreamReader(inputStream, StandardCharsets.UTF_8)
                     : null) {

            if (reader == null) {
                System.err.println("❌ mockData.json não encontrado. Verifique se está em src/main/resources.");
                return;
            }

            // Criando o type que será usado
            Type playerListType = new TypeToken<List<Player>>() {}.getType();
            List<Player> players = gson.fromJson(reader, playerListType);

            if (players == null || players.isEmpty()) {
                System.err.println("⚠️ mockData.json está vazio ou mal formatado.");
                return;
            }

            // O for serve para percorrer a lista type e adicionar todos os jogadores que estão no JSON
            for (Player player : players) {
                // Adiciona o jogador
                playerService.addPlayer(player);

                // Adiciona os personagens do jogador
                if (player.getCharacters() != null) {
                    for (Character character : player.getCharacters()) {
                        characterService.save(player.getId(), character);

                        // Adiciona o status do personagem (se houver)
                        Status status = character.getStatus();
                        if (status != null) {
                            characterService.saveStatus(player.getId(), character.getId(), status);
                        }
                    }
                }
            }

            System.out.println("✅ Mock data carregado com sucesso.");

        } catch (Exception e) {
            System.err.println("❌ Erro ao carregar mock data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
