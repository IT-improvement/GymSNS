package feed.controller.action;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import org.json.JSONObject;

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
		feedObj.put("comment", feed.getComment());

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedObj.toString());
		
		
	}

}
