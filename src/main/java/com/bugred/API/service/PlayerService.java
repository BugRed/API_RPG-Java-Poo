package com.bugred.API.service;

import com.bugred.API.model.Player;
import com.bugred.API.repository.PlayerRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Serviço responsável pela lógica de negócio relacionada a jogadores.
 * Implementa singleton para garantir consistência em tempo de execução.
 */
public class PlayerService {

    private static PlayerService instance;

    private final PlayerRepository repository = new PlayerRepository("data/players.json");

    // Usado para gerar IDs únicos automaticamente
    private static final AtomicInteger nextPlayerId = new AtomicInteger(1);

    private PlayerService() {
        // Inicializa o ID baseado no maior ID já existente nos dados persistidos
        List<Player> players = repository.findAll();
        if (!players.isEmpty()) {
            int maxId = players.stream().mapToInt(Player::getId).max().orElse(0);
            nextPlayerId.set(maxId + 1);
        }
    }

    // Retorna a instância singleton
    public static PlayerService getInstance() {
        if (instance == null) {
            instance = new PlayerService();
        }
        return instance;
    }

    // Retorna todos os jogadores
    public List<Player> findAll() {
        return repository.findAll();
    }

    // Busca jogador por ID
    public Player findById(int id) {
        return repository.findById(id);
    }

    // Cria novo jogador, gerando ID automaticamente
    public Player create(Player player) {
        int id = nextPlayerId.getAndIncrement();
        player.setId(id);
        repository.save(player);
        return player;
    }

    // Atualiza um jogador existente
    public Player update(int id, Player updatedPlayer) {
        if (!repository.exists(id)) return null;
        updatedPlayer.setId(id);
        repository.save(updatedPlayer);
        return updatedPlayer;
    }

    // Remove jogador pelo ID
    public boolean delete(int id) {
        return repository.delete(id);
    }

    /**
     * Adiciona um jogador manualmente — usado para carregar dados mockados.
     * Se o jogador já tiver um ID definido, o ID é preservado.
     */
    public void addPlayer(Player player) {
        if (player.getId() == 0) {
            int id = nextPlayerId.getAndIncrement();
            player.setId(id);
        } else {
            // Garante que o próximo ID gerado será maior que o atual
            nextPlayerId.updateAndGet(current -> Math.max(current, player.getId() + 1));
        }
        repository.save(player);
    }
}
