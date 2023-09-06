package com.sparklemotion.services;

import com.sparklemotion.entities.Action;

import java.util.List;

public interface ActionService {

    List<Action> findAllActions();
    Action createAction(Action action);
    Action updateAction(Long id, Action action);
    Action findActionById(Long id);
    void deleteActionById(Long id);

}
