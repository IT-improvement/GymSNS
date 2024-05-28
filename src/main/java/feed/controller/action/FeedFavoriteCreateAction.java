package feed.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import user.model.User;

/**
 * Servlet implementation class FeedFavoriteCreateAction
 */
public class FeedFavoriteCreateAction implements Action {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = new User();
		session.setAttribute("user", user);
		
		User userDto = (User)session.getAttribute("user");
		
		if (userDto == null) {
			response.sendRedirect("/login");
		}

		String url[] = request.getPathInfo().split("/");
		int feedIndex = Integer.parseInt(url[1]);

		 int userCode = userDto.getCode();

		
		FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex, userCode);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.createFeedFavorate(feedDto);
	}

}
