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

/**
 * Servlet implementation class FeedDeleteAction
 */
public class FeedDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url[] = request.getPathInfo().split("/");
		
		int feedIndex = Integer.parseInt(url[1]);
		
		System.out.println(feedIndex);
		
		FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		feedDao.deleteFeed(feedDto);
	}

}
