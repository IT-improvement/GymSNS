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
 * Servlet implementation class FeedCommentUpdateAction
 */
public class FeedCommentUpdateAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userCodeStr = request.getHeader("Authorization");
		String url[] = request.getPathInfo().split("/");


		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(request.getParameter("commentIndex")) || request.getParameter("comment") == null) {
			JSONObject resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}


		int commentIndex = Integer.parseInt(request.getParameter("commentIndex"));
		String comment = request.getParameter("comment");


		FeedRequestDTO feedDto = new FeedRequestDTO();
		feedDto.setCommentIndex(commentIndex);
		feedDto.setComment(comment);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.updateComment(feedDto);

		JSONObject feedObj = new JSONObject();
		feedObj.put("feedIndex", feed.getFeedIndex());
		feedObj.put("userCode", feed.getUserCode());
		feedObj.put("comment", feed.getComments());

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedObj.toString());


	}

}
