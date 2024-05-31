package feed.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import org.json.JSONObject;
import util.ApiResponseManager;
import util.ParameterValidator;

/**
 * Servlet implementation class FeedDeleteAction
 */
public class FeedDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userCodeStr = request.getHeader("Authorization");
		String url[] = request.getPathInfo().split("/");


		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(url[1])) {
			JSONObject resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}
		JSONObject resObj = new JSONObject();
		int feedIndex = Integer.parseInt(url[1]);
		
		System.out.println(feedIndex);
		
		FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		if (feedDao.deleteFeed(feedDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Exercise Delete is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}
		response.getWriter().write(resObj.toString());
	}

}
