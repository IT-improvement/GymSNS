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

public class ExerciseDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");
		String exerciseIndexStr = request.getParameter("exercise_index");

		JSONObject resObj = new JSONObject();

		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(exerciseIndexStr)) {
			resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int exerciseIndex = Integer.parseInt(exerciseIndexStr);

		ExerciseDao exerciseDao = ExerciseDao.getInstance();
		ExerciseRequestDto exerciseDto = new ExerciseRequestDto(exerciseIndex, userCode);

		if (exerciseDao.deleteExercise(exerciseDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Exercise Delete is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}

		response.getWriter().write(resObj.toString());
	}
}