package exercise.controller.action;

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
		}

		return action;
	}
}