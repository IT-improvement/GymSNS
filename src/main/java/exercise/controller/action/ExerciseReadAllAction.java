package exercise.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exercise.controller.Action;
import org.json.JSONArray;
import org.json.JSONObject;

import exercise.model.ExerciseDao;
import exercise.model.ExerciseResponseDto;
import util.ParameterValidator;

public class ExerciseReadAllAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String pageNumberStr = request.getParameter("pageNumber");
		String isDescOrderStr = request.getParameter("isDescOrder");

		boolean isDescOrder = (isDescOrderStr != null && isDescOrderStr.equals("false")) ? false : true;
		int pageNumber = ParameterValidator.isInteger(pageNumberStr) ? Integer.parseInt(pageNumberStr) : 1;

		ExerciseDao exerciseDao = ExerciseDao.getInstance();
		List<ExerciseResponseDto> exercises = exerciseDao.findExerciseAllByPageNumber(isDescOrder, pageNumber);

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
