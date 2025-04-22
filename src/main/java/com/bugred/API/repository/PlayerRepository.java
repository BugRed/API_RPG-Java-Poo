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

public class PlayerRepository {

    private final Map<Integer, Player> playerStorage = new HashMap<>();
    private final String filePath;
    private final Gson gson = new Gson();

    public PlayerRepository(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    public List<Player> findAll() {
        return new ArrayList<>(playerStorage.values());
    }

    public Player findById(int id) {
        return playerStorage.get(id);
    }

    public void save(Player player) {
        playerStorage.put(player.getId(), player);
        saveToFile();
    }

    public boolean delete(int id) {
        boolean removed = playerStorage.remove(id) != null;
        if (removed) saveToFile();
        return removed;
    }

    public boolean exists(int id) {
        return playerStorage.containsKey(id);
    }

    private void loadFromFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]"); // JSON vazio válido
                }
            }
            try (FileReader reader = new FileReader(file)) {
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

    private void saveToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(playerStorage.values(), writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar jogadores no arquivo: " + e.getMessage());
        }
    }
}
