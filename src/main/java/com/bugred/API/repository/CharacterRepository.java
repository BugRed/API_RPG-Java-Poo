package com.bugred.API.repository;

import com.bugred.API.model.Character;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Repositório responsável por armazenar e recuperar os personagens (Character)
 * de cada jogador (Player), agrupados por playerId.
 */
public class CharacterRepository {

    // Mapa onde a chave é o ID do jogador e o valor é a lista de personagens dele
    private final Map<UUID, List<Character>> characterStorage = new HashMap<>();

    private final String filePath;
    private final Gson gson = new Gson();

    public CharacterRepository(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    /**
     * Retorna todos os personagens de um jogador específico
     */
    public List<Character> findAll(UUID playerId) {
        return characterStorage.getOrDefault(playerId, new ArrayList<>());
    }

    /**
     * Retorna um personagem específico de um jogador pelo ID
     */
    public Character findById(UUID playerId, UUID characterId) {
        return findAll(playerId).stream()
                .filter(c -> c.getId().equals(characterId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Salva ou atualiza um personagem de um jogador
     */
    public void save(UUID playerId, Character character) {
        List<Character> characters = characterStorage.computeIfAbsent(playerId, k -> new ArrayList<>());
        // Remove o personagem existente com o mesmo ID (caso esteja atualizando)
        characters.removeIf(c -> c.getId().equals(character.getId()));
        characters.add(character);
        saveToFile();
    }

    /**
     * Remove um personagem de um jogador
     */
    public boolean delete(UUID playerId, UUID characterId) {
        List<Character> characters = characterStorage.get(playerId);
        if (characters == null) return false;

        boolean removed = characters.removeIf(c -> c.getId().equals(characterId));
        if (removed) saveToFile();
        return removed;
    }

    /**
     * Verifica se um personagem existe para determinado jogador
     */
    public boolean exists(UUID playerId, UUID characterId) {
        return findById(playerId, characterId) != null;
    }

    /**
     * Carrega os dados dos personagens a partir de um arquivo JSON
     */
    private void loadFromFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                // Cria diretórios e arquivo se não existirem
                file.getParentFile().mkdirs();
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("{}"); // JSON inicial vazio
                }
            }

            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<Map<UUID, List<Character>>>() {}.getType();
                Map<UUID, List<Character>> data = gson.fromJson(reader, type);
                if (data != null) {
                    characterStorage.clear();
                    characterStorage.putAll(data);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Erro ao carregar personagens do arquivo: " + e.getMessage());
        }
    }

    /**
     * Salva o estado atual dos personagens no arquivo JSON
     */
    private void saveToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(characterStorage, writer);
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar personagens no arquivo: " + e.getMessage());
        }
    }
}
