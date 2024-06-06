package routineDiet.controller;

import routineDiet.controller.action.RoutineDietCreateAction;
import routineDiet.controller.action.RoutineDietDeleteAction;
import routineDiet.controller.action.RoutineDietReadAction;
import routineDiet.controller.action.RoutineDietUpdateAction;

public class ActionFactory {

    private ActionFactory() {

    }

    private static ActionFactory instance = new ActionFactory();

    public static ActionFactory getInstance() {return instance;}

    public Action getAction(String command){
        Action action = null;

        if(command.equals("read"))
            action = new RoutineDietReadAction();
        else if(command.equals("write"))
            action = new RoutineDietCreateAction();
        else if(command.equals("delete"))
            action = new RoutineDietDeleteAction();
        else if(command.equals("update"))
            action = new RoutineDietUpdateAction();

        return action;
    }
}
