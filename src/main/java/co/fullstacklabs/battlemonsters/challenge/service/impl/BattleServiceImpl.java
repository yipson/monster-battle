package co.fullstacklabs.battlemonsters.challenge.service.impl;

import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import co.fullstacklabs.battlemonsters.challenge.dto.request.CreateFightDTO;
import co.fullstacklabs.battlemonsters.challenge.exceptions.ResourceNotFoundException;
import co.fullstacklabs.battlemonsters.challenge.model.Battle;
import co.fullstacklabs.battlemonsters.challenge.repository.BattleRepository;
import co.fullstacklabs.battlemonsters.challenge.service.BattleService;
import co.fullstacklabs.battlemonsters.challenge.service.MonsterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author FullStack Labs
 * @version 1.0
 * @since 2022-10
 */
@Service
public class BattleServiceImpl implements BattleService {

    private final BattleRepository battleRepository;
    private final MonsterService monsterService;
    private final ModelMapper modelMapper;


    public BattleServiceImpl(BattleRepository battleRepository, ModelMapper modelMapper,
                             MonsterService monsterService) {
        this.battleRepository = battleRepository;
        this.monsterService = monsterService;
        this.modelMapper = modelMapper;    
    }

    /**
     * List all existence battles
     */
    @Override
    public List<BattleDTO> getAll() {
        List<Battle> battles = battleRepository.findAll();
        return battles.stream().map(battle -> modelMapper.map(battle, BattleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BattleDTO createBattle(CreateFightDTO createFightDTO) {
        MonsterDTO monsterA = monsterService.findById(createFightDTO.getMonsterAId());
        MonsterDTO monsterB = monsterService.findById(createFightDTO.getMonsterBId());

        MonsterDTO winner = fightBattle(monsterA, monsterB);
        if(Objects.equals(winner.getId(), monsterA.getId())) {
            return saveBattleWinnerA(monsterA, monsterB);
        }
        return saveBattleWinnerB(monsterB, monsterA);
    }

    private MonsterDTO fightBattle(MonsterDTO monsterA, MonsterDTO monsterB) {
        return defineWhoStarts(monsterA, monsterB);
    }

    private MonsterDTO defineWhoStarts(MonsterDTO monsterA, MonsterDTO monsterB) {
        Integer speedA = monsterA.getSpeed();
        Integer speedB = monsterB.getSpeed();

        if(Objects.equals(speedA, speedB)) {
            if(monsterA.getAttack() > monsterB.getAttack()) {
                return startMonsterA(monsterA, monsterB);
            }
            return startMonsterB(monsterB, monsterA);
        }

        if(speedA > speedB) {
            return startMonsterA(monsterA, monsterB);
        }
        return startMonsterB(monsterB, monsterA);
    }

    private MonsterDTO startMonsterA(MonsterDTO monsterAttack, MonsterDTO monsterDefense){
        return turnToAttack(monsterAttack, monsterDefense);
    }

    private MonsterDTO startMonsterB(MonsterDTO monsterAttack, MonsterDTO monsterDefense){
        return turnToAttack(monsterAttack, monsterDefense);
    }


    private MonsterDTO turnToAttack(MonsterDTO monsterAttack, MonsterDTO monsterDefense) {
        Integer damage = calculateDamage(monsterAttack, monsterDefense);
        calculateHitPoints(monsterDefense, damage);
        if(monsterDefense.getHp() > 0) {
            turnToAttack(monsterDefense, monsterAttack);
        }
        return monsterAttack;
    }


    private Integer calculateDamage(MonsterDTO monsterA, MonsterDTO monsterB) {
        if(monsterA.getAttack() <= monsterB.getDefense()) {
            return 1;
        }
        return monsterA.getAttack() - monsterB.getDefense();
    }

    private void calculateHitPoints(MonsterDTO monsterDefense, Integer damage) {
        monsterDefense.setHp(monsterDefense.getHp() - damage);
    }

    private BattleDTO saveBattleWinnerA(MonsterDTO winner, MonsterDTO loser) {
        return saveBattle(winner, loser);
    }

    private BattleDTO saveBattleWinnerB(MonsterDTO winner, MonsterDTO loser) {
        return saveBattle(winner, loser);
    }

    private BattleDTO saveBattle(MonsterDTO winner, MonsterDTO loser) {
        BattleDTO battleDTO = buildBattleDTO(winner, loser);
        Battle battle = modelMapper.map(battleDTO, Battle.class);
        Integer battleId = battleRepository.save(battle).getId();
        battleDTO.setId(battleId);
        return battleDTO;
    }

    private BattleDTO buildBattleDTO(MonsterDTO winner, MonsterDTO loser) {
        return BattleDTO.builder()
                .monsterA(winner)
                .monsterB(loser)
                .winner(winner)
                .build();
    }

    @Override
    public void deleteBattle(int battleId) {
        Battle battle = findBattleById(battleId);
        battleRepository.delete(battle);
    }

    private Battle findBattleById(int battleId) {
        return battleRepository.findById(battleId)
                .orElseThrow(() -> new ResourceNotFoundException("Battle not found"));
    }
}
