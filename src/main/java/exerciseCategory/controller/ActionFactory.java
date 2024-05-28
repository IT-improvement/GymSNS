package exerciseCategory.controller;

import exerciseCategory.controller.action.*;

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
				action = new ExerciseCategoryReadAllAction();
				break;
//			case "create":
//				action = new ExerciseCategoryCreateAction();
//				break;
//			case "delete":
//				action = new ExerciseCategoryDeleteAction();
//				break;
//			case "update":
//				action = new ExerciseCategoryUpdateAction();
//				break;
		}

		return action;
	}
}