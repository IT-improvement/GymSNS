package routine.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import routine.controller.Action;
import routine.model.RoutineDAO;
import routine.model.RoutineRequestDTO;

public class RoutineCreateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userCode = 123;
		String day = request.getParameter("day");
		int exerciseIndex =Integer.parseInt(request.getParameter("exerciseIndex"));
		
		RoutineRequestDTO dto = new RoutineRequestDTO();
		dto.setDay(day);
		dto.setExerciseIndex(exerciseIndex);
		dto.setUserCode(userCode);
		
		RoutineDAO dao = RoutineDAO.getInstance();
		dao.inesrtRoutine(dto);
	}

}
