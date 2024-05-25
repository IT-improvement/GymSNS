package exerciseCategory.controller;

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
			case "add":
				action = new ExerciseCategoryAddAction();
				break;
			case "delete":
				action = new ExerciseCategoryDeleteAction();
				break;
		}

		return action;
	}
}