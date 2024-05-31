package routine.controller;

import routine.controller.action.RoutineCreateAction;
import routine.controller.action.RoutineReadAction;

public class ActionFactory {

	private ActionFactory() {

	}

	private static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;

		if (command.equals("read")) {
			action = new RoutineReadAction();
		} else if (command.equals("write")) {
			action = new RoutineCreateAction();
		} else if (command.equals("update")) {
		} else if (command.equals("delete")) {
		}

		return action;
	}

}
