package diet.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import diet.model.DietDao;
import diet.model.DietRequestDto;
import user.controller.Action;
import util.ApiResponseManager;

public class UpdateDietAction implements Action{

        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                try {
                        String dietIndexStr = request.getParameter("dietIndex");
                        String userCodeStr = request.getParameter("userCode");

                        int dietIndex = Integer.parseInt(dietIndexStr);
                        int userCode = Integer.parseInt(userCodeStr);

                        int foodIndex = Integer.parseInt(request.getParameter("foodIndex"));
                        int totalCalories = Integer.parseInt(request.getParameter("totalCalories"));
                        int totalProtein = Integer.parseInt(request.getParameter("totalProtein"));

                        validateInput(dietIndex, userCode, foodIndex, totalCalories, totalProtein);

                        DietDao dao = DietDao.getInstance();
                        DietRequestDto dietDto = new DietRequestDto(dietIndex, userCode, foodIndex, totalCalories, totalProtein);

                        if (!dao.existsById(dietIndex)) {
                                JSONObject jsonResponse = ApiResponseManager.getStatusObject(404, "Diet not found");
                                response.getWriter().write(jsonResponse.toString());
                                return;
                        }

                        if (dao.updateDiet(dietIndex, dietDto)) {
                                JSONObject jsonResponse = ApiResponseManager.getStatusObject(200, "식단 업데이트 성공");
                                response.getWriter().write(jsonResponse.toString());
                        } else {
                                JSONObject jsonResponse = ApiResponseManager.getStatusObject(500, "Failed to update diet");
                                response.getWriter().write(jsonResponse.toString());
                        }
                } catch (NumberFormatException e) {
                        e.printStackTrace();
                        JSONObject jsonResponse = ApiResponseManager.getStatusObject(400, "Invalid input data");
                        response.getWriter().write(jsonResponse.toString());
                } catch (Exception e) {
                        e.printStackTrace();
                        JSONObject jsonResponse = ApiResponseManager.getStatusObject(500, "Failed to update diet");
                        response.getWriter().write(jsonResponse.toString());
                }
        }

        private void validateInput(int dietIndex, int userCode, int foodIndex, int totalCalories, int totalProtein) throws ValidationException {
                if (dietIndex <= 0) {
                        throw new ValidationException("Invalid diet index");
                }
                if (userCode <= 0) {
                        throw new ValidationException("Invalid user code");
                }
                if (foodIndex <= 0) {
                        throw new ValidationException("Invalid food index");
                }
                if (totalCalories < 0) {
                        throw new ValidationException("Total calories should be a non-negative value");
                }
                if (totalProtein < 0) {
                        throw new ValidationException("Total protein should be a non-negative value");
                }
        }

        public class ValidationException extends Exception {
                public ValidationException(String message) {
                        super(message);
                }
        }
}
