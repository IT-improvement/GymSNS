package diary.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.model.DiaryDAO;
import diary.model.DiaryRequestDTO;
import util.DBManager;

public class DiaryFormAction extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = request.getParameter("date");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		System.out.println(temp);
		try {
			Date date =  sdf.parse(temp);
			System.out.println(new Timestamp(date.getTime()));
		} catch (Exception e) {
			System.out.println("오류");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int code = (int)session.getAttribute("user");
		
		String content = request.getParameter("content");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		long dateTemp = System.currentTimeMillis();
		String date = sdf.format(dateTemp);
		Timestamp time= Timestamp.valueOf(date);
		
		DiaryRequestDTO dto = new DiaryRequestDTO();
		dto.setUserCode(code);
		dto.setContent(content);
		dto.setDiary_date(time);
		
		DiaryDAO dao = DiaryDAO.getInstance();
		dao.writeDiary(dto);
	}

}
