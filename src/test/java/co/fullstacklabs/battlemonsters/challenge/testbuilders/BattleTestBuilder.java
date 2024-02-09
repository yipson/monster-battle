package co.fullstacklabs.battlemonsters.challenge.testbuilders;

import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import co.fullstacklabs.battlemonsters.challenge.model.Battle;
import co.fullstacklabs.battlemonsters.challenge.model.Monster;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FullStack Labs
 * @version 1.0
 * @since 2022-10
 */
public class BattleTestBuilder {

    @Builder
    public static Battle battle(Integer id, Monster monsterA, Monster monsterB, Monster monsterWinner) {
        Battle battle = new Battle();
        battle.setId(id);
        battle.setMonsterA(monsterA);
        battle.setMonsterB(monsterB);
        battle.setWinner(monsterWinner);
        return battle;
    }

    public static List<BattleDTO> battleDtoList() {
        List<BattleDTO> battleDTOList = new ArrayList<>();
        battleDTOList.add(battleDTO());
        battleDTOList.add(battleDTO());
        return battleDTOList;
    }

    public static BattleDTO battleDTO() {
        return BattleDTO.builder()
                .id(1)
                .monsterA(monsterDTO(1))
                .monsterB(monsterDTO(2))
                .winner(monsterDTO(1))
                .build();
    }

    public static MonsterDTO monsterDTO(Integer id) {
        return MonsterDTO.builder()
                .id(1)
                .hp(40)
                .attack(70)
                .defense(50)
                .imageUrl("image.jpg")
                .name("Monster")
                .speed(60)
                .build();
    }

    public static class BattleBuilder {
        private Integer id = 1;
        private Monster monsterA = MonsterTestBuilder.builder().id(1).build();
        private Monster monsterB = MonsterTestBuilder.builder().id(2).build();
        private Monster monsterWinner = monsterA;        


    }
}
