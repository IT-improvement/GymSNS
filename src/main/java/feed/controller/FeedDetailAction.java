package feed.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import feed.model.Feed;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;


public class FeedDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello" + request.getPathInfo());
		String url[] = request.getPathInfo().split("/");
		int feedIndex = Integer.parseInt(url[1]);
		System.out.println(feedIndex);
		
		FeedDAO feedDao = new FeedDAO();
		FeedResponseDTO feedDto = feedDao.getFeedByFeedIndex(feedIndex);
		
		JSONObject feedObj = new JSONObject();
				
		
			
		feedObj.put("title", feedDto.getTitle());
		feedObj.put("content", feedDto.getContent());
		feedObj.put("feedIndex", feedDto.getFeedIndex());
		feedObj.put("userCode", feedDto.getUserCode());	
			
	

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedObj.toString());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url[] = request.getPathInfo().split("/");
		
		int feedIndex = Integer.parseInt(url[1]);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		FeedRequestDTO feedDto = new FeedRequestDTO(title, content, feedIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.updateFeed(feedDto);
		
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url[] = request.getPathInfo().split("/");
		
		int feedIndex = Integer.parseInt(url[1]);
		
		System.out.println(feedIndex);
		
		FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		feedDao.deleteFeed(feedDto);
	}
	


}
