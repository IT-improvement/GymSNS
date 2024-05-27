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
//		String userCode = (String)session.getAttribute("user");
		int userCode = 123;
		Timestamp date = Timestamp.valueOf(request.getParameter("diaryDate"));
		String content = request.getParameter("content");
		
		DiaryRequestDTO dto = new DiaryRequestDTO();
		dto.setUserCode(userCode);
		dto.setContent(content);
		dto.setDiary_date(date);
		
		DiaryDAO dao = DiaryDAO.getInstance();
		dao.updateDiary(dto);
	}

}
