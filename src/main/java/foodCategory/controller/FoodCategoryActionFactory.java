package foodCategory.controller;

import foodCategory.controller.action.CreateFoodCategoryAction;
import foodCategory.controller.action.DeleteFoodCategoryAction;
import foodCategory.controller.action.ReadAllFoodCategoryAction;
import foodCategory.controller.action.UpdateFoodCategoryAction;
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
		else if(command.equals("updateFoodCategory"))
			action = new UpdateFoodCategoryAction();
		else if(command.equals("readAllFoodCategory"))
			action = new ReadAllFoodCategoryAction();
		
		return action;
	}
}
