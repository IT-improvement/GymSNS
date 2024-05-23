package feed.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;

/**
 * Servlet implementation class FeedUpdateAction
 */
public class FeedUpdateAction implements Action{
       
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url[] = request.getPathInfo().split("/");
		
		int feedIndex = Integer.parseInt(url[1]);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		FeedRequestDTO feedDto = new FeedRequestDTO(title, content, feedIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.updateFeed(feedDto);
		
		JSONObject feedObj = new JSONObject();
		feedObj.put("title", feed.getTitle());
		feedObj.put("content", feed.getContent());
		feedObj.put("userCode", feed.getUserCode());
		feedObj.put("createDate", feed.getCreateDate());
		
		
				
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedObj.toString());
		
	}

}
