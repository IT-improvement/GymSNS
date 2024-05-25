package exercise.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import exercise.model.ExerciseDao;
import exercise.model.ExerciseRequestDto;
import util.ApiResponseManager;

public class ExerciseCreateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String categoryIndexStr = request.getParameter("category_index");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		
		int categoryIndex = Integer.parseInt(categoryIndexStr);

		ExerciseDao exerciseDao = ExerciseDao.getInstance();

		//ExerciseRequestDto exerciseRequestDto = new ExerciseRequestDto(categoryIndex, user.getCode(), name, content);
		ExerciseRequestDto exerciseDto = new ExerciseRequestDto(categoryIndex, 1, name, content);
		
		JSONObject resObj = new JSONObject();
		
		if (exerciseDao.createExercise(exerciseDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Exercise Create is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}

		response.getWriter().write(resObj.toString());
	}
}
