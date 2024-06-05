package dietcategory.controller.action;

import dietcategory.controller.Action;
import dietcategory.model.DietCategoryDao;
import dietcategory.model.DietCategoryResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DietCategoryReadAllAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        DietCategoryDao dietCategoryDao = DietCategoryDao.getInstance();

        List<DietCategoryResponseDto> dietCategories = dietCategoryDao.findDietCategoryAll();

        JSONArray dietCategoryJsonArr = new JSONArray();

        for (DietCategoryResponseDto dietCategory : dietCategories) {
            JSONObject dietCategoryObj = new JSONObject();

            dietCategoryObj.put("index", dietCategory.getIndex());
            dietCategoryObj.put("name", dietCategory.getName());

            dietCategoryJsonArr.put(dietCategoryObj);
        }

        response.getWriter().write(dietCategoryJsonArr.toString());
    }
}
