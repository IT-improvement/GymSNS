package feed.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import feed.model.Feed;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import user.model.User;
import user.model.UserResponseDTO;

/**
 * Servlet implementation class FeedAction
 */
public class FeedCreateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		User user = new User();
//		user.setCode(1004);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		User userDto = (User)session.getAttribute("user");
		
		if (userDto == null) {
			response.sendRedirect("/login");
		}
		
		int userCode = userDto.getCode();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String feedImage = request.getParameter("feedImage");
		
		System.out.println(title);
		System.out.println(content);
		
		boolean isValid = true;
		
		if(title == null || title.equals(""))
			isValid = false;
		else if(content == null || content.equals(""))
			isValid = false;
		
		if(isValid) {
			FeedRequestDTO feedDto = new FeedRequestDTO(userCode, title, content);
			FeedDAO feedDao = FeedDAO.getInstance();
			FeedResponseDTO feed = feedDao.createFeed(feedDto);
		}
		else {
			System.out.println("isValid 실패");
		}
	}

}
