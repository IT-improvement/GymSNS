package user.controller;

import user.controller.action.*;

public class ActionFactory {

	private ActionFactory() {
		
	}
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("create")) 
			action = new UserCreateAction();
		else if(command.equals("login"))
			action = new LoginAction();
		else if(command.equals("logout"))
			action = new LogoutAction();
		else if(command.equals("leave"))
			action = new LeaveAction();
		else if(command.equals("update"))
			action = new UpdateAction();
		else if(command.equals("read_one"))
			action = new UserReadOneAction();
		else if(command.equals("read_all"))
			action = new UserReadAllAction();
		else if(command.equals("read_all_by_query"))
			action = new UserReadAllByQueryAction();
		else if(command.equals("test"))
			action = new TestAction();

		return action;
	}
}
