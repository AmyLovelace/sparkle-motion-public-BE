package com.sparklemotion;

import com.sparklemotion.mockdata.ActionRepositoryMockData;
import com.sparklemotion.entities.Action;
import com.sparklemotion.repositories.ActionRepository;
import com.sparklemotion.services.impl.ActionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ActionRepositoryTest {

    @Mock
    private ActionRepository actionRepository;

    @InjectMocks
    private ActionServiceImpl actionService;

    @Test
    public void givenSampleActions_whenFindAllActions_thenListOfActionsReturned() {

        List<Action> expectedActions = ActionRepositoryMockData.createSampleActions();
        when(actionRepository.findAll()).thenReturn(expectedActions);

        List<Action> actualActions = actionService.findAllActions();

        assertEquals(expectedActions, actualActions);
        verify(actionRepository, times(1)).findAll();
    }

    @Test
    public void givenExistingActionId_whenFindActionById_thenActionReturned() {

        Long id = 1L;
        Action expectedAction = ActionRepositoryMockData.createSampleAction1();
        when(actionRepository.findById(id)).thenReturn(Optional.of(expectedAction));

        Optional<Action> actualActionOptional = actionRepository.findById(id);

        assertTrue(actualActionOptional.isPresent());
        assertEquals(expectedAction, actualActionOptional.get());
        verify(actionRepository, times(1)).findById(id);
    }

    @Test
    public void givenNonExistingActionId_whenFindActionById_thenEmptyOptionalReturned() {

        Long id = 100L;
        when(actionRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Action> actualActionOptional = actionRepository.findById(id);

        assertFalse(actualActionOptional.isPresent());
        verify(actionRepository, times(1)).findById(id);
    }
}
