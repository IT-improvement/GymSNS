package exercise.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import exercise.controller.Action;
import exercise.model.ExerciseDao;
import exercise.model.ExerciseResponseDto;

public class ExerciseReadByUserCode implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		int userCode = 123;
		ExerciseDao dao = ExerciseDao.getInstance();
		List<ExerciseResponseDto> list = dao.readByUserCode(userCode);
		
		JSONArray array = new JSONArray();
		for(ExerciseResponseDto dto: list) {
			JSONObject object = new JSONObject();
			object.put("exercise_index", dto.getIndex());
			object.put("exercise_category_index", dto.getCategoryIndex());
			object.put("name", dto.getName());
		
			array.put(object);
		}
		response.getWriter().write(array.toString());
	}
}
