package com.sparklemotion.mockdata;

import com.sparklemotion.entities.Action;

public class ActionControllerMockData {

    public static Action createActionWithId(Long id, String command, String callback) {
        return new Action(id, null, command, callback);
    }

    public static Action createSampleAction1() {
        return createActionWithId(1L, "Command 1", "Callback 1");
    }

    public static Action createSampleAction2() {

        return createActionWithId(2L, "Command 2", "Callback 2");
    }

}
