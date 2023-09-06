package com.sparklemotion;

import com.sparklemotion.mockdata.ActionControllerMockData;
import com.sparklemotion.controllers.ActionController;
import com.sparklemotion.entities.Action;
import com.sparklemotion.services.impl.ActionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;


public class ActionControllerTest {

    @Mock
    private ActionServiceImpl actionService;

    @InjectMocks
    private ActionController actionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final Action sampleAction1 = ActionControllerMockData.createSampleAction1();
    private final Action sampleAction2 = ActionControllerMockData.createSampleAction2();

    @Test
    public void givenNewAction_whenCreateAction_thenCreatedStatusAndActionReturned() {

        Action action = new Action(null, null, "Command", "Callback");
        when(actionService.createAction(any(Action.class))).thenReturn(sampleAction1);

        ResponseEntity<Action> responseEntity;
        responseEntity = actionController.createAction(action);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(sampleAction1, responseEntity.getBody());
    }

    @Test
    public void givenUpdatedAction_whenUpdateAction_thenOkStatusAndUpdatedActionReturned() {

        Long id = 1L;
        Action updatedAction = new Action(id, null, "Updated Command", "Updated Callback");
        when(actionService.updateAction(anyLong(), any(Action.class))).thenReturn(updatedAction);

        ResponseEntity<Action> responseEntity = actionController.updateAction(id, updatedAction);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedAction, responseEntity.getBody());
        verify(actionService, times(1)).updateAction(id, updatedAction);
    }

    @Test
    public void givenUnknownId_whenUpdateAction_thenNotFoundStatusAndNullReturned() {

        Long id = 100L;
        Action updatedAction = new Action(id, null, "Updated Command", "Updated Callback");
        when(actionService.updateAction(id, updatedAction)).thenReturn(null);

        ResponseEntity<Action> responseEntity = actionController.updateAction(id, updatedAction);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void givenActionId_whenDeleteActionById_thenNoContentStatusReturned() {

        Long id = 1L;
        doNothing().when(actionService).deleteActionById(id);

        ResponseEntity<Void> responseEntity = actionController.deleteActionById(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void whenFindAllActions_thenOkStatusAndListOfActionsReturned() {

        List<Action> actions = new ArrayList<>();
        actions.add(sampleAction1);
        actions.add(sampleAction2);
        when(actionService.findAllActions()).thenReturn(actions);


        ResponseEntity<List<Action>> responseEntity = actionController.findAllActions();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(actions, responseEntity.getBody());
    }
}
