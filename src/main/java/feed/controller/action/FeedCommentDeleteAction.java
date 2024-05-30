package feed.controller.action;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import org.json.JSONObject;
import util.ApiResponseManager;
import util.ParameterValidator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedCommentDeleteAction
 */
public class FeedCommentDeleteAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userCodeStr = request.getHeader("Authorization");
		String url[] = request.getPathInfo().split("/");


		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(request.getParameter("commentIndex"))) {
			JSONObject resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int commentIndex = Integer.parseInt(request.getParameter("commentIndex"));

		FeedRequestDTO feedDto = new FeedRequestDTO();
		feedDto.setCommentIndex(commentIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.deleteComment(feedDto);

		JSONObject feedObj = new JSONObject();
		if (feed == null) {
			feedObj = ApiResponseManager.getStatusObject(200);
		} else {
			feedObj = ApiResponseManager.getStatusObject(400);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().append(feedObj.toString());


	}
}
