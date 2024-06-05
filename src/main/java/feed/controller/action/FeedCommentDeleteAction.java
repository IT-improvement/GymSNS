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
		System.out.println("><><>><>");
		System.out.println("User : " + userCodeStr);
		int commentIndex = Integer.parseInt(url[1]);


		int userCode = Integer.parseInt(userCodeStr);

		System.out.println("CommentIndex : " + commentIndex);

		FeedRequestDTO feedDto = new FeedRequestDTO();
		feedDto.setCommentIndex(commentIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.deleteComment(feedDto);

		JSONObject resObj = new JSONObject();
		if (feed == null) {
			resObj = ApiResponseManager.getStatusObject(200);
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(resObj.toString());


	}
}
