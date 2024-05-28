package feed.controller.action;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import org.json.JSONObject;
import util.ApiResponseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FeedFavoriteDeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url[] = request.getPathInfo().split("/");
        int feedIndex = Integer.parseInt(url[1]);

        System.out.println(feedIndex);
        int userCode = 1002;

        FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex, userCode);
        FeedDAO feedDao = FeedDAO.getInstance();
        FeedResponseDTO feed = feedDao.deleteFeedFavorite(feedDto);

        JSONObject resObj = new JSONObject();

        if (feed == null) {
            resObj = ApiResponseManager.getStatusObject(200);
        } else {
            resObj = ApiResponseManager.getStatusObject(400);
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().append(resObj.toString());
    }
}
