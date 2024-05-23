package feed.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import user.model.User;

/**
 * Servlet implementation class FeedFavoriteAction
 */
public class FeedFavoriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedFavoriteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int feedIndex = Integer.parseInt(request.getParameter("feedIndex"));
		
		FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.readFeedFavoriteInfo(feedDto);
		
		JSONObject feedFavoriteCount = new JSONObject();
		
		feedFavoriteCount.put("feedFavoriteCount", feed.getFavoriteCount());
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedFavoriteCount.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		User user = new User();
		session.setAttribute("user", user);
		
		User userDto = (User)session.getAttribute("user");
		
		if (userDto == null) {
			response.sendRedirect("/login");
		}
		
		int userCode = userDto.getCode();
		int feedIndex = Integer.parseInt(request.getParameter("feedIndex"));
		
		FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex, userCode);
		FeedDAO feedDao = FeedDAO.getInstance();
		FeedResponseDTO feed = feedDao.createFeedFavorate(feedDto);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	
	
}
