package com.sparklemotion.mockdata;
import com.sparklemotion.entities.Action;
import java.util.ArrayList;
import java.util.List;

public class ActionRepositoryMockData {

    public static Action createActionWithId(Long id, String command, String callback) {
        return new Action(id, null, command, callback);
    }

    public static Action createSampleAction1() {
        return createActionWithId(1L, "Command 1", "Callback 1");
    }

    public static Action createSampleAction2() {

        return createActionWithId(2L, "Command 2", "Callback 2");
    }

    public static List<Action> createSampleActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(createSampleAction1());
        actions.add(createSampleAction2());
        return actions;
    }
}
