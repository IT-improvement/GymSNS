package exercise.controller;

import exercise.controller.action.*;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() { }
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		switch (command) {
			case "read_all":
				action = new ExerciseReadAllAction();
				break;
			case "read_all_by_query":
				action = new ExerciseReadAllByQueryAction();
				break;
			case "read_one":
				action = new ExerciseReadOneAction();
				break;
			case "create":
				action = new ExerciseCreateAction();
				break;
			case "delete":
				action = new ExerciseDeleteAction();
				break;
			case "update":
				action = new ExerciseUpdateAction();
				break;
			case "read_userCode":
				action = new ExserciseReadByUserCode();
				break;
		}

		return action;
	}
}