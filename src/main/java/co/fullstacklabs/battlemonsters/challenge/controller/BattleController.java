package co.fullstacklabs.battlemonsters.challenge.controller;

import java.util.List;

import co.fullstacklabs.battlemonsters.challenge.dto.request.CreateFightDTO;
import co.fullstacklabs.battlemonsters.challenge.service.impl.BattleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.service.BattleService;

import static co.fullstacklabs.battlemonsters.challenge.utils.ConstantPath.BATTLE_PATH;

/**
 * @author FullStack Labs
 * @version 1.0
 * @since 2022-10
 */
@RestController
@RequestMapping(BATTLE_PATH)
public class BattleController {

    private BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @GetMapping
    public List<BattleDTO> getAll() {
        return battleService.getAll();
    }

    @PostMapping()
    public BattleDTO createBattle(@RequestBody CreateFightDTO createFightDTO) {
        return battleService.createBattle(createFightDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBattle(@PathVariable("id") int battleId) {
        battleService.deleteBattle(battleId);
    }
    
}
