package foodCategory.controller;

import foodCategory.controller.action.CreateFoodCategoryAction;
import user.controller.Action;

public class FoodCategoryActionFactory {

	private FoodCategoryActionFactory() {
		
	}
	private static FoodCategoryActionFactory instance = new FoodCategoryActionFactory();
	
	public static FoodCategoryActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("createFoodCategory"))
			action = new CreateFoodCategoryAction();
		else if(command.equals("deleteFoodCategory"))
			action = new DeleteFoodCategoryAction();
		//...
		
		return action;
	}
}
