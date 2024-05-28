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

public class ExerciseReadOneAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String exerciseIndexStr = request.getParameter("index");
		int exerciseIndex = Integer.parseInt(exerciseIndexStr);

		ExerciseDao exerciseDao = ExerciseDao.getInstance();

		ExerciseRequestDto exerciseDto = new ExerciseRequestDto();
		exerciseDto.setIndex(exerciseIndex);
		
		ExerciseResponseDto exercise = exerciseDao.findExerciseOneByIndex(exerciseDto);

		JSONObject exerciseObj = new JSONObject();

		exerciseObj.put("index", exercise.getIndex());
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
