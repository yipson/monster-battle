package co.fullstacklabs.battlemonsters.challenge.service;

import java.io.InputStream;
import java.util.List;

import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import co.fullstacklabs.battlemonsters.challenge.model.Monster;

/**
 * @author FullStack Labs
 * @version 1.0
 * @since 2022-10
 */
public interface MonsterService {
    
    MonsterDTO create(MonsterDTO monsterDTO);

    List<MonsterDTO> getAllMonsters();

    MonsterDTO findById(int id);

    Monster findMonsterById(int id);

    MonsterDTO update(MonsterDTO monsterDTO);

    void delete(Integer id);

    void importFromInputStream(InputStream inputStream);


}
