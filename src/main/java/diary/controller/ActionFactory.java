package diary.controller;


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
			action = new DiaryReadAction();
		} else if (command.equals("write")) {
		} else if (command.equals("update")) {
		} else if (command.equals("delete")) {
		}

		return action;
	}

}
