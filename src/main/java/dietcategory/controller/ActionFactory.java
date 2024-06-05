package dietcategory.controller;

import dietcategory.controller.action.DietCategoryReadAllAction;

public class ActionFactory {
    private static ActionFactory instance = new ActionFactory();

    private ActionFactory(){}

    public static ActionFactory getInstance(){return instance;}

    public Action getAction(String command){
        Action action = null;

        switch (command){
            case "read_all":
                action = new DietCategoryReadAllAction();
                break;
        }
        return action;
    }
}
