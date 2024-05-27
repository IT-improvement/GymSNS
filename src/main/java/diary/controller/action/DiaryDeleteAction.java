package diary.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.controller.Action;
import diary.model.DiaryDAO;

public class DiaryDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession();
//		String userCode = (String)session.getAttribute("user");
		int userCode = 123;
		int diaryIndex = Integer.parseInt(request.getParameter("diaryIndex"));
		
		DiaryDAO dao = DiaryDAO.getInstance();
		dao.deleteDiary(diaryIndex);
	}

}
