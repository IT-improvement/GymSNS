package foods.controller;

import foods.controller.action.CreateFoodAction;
import foods.controller.action.DeleteFoodAction;
import foods.controller.action.ReadAllFoodAction;
import foods.controller.action.UpdateFoodAction;
import user.controller.Action;

public class FoodsActionFactory {

	private FoodsActionFactory() {
		
	}
	private static FoodsActionFactory instance = new FoodsActionFactory();
	
	public static FoodsActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("createFood"))
			action = new CreateFoodAction();
		else if(command.equals("deleteFood"))
			action = new DeleteFoodAction();
		else if(command.equals("updateFood"))
			action = new UpdateFoodAction();
		else if(command.equals("readFoodList"))
			action = new ReadAllFoodAction();
		
		return action;
	}
}
