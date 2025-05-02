package com.bugred.API.repository;

import com.bugred.API.model.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


/**
 * Classe responsável por gerenciar o armazenamento de jogadores.
 */
public class PlayerRepository {

    // Tudo vai funcionar em um MAP pq o json é uma lista no mockData
    private final Map<UUID, Player> playerStorage = new HashMap<>();
    private final String filePath;
    private final Gson gson = new Gson();

    // Quando eu inicio o repositorio eu faço o load do arquivo
    public PlayerRepository(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    // Pegando todos os players
    public List<Player> findAll() {
        return new ArrayList<>(playerStorage.values());
    }

    // procurando por id
    public Player findById(UUID id) {
        return playerStorage.get(id);
    }

    public void save(Player player) {
        playerStorage.put(player.getId(), player);
        saveToFile();
    }

    public boolean delete(UUID id) {
        boolean removed = playerStorage.remove(id) != null;
        if (removed) saveToFile();
        return removed;
    }

    public boolean exists(UUID id) {
        return playerStorage.containsKey(id);
    }


    // Essa função é chamada quando o servidor é iniciado
    // Ela carrega os jogadores do arquivo JSON
    private void loadFromFile() {
        try {
            // Verifica se o arquivo existe, se não existir, cria um novo
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                // Cria um arquivo JSON vazio
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]"); // JSON vazio válido
                }
            }
            // Lê o arquivo JSON e converte para uma lista de jogadores
            try (FileReader reader = new FileReader(file)) {
                // Cria o type que será usado
                // O TypeToken é usado para capturar o tipo genérico List<Player>
                // Isso é necessário porque o Java não mantém informações de tipo em tempo de execução
                // Portanto, precisamos usar TypeToken para informar ao Gson qual tipo de lista estamos esperando
                // O TypeToken é uma classe do Gson que permite capturar tipos genéricos
                Type listType = new TypeToken<List<Player>>() {}.getType();
                List<Player> players = gson.fromJson(reader, listType);
                if (players != null) {
                    for (Player player : players) {
                        playerStorage.put(player.getId(), player);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar jogadores do arquivo: " + e.getMessage());
        }
    }

    // Essa função é chamada quando um jogador é adicionado ou atualizado
    // Ela salva os jogadores no arquivo JSON
    private void saveToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(playerStorage.values(), writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar jogadores no arquivo: " + e.getMessage());
        }
    }
}
