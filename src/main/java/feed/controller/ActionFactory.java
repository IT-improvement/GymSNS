package feed.controller;

import feed.controller.action.*;

public class ActionFactory {
private ActionFactory() {
		
	}
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		System.out.println(command);
		if(command.equals("feedRead")) {
			action = new FeedReadAction();
		}else if (command.equals("feedCreate")) {
			action = new FeedCreateAction();
		}else if (command.equals("feedUpdate")) {
			action = new FeedUpdateAction();
		}else if (command.equals("feedDelete")) {
			action = new FeedDeleteAction();
		}else if (command.equals("feedDetail")) {
			action = new FeedDetailAction();
		}else if (command.equals("feedCountFavorite")) {
			action = new FeedFavoriteReadAction();
		}else if (command.equals("feedFavoritePlus")) {
			action = new FeedFavoriteCreateAction();
		}else if (command.equals("feedFavoriteCheck")) {
			action = new FeedFavoriteCheckAction();
		}else if (command.equals("feedFavoriteDelete")) {
			action = new FeedFavoriteDeleteAction();
		}else if (command.equals("feedCommentReadAction")) {
			action = new FeedCommentAction();
		}
	
		
		return action;
	}
}
