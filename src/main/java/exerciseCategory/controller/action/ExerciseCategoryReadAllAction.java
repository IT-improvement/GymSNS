package exerciseCategory.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import exerciseCategory.model.ExerciseCategoryDao;
import exerciseCategory.model.ExerciseCategoryRequestDto;
import exerciseCategory.model.ExerciseCategoryResponseDto;

public class ExerciseCategoryReadAllAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ExerciseCategoryDao exerciseCategoryDao = ExerciseCategoryDao.getInstance();

		// ExerciseCategoryRequestDto friendDto = new ExerciseCategoryRequestDto(user.getCode());
		ExerciseCategoryRequestDto exerciseCategoryDto = new ExerciseCategoryRequestDto(1001);
		List<ExerciseCategoryResponseDto> exerciseCategories = exerciseCategoryDao.findExerciseCategoryAll(exerciseCategoryDto);

		JSONArray exerciseCategoryJsonArr = new JSONArray();
		
		for (ExerciseCategoryResponseDto exerciseCategory : exerciseCategories) {
			JSONObject exerciseCategoryObj = new JSONObject();

			exerciseCategoryObj.put("index", exerciseCategory.getIndex());
			exerciseCategoryObj.put("name", exerciseCategory.getName());
			
			exerciseCategoryJsonArr.put(exerciseCategoryObj);
		}

		response.getWriter().write(exerciseCategoryJsonArr.toString());
	}
}