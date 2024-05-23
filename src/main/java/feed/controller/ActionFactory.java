package feed.controller;

import feed.controller.action.FeedCreateAction;
import feed.controller.action.FeedDeleteAction;
import feed.controller.action.FeedReadAction;
import feed.controller.action.FeedUpdateAction;

public class ActionFactory {
private ActionFactory() {
		
	}
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		if(command.equals("feedRead")) {
			action = new FeedReadAction();
		}else if (command.equals("feedCreate")) {
			action = new FeedCreateAction();
		}else if (command.equals("feedUpdate")) {
			action = new FeedUpdateAction();
		}else if (command.equals("feedDelete")) {
			action = new FeedDeleteAction();
		}
	
		
		return action;
	}
}
