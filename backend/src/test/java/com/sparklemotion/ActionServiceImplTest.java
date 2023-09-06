package com.sparklemotion;

import com.sparklemotion.config.exceptions.ResourceNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.mockito.Mockito.*;

@DisplayName("ActionServiceImpl Tests")
public class ActionServiceImplTest {

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

    @Test
    @DisplayName("findActionById() - Existing Action - Should Return Action")
    public void givenExistingActionId_whenFindActionById_thenActionReturned() {

        Long id = 1L;
        Action action = ActionRepositoryMockData.createSampleAction1();
        when(actionRepository.findById(id)).thenReturn(Optional.of(action));

        Action result = actionService.findActionById(id);

        assertNotNull(result);
        assertEquals(action, result);
    }

    @Test
    @DisplayName("findActionById() - Non-Existing Action - Should Return Null")
    public void givenNonExistingActionId_whenFindActionById_thenNullReturned() {
        Long id = 1L;
        when(actionRepository.findById(id)).thenReturn(Optional.empty());

        Action result = actionService.findActionById(id);

        assertNull(result);
    }

    @Test
    @DisplayName("createAction() - Should Return Saved Action")
    public void givenActionToSave_whenCreateAction_thenSavedActionReturned() {

        Action actionToSave = new Action(null, null, "Command", "Callback");
        Action savedAction = ActionRepositoryMockData.createActionWithId(1L, "Command", "Callback");
        when(actionRepository.save(actionToSave)).thenReturn(savedAction);

        Action result = actionService.createAction(actionToSave);

        assertNotNull(result);
        assertEquals(savedAction.getId(), result.getId());
        assertEquals(savedAction.getCommand(), result.getCommand());
        assertEquals(savedAction.getCallback(), result.getCallback());
    }

    @Test
    @DisplayName("updateAction() - Existing Action - Should Return Updated Action")
    public void givenExistingActionAndUpdatedAction_whenUpdateAction_thenUpdatedActionReturned() {

        Long id = 1L;
        Action updatedAction = ActionRepositoryMockData.createActionWithId(id, "Updated Command", "Updated Callback");
        Action existingAction = ActionRepositoryMockData.createSampleAction1();
        when(actionRepository.findById(id)).thenReturn(Optional.of(existingAction));
        when(actionRepository.save(existingAction)).thenReturn(updatedAction);

        Action result = actionService.updateAction(id, updatedAction);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(updatedAction.getCommand(), result.getCommand());
        assertEquals(updatedAction.getCallback(), result.getCallback());
    }

    @Test
    @DisplayName("updateAction() - Non-Existing Action - Should Throw IllegalArgumentException")
    public void givenNonExistingActionId_whenUpdateAction_thenIllegalArgumentExceptionThrown() {

        Long id = 1L;
        Action updatedAction = ActionRepositoryMockData.createActionWithId(id, "Updated Command", "Updated Callback");
        when(actionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> actionService.updateAction(id, updatedAction));
    }

    @Test
    @DisplayName("deleteActionById() - Should Call actionRepository.deleteById() Once")
    public void givenActionId_whenDeleteActionById_thenActionDeleted() {

        Long id= 1L;

        actionService.deleteActionById(id);

        verify(actionRepository, times(1)).deleteById(id);
    }
}
