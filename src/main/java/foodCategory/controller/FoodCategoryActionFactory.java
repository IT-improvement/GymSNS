package foodCategory.controller;

import foodCategory.controller.action.*;
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
		else if(command.equals("readDetailFoodCategory"))
			action = new ReadDetailFoodCategoryAction();
		
		return action;
	}
}
