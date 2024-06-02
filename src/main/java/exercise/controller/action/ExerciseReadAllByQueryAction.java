package exercise.controller.action;

import exercise.controller.Action;
import exercise.model.ExerciseDao;
import exercise.model.ExerciseRequestDto;
import exercise.model.ExerciseResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;
import util.ParameterValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExerciseReadAllByQueryAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String query = request.getParameter("query");
		String limit = request.getParameter("limit");

		ExerciseDao exerciseDao = ExerciseDao.getInstance();

		List<ExerciseResponseDto> exercises = ParameterValidator.isInteger(limit) ?
				exerciseDao.findExerciseAllByQueryWithLimit(query, Integer.parseInt(limit)) :
				exerciseDao.findExerciseAllByQuery(query);

		JSONArray exerciseJsonArr = new JSONArray();
		
		for (ExerciseResponseDto exercise : exercises) {
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
			
			exerciseJsonArr.put(exerciseObj);
		}

		response.getWriter().write(exerciseJsonArr.toString());
	}
}
