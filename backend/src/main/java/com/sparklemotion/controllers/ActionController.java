package com.sparklemotion.controllers;
import com.sparklemotion.entities.Action;
import com.sparklemotion.services.impl.ActionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/actions")
@CrossOrigin
@RequiredArgsConstructor
public class ActionController {
    private final static String USER_ID_ENDPOINT= "/{id}";
    private final ActionServiceImpl actionService;

    @GetMapping
    public ResponseEntity<List<Action>> findAllActions() {
        return new ResponseEntity<>(actionService.findAllActions(), HttpStatus.OK);
    }

    @GetMapping(USER_ID_ENDPOINT)
    public ResponseEntity<Action> findActionById(@PathVariable Long id) {
        Action action = actionService.findActionById(id);
        if (Objects.nonNull(action)) {
            return new ResponseEntity<>(action, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Action> createAction(@RequestBody Action action) {
        return new ResponseEntity<>(actionService.createAction(action), HttpStatus.CREATED);
    }


    @PutMapping(USER_ID_ENDPOINT)
    public ResponseEntity<Action> updateAction(@PathVariable Long id, @RequestBody Action updatedAction) {
        Action action = actionService.updateAction(id, updatedAction);
        if (Objects.nonNull(action)){
            return new ResponseEntity<>(action, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(USER_ID_ENDPOINT)
    public ResponseEntity<Void> deleteActionById(@PathVariable Long id) {
        actionService.deleteActionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}