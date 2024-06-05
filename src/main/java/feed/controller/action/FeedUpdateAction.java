package feed.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import feed.model.Feed;
import org.json.JSONArray;
import org.json.JSONObject;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import util.ApiResponseManager;
import util.ParameterValidator;

/**
 * Servlet implementation class FeedUpdateAction
 */
public class FeedUpdateAction implements Action{
       
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userCodeStr = request.getHeader("Authorization");
		String url[] = request.getPathInfo().split("/");


		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(url[1])) {
			JSONObject resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int feedIndex = Integer.parseInt(url[1]);
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		JSONObject resObj = new JSONObject();
		FeedRequestDTO feedDto = new FeedRequestDTO(title, content, feedIndex, userCode);
		FeedDAO feedDao = FeedDAO.getInstance();

		if(feedDao.updateFeed(feedDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Feed Update is finished successfully");
		}else {
			resObj = ApiResponseManager.getStatusObject(500);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resObj.toString());
		
	}

}
