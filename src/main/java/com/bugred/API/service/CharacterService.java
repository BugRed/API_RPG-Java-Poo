package com.bugred.API.service;

import com.bugred.API.dto.CharacterDTO;
import com.bugred.API.dto.DTOFactory;
import com.bugred.API.dto.StatusDTO;
import com.bugred.API.model.Character;
import com.bugred.API.model.Player;
import com.bugred.API.model.Status;
import com.bugred.API.repository.CharacterRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CharacterService {

    // Instância Singleton
    private static CharacterService instance;

    // Repositórios necessários
    private final CharacterRepository repository = new CharacterRepository("data/characters.json");
    private final PlayerService playerService = PlayerService.getInstance();

    // Gerador de ID para personagens
    private static final AtomicInteger nextCharacterId = new AtomicInteger(1);

    private CharacterService() {}

    public static CharacterService getInstance() {
        if (instance == null) {
            instance = new CharacterService();
        }
        return instance;
    }

    // Retorna todos os personagens associados a um jogador específico
    public List<Character> findAll(Player player) {
        // Delegando a busca para o repositório, usando o ID do jogador
        return repository.findAll(player.getId());
    }


    // Busca um personagem específico
    public Character findById(int playerId, int characterId) {
        return repository.findById(playerId, characterId);
    }

    // Cria e adiciona personagem ao jogador
    public Character create(int playerId, CharacterDTO dto) {
        Player player = playerService.findById(playerId);
        if (player == null) return null;

        Character character = DTOFactory.toCharacter(dto);
        character.setId(nextCharacterId.getAndIncrement());

        repository.save(playerId, character);
        return character;
    }

    // Atualiza personagem existente
    public Character update(int playerId, int characterId, CharacterDTO dto) {
        Character character = findById(playerId, characterId);
        if (character == null) return null;

        character.setName(dto.getName());
        character.setDescription(dto.getDescription());
        character.setTypeClass(dto.getTypeClass());
        character.setLevel(dto.getlevel());

        Status status = character.getStatus();
        DTOFactory.updateStatus(status, dto.getStatus());

        repository.save(playerId, character);
        return character;
    }

    // Remove personagem
    public boolean delete(int playerId, int characterId) {
        return repository.delete(playerId, characterId);
    }

    // Utilitário para inserção direta (ex: mock)
    public void addCharacter(int playerId, Character character) {
        character.setId(nextCharacterId.getAndIncrement());
        repository.save(playerId, character);
    }

    public Status updateStatus(int playerId, int characterId, StatusDTO dto) {
        Character character = findById(playerId, characterId);
        if (character == null) return null;

        Status status = character.getStatus();
        DTOFactory.updateStatus(status, dto);
        repository.save(playerId, character);
        return status;
    }

    // Gera o próximo ID disponível para o jogador
    private int getNextCharacterId(int playerId) {
        List<Character> characters = repository.findAll(playerId);
        return characters.stream()
                .mapToInt(Character::getId)
                .max()
                .orElse(0) + 1;
    }


    // Salva ou atualiza um personagem de um jogador
    public void save(int playerId, Character character) {
        if (character == null) return;

        // Se o personagem não tiver ID definido (ex: vindo do JSON com id = 0), gera um novo ID único
        if (character.getId() == 0) {
            int nextId = getNextCharacterId(playerId);
            character.setId(nextId);
        }

        repository.save(playerId, character);
    }


    public void saveStatus(int playerId, int characterId, Status status) {
        Character character = findById(playerId, characterId);
        if (character != null) {
            character.setStatus(status); // Substitui (ou define) o status único do personagem
            save(playerId, character);   // Re-salva o personagem com o novo status
        } else {
            System.err.println("⚠️ Personagem com ID " + characterId + " não encontrado para o jogador " + playerId);
        }
    }

}
