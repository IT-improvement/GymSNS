package feed.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;

/**
 * Servlet implementation class FeedFavoriteReadAction
 */
public class FeedFavoriteReadAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	int feedIndex = Integer.parseInt(request.getParameter("feedIndex"));
		
		String url[] = request.getPathInfo().split("/");
		int feedIndex = Integer.parseInt(url[1]);
		
		FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.readFeedFavoriteInfo(feedDto);
		
		JSONObject feedFavoriteCount = new JSONObject();
		
		feedFavoriteCount.put("feedFavoriteCount", feed.getFavoriteCount());
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedFavoriteCount.toString());
	}


}
