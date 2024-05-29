package feed.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import feed.controller.Action;
import feed.model.Feed;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;


public class FeedDetailAction implements Action {
       
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello" + request.getPathInfo());
		String url[] = request.getPathInfo().split("/");
		int feedIndex = Integer.parseInt(url[1]);
		System.out.println(feedIndex);
		
		FeedDAO feedDao = new FeedDAO();
		FeedResponseDTO feedDto = feedDao.getFeedByFeedIndex(feedIndex);
		
		JSONObject feedObj = new JSONObject();
				
		
		System.out.println(feedDto.getTitle());
		System.out.println(feedDto.getContent());
		System.out.println(feedDto.getFeedIndex());
		System.out.println(feedDto.getUserCode());
		
		feedObj.put("title", feedDto.getTitle());
		feedObj.put("content", feedDto.getContent());
		feedObj.put("feedIndex", feedDto.getFeedIndex());
		feedObj.put("userCode", feedDto.getUserCode());
		feedObj.put("comment", feedDto.getComment());
			
	

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedObj.toString());
	}
	


	


}
