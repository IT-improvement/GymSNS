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

public class DietCategoryCreateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        DietCategoryDao dietCategoryDao = DietCategoryDao.getInstance();
        String name = request.getParameter("name");

        DietCategoryRequestDto dietCategoryDto = new DietCategoryRequestDto(1001, name);

        JSONObject resObj = new JSONObject();

        if (dietCategoryDao.createDietCategory(dietCategoryDto)) {
            resObj = ApiResponseManager.getStatusObject(200, "Diet Category Create is finished successfully");
        } else {
            resObj = ApiResponseManager.getStatusObject(500);
        }

        response.getWriter().write(resObj.toString());
    }
}
