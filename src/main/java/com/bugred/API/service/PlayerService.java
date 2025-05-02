package com.bugred.API.service;

import com.bugred.API.model.Player;
import com.bugred.API.repository.PlayerRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Serviço responsável pela lógica de negócio relacionada a jogadores.
 * Implementa singleton para garantir consistência em tempo de execução.
 */
public class PlayerService {

    private static PlayerService instance;

    // Sempre que modificar os parametros de player excluir a pasta data e recriar o projeto
    private final PlayerRepository repository = new PlayerRepository("src/main/resources/data/players.json");

    /** Inicializa o ID baseado no maior ID já existente nos dados persistidos*/
    private PlayerService() {
        List<Player> players = repository.findAll();
    }

    /** Retorna a instância singleton*/
    public static PlayerService getInstance() {
        if (instance == null) {
            instance = new PlayerService();
        }
        return instance;
    }

    /** Retorna todos os jogadores */
    public List<Player> findAll() {
        return repository.findAll();
    }

    /** Busca jogador por ID */
    public Player findById(UUID id) {
        return repository.findById(id);
    }

    /** Cria novo jogador, gerando ID automaticamente */
    public Player create(Player player) {
        UUID id = UUID.randomUUID();
        player.setId(id);
        repository.save(player);
        return player;
    }

    /** Atualiza um jogador existente */
    public Player update(UUID id, Player updatedPlayer) {
        if (!repository.exists(id)) return null;
        updatedPlayer.setId(id);
        repository.save(updatedPlayer);
        return updatedPlayer;
    }

    /** Remove jogador pelo ID */
    public boolean delete(UUID id) {
        return repository.delete(id);
    }

    /**
     * Adiciona um jogador manualmente — usado para carregar dados mockados.
     * Se o jogador já tiver um ID definido, o ID é preservado.
     */
    public void addPlayer(Player player) {
        if (player.getId() == null) {
            UUID id = UUID.randomUUID();
            player.setId(id);
        }
        repository.save(player);
    }
}
