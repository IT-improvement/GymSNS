package exercise.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import exercise.model.ExerciseDao;
import exercise.model.ExerciseRequestDto;
import util.ApiResponseManager;

public class ExerciseDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String exerciseIndexStr = request.getParameter("exercise_index");
		int exerciseIndex = Integer.parseInt(exerciseIndexStr);

		ExerciseDao exerciseDao = ExerciseDao.getInstance();

		//ExerciseRequestDto exerciseRequestDto = new ExerciseRequestDto(exerciseIndex, user.getCode());
		ExerciseRequestDto exerciseDto = new ExerciseRequestDto(exerciseIndex, 1);
		
		JSONObject resObj = new JSONObject();
		
		if (exerciseDao.deleteExercise(exerciseDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Exercise Delete is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}

		response.getWriter().write(resObj.toString());
	}
}
