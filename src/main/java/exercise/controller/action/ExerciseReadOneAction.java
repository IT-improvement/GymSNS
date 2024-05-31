package exercise.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exercise.controller.Action;
import org.json.JSONObject;

import exercise.model.ExerciseDao;
import exercise.model.ExerciseRequestDto;
import exercise.model.ExerciseResponseDto;
import util.ApiResponseManager;
import util.ParameterValidator;

public class ExerciseReadOneAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");
		String exerciseIndexStr = request.getParameter("exercise_index");

		JSONObject exerciseObj = new JSONObject();

		if (!ParameterValidator.isInteger(exerciseIndexStr)) {
			response.sendError(400, "Bad Request");
			return;
		}

		int exerciseIndex = Integer.parseInt(exerciseIndexStr);

		ExerciseDao exerciseDao = ExerciseDao.getInstance();

		ExerciseRequestDto exerciseDto = new ExerciseRequestDto();
		exerciseDto.setIndex(exerciseIndex);
		
		ExerciseResponseDto exercise = exerciseDao.findExerciseOneByIndex(exerciseDto);

		if (exercise == null) {
			response.sendError(400, "Bad Request");
			return;
		}

		boolean isWriter = ParameterValidator.isInteger(userCodeStr) && exercise.getUserCode() == Integer.parseInt(userCodeStr);

		exerciseObj.put("index", exercise.getIndex());
		exerciseObj.put("isWriter", isWriter);
		exerciseObj.put("categoryIndex", exercise.getCategoryIndex());
		exerciseObj.put("categoryName", exercise.getCategoryName());
		exerciseObj.put("userCode", exercise.getUserCode());
		exerciseObj.put("userId", exercise.getUserId());
		exerciseObj.put("userName", exercise.getUserName());
		exerciseObj.put("name", exercise.getName());
		exerciseObj.put("content", exercise.getContent());
		exerciseObj.put("createDate", exercise.getCreateDate());
		exerciseObj.put("modDate", exercise.getModDate());

		response.getWriter().write(exerciseObj.toString());
	}
}
