package exercise.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exercise.controller.Action;
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
			resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int categoryIndex = Integer.parseInt(categoryIndexStr);

		ExerciseDao exerciseDao = ExerciseDao.getInstance();

		ExerciseRequestDto exerciseDto = new ExerciseRequestDto(categoryIndex, userCode, name, content);

		if (exerciseDao.createExercise(exerciseDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Exercise Create is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}

		response.getWriter().write(resObj.toString());
	}
}