package com.bugred.API.dto;

import com.bugred.API.dto.request.CharacterRequestDTO;
import com.bugred.API.dto.request.PlayerRequestRequestDTO;
import com.bugred.API.dto.request.StatusRequestDTO;
import com.bugred.API.model.Player;
import com.bugred.API.model.Character;
import com.bugred.API.model.Status;

import java.util.List;
import java.util.stream.Collectors;

public class DTOFactory {

    /**
     * Converte uma entidade para o DTO correspondente.
     */
    public static <T> Object toDTO(T entity) {
        if (entity instanceof Player p) return fromPlayer(p);
        if (entity instanceof Character c) return fromCharacter(c);
        if (entity instanceof Status s) return fromStatus(s);
        throw new IllegalArgumentException("Tipo não suportado para conversão para DTO: " + entity.getClass());
    }

    /**
     * Converte um DTO para a entidade correspondente.
     */
    public static <T> T fromDTO(Object dto, Class<T> clazz) {
        if (dto instanceof PlayerRequestRequestDTO pd && clazz == Player.class) return clazz.cast(toPlayer(pd));
        if (dto instanceof CharacterRequestDTO cd && clazz == Character.class) return clazz.cast(toCharacter(cd));
        if (dto instanceof StatusRequestDTO sd && clazz == Status.class) return clazz.cast(toStatus(sd));
        throw new IllegalArgumentException("Tipo não suportado para conversão de DTO: " + dto.getClass());
    }

    /**
     * Converte um objeto Player para PlayerDTO, incluindo ID e personagens.
     */
    public static PlayerRequestRequestDTO fromPlayer(Player p) {
        List<CharacterRequestDTO> chars = p.getCharacters().stream()
                .map(DTOFactory::fromCharacter)
                .collect(Collectors.toList());

        // Method Chain
        return new PlayerRequestRequestDTO(p.getPlayerName())
                .setListCharacter(chars);
    }

    /**
     * Converte um objeto Character para CharacterDTO, incluindo status.
     */
    public static CharacterRequestDTO fromCharacter(Character c) {
        StatusRequestDTO statusRequestDto = fromStatus(c.getStatus());
        return new CharacterRequestDTO(
                c.getName(),
                c.getDescription(),
                statusRequestDto,
                c.getTypeClass(),
                c.getLevel()
        );
    }

    /**
     * Converte um objeto Status para StatusDTO.
     */
    public static StatusRequestDTO fromStatus(Status s) {
        return new StatusRequestDTO(
                s.getStrength(),
                s.getDexterity(),
                s.getConstitution(),
                s.getIntelligence(),
                s.getWisdom(),
                s.getCharisma()
        );
    }

    /**
     * Converte um PlayerDTO para Player, personagens incluídos.
     * O ID será tratado como 0 (ou ajustado posteriormente no serviço).
     */
    public static Player toPlayer(PlayerRequestRequestDTO dto) {
        List<Character> chars = dto.getListCharacter().stream()
                .map(DTOFactory::toCharacter)
                .collect(Collectors.toList());

        return new Player(dto.getPlayerName(), chars); // Respeita o ID do DTO
    }

    /**
     * Converte um CharacterDTO para Character, incluindo status.
     */
    public static Character toCharacter(CharacterRequestDTO dto) {
        Status status = toStatus(dto.getStatus());
        return new Character(
                dto.getName(),
                dto.getDescription(),
                status,
                dto.getTypeClass(),
                dto.getlevel()
        );
    }

    /**
     * Converte um StatusDTO para Status.
     */
    public static Status toStatus(StatusRequestDTO dto) {
        return new Status()
                .setStrength(dto.getStrength())
                .setDexterity(dto.getDexterity())
                .setConstitution(dto.getConstitution())
                .setIntelligence(dto.getIntelligence())
                .setWisdom(dto.getWisdom())
                .setCharisma(dto.getCharisma());
    }

    /**
     * Atualiza um Status existente com os valores do DTO.
     */
    public static Status updateStatus(Status status, StatusRequestDTO dto) {
        return status
                .setStrength(dto.getStrength())
                .setDexterity(dto.getDexterity())
                .setConstitution(dto.getConstitution())
                .setIntelligence(dto.getIntelligence())
                .setWisdom(dto.getWisdom())
                .setCharisma(dto.getCharisma());
    }

    /**
     * Converte diretamente um Character em CharacterDTO (atalho).
     */
    public static CharacterRequestDTO toCharacterDTO(Character c) {
        return fromCharacter(c);
    }

    /**
     * Converte diretamente um Status em StatusDTO (atalho).
     */
    public static StatusRequestDTO toStatusDTO(Status s) {
        return fromStatus(s);
    }
}
