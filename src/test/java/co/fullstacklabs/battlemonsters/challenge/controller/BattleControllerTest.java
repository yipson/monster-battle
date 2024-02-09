package co.fullstacklabs.battlemonsters.challenge.controller;

import co.fullstacklabs.battlemonsters.challenge.ApplicationConfig;
import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.service.BattleService;
import co.fullstacklabs.battlemonsters.challenge.service.impl.BattleServiceImpl;
import co.fullstacklabs.battlemonsters.challenge.testbuilders.BattleTestBuilder;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static co.fullstacklabs.battlemonsters.challenge.utils.ConstantPath.BATTLE_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author FullStack Labs
 * @version 1.0
 * @since 2022-10
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class BattleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BattleService battleService;

//    @InjectMocks
//    private BattleServiceImpl battleServiceImpl;

    @InjectMocks
    private BattleController battleController;
    

    @Test
    void shouldFetchAllBattles() throws Exception {
        Mockito.when(battleService.getAll()).thenReturn(BattleTestBuilder.battleDtoList());

        mockMvc.perform(get(BATTLE_PATH)).andExpect(status().isOk());

        verify(battleService, times(1)).getAll();
    }
    
    @Test
    void shouldFailBattleWithInexistentMonster() {
        //TODO: Implement
        assertEquals(1, 1);
    }


    @Test
    void shouldInsertBattleWithMonsterBWinning() {
        //TODO: Implement
        assertEquals(1, 1);
    }

    @Test
    void shouldDeleteBattleSucessfully() {
        //TODO: Implement
        assertEquals(1, 1);
    }

    @Test
    void shouldFailDeletingInexistentBattle() {
        //TODO: Implement
        assertEquals(1, 1);
    }
}