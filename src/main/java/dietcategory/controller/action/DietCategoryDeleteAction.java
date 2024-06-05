package dietcategory.controller.action;

import dietcategory.controller.Action;
import dietcategory.model.DietCategoryDao;
import dietcategory.model.DietCategoryRequestDto;
import org.json.JSONObject;
import util.ApiResponseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DietCategoryDeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String indexStr = request.getParameter("index");
        int index = Integer.parseInt(indexStr);

        DietCategoryDao dietCategoryDao = DietCategoryDao.getInstance();

        DietCategoryRequestDto dietCategoryDto = new DietCategoryRequestDto(1);

        JSONObject resObj = new JSONObject();

        if (dietCategoryDao.deleteDietCategory(dietCategoryDto)) {
            resObj = ApiResponseManager.getStatusObject(200, "Diet Category Delete is finished successfully");
        } else {
            resObj = ApiResponseManager.getStatusObject(500);
        }

        response.getWriter().write(resObj.toString());
    }
}
