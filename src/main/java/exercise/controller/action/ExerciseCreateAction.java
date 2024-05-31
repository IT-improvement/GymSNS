package exercise.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exercise.controller.Action;
import exerciseCategory.model.ExerciseCategoryDao;
import exerciseCategory.model.ExerciseCategoryRequestDto;
import org.json.JSONObject;

import exercise.model.ExerciseDao;
import exercise.model.ExerciseRequestDto;
import util.ApiResponseManager;
import util.ParameterValidator;

public class ExerciseCreateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");
		String categoryIndexStr = request.getParameter("category_index");
		String name = request.getParameter("name");
		String content = request.getParameter("content");

		JSONObject resObj = new JSONObject();

		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(categoryIndexStr) ||
				name == null || name.isEmpty() ||
				content == null || content.isEmpty()) {
			response.sendError(400, "Bad Request");
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int categoryIndex = Integer.parseInt(categoryIndexStr);

		ExerciseDao exerciseDao = ExerciseDao.getInstance();
		ExerciseCategoryDao exerciseCategoryDao = ExerciseCategoryDao.getInstance();

		ExerciseRequestDto exerciseDto = new ExerciseRequestDto(categoryIndex, userCode, name, content);
		ExerciseCategoryRequestDto exerciseCategoryDto = new ExerciseCategoryRequestDto(categoryIndex);

		if (!exerciseCategoryDao.doesCategoryExist(exerciseCategoryDto)) {
			response.sendError(400, "Bad Request");
			return;
		}

		if (exerciseDao.createExercise(exerciseDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Exercise Create is finished successfully");
			response.getWriter().write(resObj.toString());
		} else {
			response.sendError(500, "Server Error");
		}
	}
}