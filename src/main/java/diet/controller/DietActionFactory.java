package diet.controller;

import diet.controller.action.CreateDietAction;
import diet.controller.action.DeleteDietAction;
import diet.controller.action.ReadAllDietAction;
import diet.controller.action.ReadDetailDietAction;
import diet.controller.action.UpdateDietAction;
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
		
		if(command.equals("createDiet")) 
			action = new CreateDietAction();
		else if(command.equals("deleteDiet"))
			action = new DeleteDietAction();
		else if(command.equals("updateDiet"))
			action = new UpdateDietAction();
		else if(command.equals("readDetailDiet"))
			action = new ReadDetailDietAction();
		else if(command.equals("readAllDiet"))
			action = new ReadAllDietAction();
		
		return action;
	}
}
