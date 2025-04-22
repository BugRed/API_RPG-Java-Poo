package com.bugred.API.repository;

import com.bugred.API.model.Player;

import java.util.*;

// Criando um repositorio para que o sistema possa ser escalado futuramente
// Vai facilitar quando colocar no banco
public class PlayerRepository {

    // Criando a instancia que será usada
    // Todos o Repository vai funcionar como um banco de dados usando MAP para executar funções
    private final Map<Integer, Player> playerStorage = new HashMap<>();


    // Funções basicas de CRUD
    public List<Player> findAll() {
        return new ArrayList<>(playerStorage.values());
    }

    public Player findById(int id) {
        return playerStorage.get(id);
    }

    public void save(Player player) {
        playerStorage.put(player.getId(), player);
    }

    public boolean delete(int id) {
        return playerStorage.remove(id) != null;
    }

    public boolean exists(int id) {
        return playerStorage.containsKey(id);
    }

    public Map<Integer, Player> getStorage() {
        return playerStorage;
    }
}
