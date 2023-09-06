package com.sparklemotion;

import com.sparklemotion.mockdata.ActionRepositoryMockData;
import com.sparklemotion.entities.Action;
import com.sparklemotion.repositories.ActionRepository;
import com.sparklemotion.services.impl.ActionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("ActionServiceImpl Tests")
public class ActionServiceTest {

    @Mock
    private ActionRepository actionRepository;

    @InjectMocks
    private ActionServiceImpl actionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAllActions() - Should Return List of Actions")
    public void givenSampleActions_whenFindAllActions_thenListOfActionsReturned() {

        List<Action> actions = ActionRepositoryMockData.createSampleActions();
        when(actionRepository.findAll()).thenReturn(actions);

        List<Action> result = actionService.findAllActions();

        assertEquals(actions.size(), result.size());
        assertEquals(actions, result);
    }
}
