package diary.controller;

import diary.controller.action.DiaryDeleteAction;
import diary.controller.action.DiaryReadAction;
import diary.controller.action.DiaryReadMonthAction;
import diary.controller.action.DiaryUpdateAction;
import diary.controller.action.DiaryWriteAction;

public class ActionFactory {

	private ActionFactory() {

	}

	private static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;

		if (command.equals("readDay")) {
			action = new DiaryReadAction();
		} else if (command.equals("readMonth")) {
			action = new DiaryReadMonthAction();
		} else if (command.equals("write")) {
			action = new DiaryWriteAction();
		} else if (command.equals("update")) {
			action = new DiaryUpdateAction();
		} else if (command.equals("delete")) {
			action = new DiaryDeleteAction();
		}

		return action;
	}

}
