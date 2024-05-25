package exerciseCategory.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import exerciseCategory.model.ExerciseCategoryDao;
import exerciseCategory.model.ExerciseCategoryRequestDto;
import util.ApiResponseManager;

public class ExerciseCategoryCreateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ExerciseCategoryDao exerciseCategoryDao = ExerciseCategoryDao.getInstance();

		// ExerciseCategoryRequestDto friendDto = new ExerciseCategoryRequestDto(user.getCode());
		ExerciseCategoryRequestDto exerciseCategoryDto = new ExerciseCategoryRequestDto(1);
		
		JSONObject resObj = new JSONObject();
		
		if (exerciseCategoryDao.createExerciseCategory(exerciseCategoryDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Exercise Category Create is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}

		response.getWriter().write(resObj.toString());
	}
}