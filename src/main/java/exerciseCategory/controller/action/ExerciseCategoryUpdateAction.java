package exerciseCategory.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import exerciseCategory.model.ExerciseCategoryDao;
import exerciseCategory.model.ExerciseCategoryRequestDto;
import util.ApiResponseManager;

public class ExerciseCategoryUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String indexStr = request.getParameter("index");
		String name = request.getParameter("name");
		int index = Integer.parseInt(indexStr);

		ExerciseCategoryDao exerciseCategoryDao = ExerciseCategoryDao.getInstance();

		// ExerciseCategoryRequestDto friendDto = new ExerciseCategoryRequestDto(index, user.getCode(), name);
		ExerciseCategoryRequestDto exerciseCategoryDto = new ExerciseCategoryRequestDto(index, 1, name);
		
		JSONObject resObj = new JSONObject();
		
		if (exerciseCategoryDao.updateExerciseCategory(exerciseCategoryDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Exercise Category Update is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}

		response.getWriter().write(resObj.toString());
	}
}