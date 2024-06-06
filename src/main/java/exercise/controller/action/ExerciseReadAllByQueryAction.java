package exercise.controller.action;

import exercise.controller.Action;
import exercise.model.ExerciseDao;
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
		String pageNumberStr = request.getParameter("pageNumber");
		String isDescOrderStr = request.getParameter("isDescOrder");

		boolean isDescOrder = (isDescOrderStr != null && isDescOrderStr.equals("false")) ? false : true;
		int pageNumber = ParameterValidator.isInteger(pageNumberStr) ? Integer.parseInt(pageNumberStr) : 1;

		ExerciseDao exerciseDao = ExerciseDao.getInstance();
		List<ExerciseResponseDto> exercises = exerciseDao.findExerciseAllByQueryByPageNumber(isDescOrder, query, pageNumber);

		JSONArray exerciseJsonArr = new JSONArray();
		
		for (ExerciseResponseDto exercise : exercises) {
			JSONObject exerciseObj = new JSONObject();

			exerciseObj.put("index", exercise.getIndex());
			exerciseObj.put("categoryIndex", exercise.getCategoryIndex());
			exerciseObj.put("categoryName", exercise.getCategoryName());
			exerciseObj.put("userCode", exercise.getUserCode());
			exerciseObj.put("userId", exercise.getUserId());
			exerciseObj.put("userName", exercise.getUserName());
			exerciseObj.put("userProfileImage", exercise.getUserProfileImage());
			exerciseObj.put("name", exercise.getName());
			exerciseObj.put("content", exercise.getContent());
			exerciseObj.put("createDate", exercise.getCreateDate());
			exerciseObj.put("modDate", exercise.getModDate());

			exerciseJsonArr.put(exerciseObj);
		}

		response.getWriter().write(exerciseJsonArr.toString());
	}
}
