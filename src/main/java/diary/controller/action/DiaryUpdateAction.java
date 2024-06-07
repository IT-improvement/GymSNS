package diary.controller.action;

import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.controller.Action;
import diary.model.DiaryDAO;
import diary.model.DiaryRequestDTO;

public class DiaryUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession();
		int userCode = (int)session.getAttribute("code");
		int diaryIndex = Integer.parseInt(request.getParameter("diaryIndex"));
		String content = request.getParameter("content");
		
		DiaryDAO dao = DiaryDAO.getInstance();
		dao.updateDiary(diaryIndex, content);
	}

}
