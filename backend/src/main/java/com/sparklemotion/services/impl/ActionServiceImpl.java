package com.sparklemotion.services.impl;

import com.sparklemotion.config.Error;
import com.sparklemotion.config.exceptions.ResourceNotFoundException;
import com.sparklemotion.repositories.ActionRepository;
import com.sparklemotion.services.ActionService;
import com.sparklemotion.entities.Action;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final Environment environment;


    private final ActionRepository actionRepository;

    @Override
    public List<Action> findAllActions() {
        return (List<Action>) this.actionRepository.findAll();
    }

    @Override
    public Action findActionById(Long id) {
        return this.actionRepository.findById(id).orElse(null);
    }

    @Override
    public Action createAction(Action action) {
        return this.actionRepository.save(action);
    }

    @Override
    public Action updateAction(Long id, Action updatedAction) {
        Optional<Action> optionalAction = actionRepository.findById(id);
        if (optionalAction.isPresent()) {
            Action existingAction = optionalAction.get();
            existingAction.setCommand(updatedAction.getCommand());
            existingAction.setCallback(updatedAction.getCallback());
            return actionRepository.save(existingAction);
        } else {
            log.error(Error.ACTION_NOT_FOUND.getMessage());
            throw new ResourceNotFoundException(Error.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public void deleteActionById(Long id) {
        actionRepository.deleteById(id);
    }
}
