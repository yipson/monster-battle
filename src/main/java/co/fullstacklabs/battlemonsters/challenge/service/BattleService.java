package co.fullstacklabs.battlemonsters.challenge.service;

import java.util.List;

import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.dto.request.CreateFightDTO;

/**
 * @author FullStack Labs
 * @version 1.0
 * @since 2022-10
 */
public interface BattleService {
    
    List<BattleDTO> getAll();

    BattleDTO createBattle(CreateFightDTO createFightDTO);

    void deleteBattle(int battleId);
}
