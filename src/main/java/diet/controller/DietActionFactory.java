package diet.controller;

import user.controller.Action;

public class DietActionFactory {

	private DietActionFactory() {
		
	}
	private static DietActionFactory instance = new DietActionFactory();
	
	public static DietActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
//		if(command.equals(""))
//			action = new 
			
		
		return action;
	}
}
