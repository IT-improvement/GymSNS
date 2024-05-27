package user.controller.action;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import user.controller.Action;
import user.model.User;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class UpdateAction extends HttpServlet implements Action {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		String gender = (String) session.getAttribute("gender");
		String birth = (String) session.getAttribute("birth");

		UserDao userDao = UserDao.getInstance();
		UserResponseDto user = (UserResponseDto) session.getAttribute("user");
		
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String jsonString = sb.toString();
		
		// JSON 데이터 파싱
		JSONObject jsonRequest = new JSONObject(jsonString);
		
		String password = jsonRequest.optString("password", null);
		String name = jsonRequest.optString("name", null);
		String email = jsonRequest.optString("email", null);
		String telecom = jsonRequest.optString("telecom", null);
		String phone = jsonRequest.optString("phone", null);
		
//        String password = request.getParameter("password");
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String telecom = request.getParameter("telecom");
//        String phone = request.getParameter("phone");
        
        System.out.println("id : " + id);
        System.out.println("name : " + name);
        
        UserRequestDto userDto = new UserRequestDto();
		
		userDto.setId(id);
		userDto.setGender(gender);
		userDto.setBirth(birth);
		
		JSONObject jsonResponse = new JSONObject();
		
		try {
			if (password != null && !password.isEmpty() && !password.equals(user.getPassword())) {
				userDto.setPassword(password);
				user = userDao.updateUserPassword(userDto, password);
				}
			if (name != null && !name.isEmpty() && !name.equals(user.getName())) {
				userDto.setName(name);
				user = userDao.updateUserName(userDto);
				if (user != null) {
					System.out.println("이름 업데이트 완료: " + name);
				} else {
					System.out.println("이름 업데이트 실패");
				}
			}
			
			if (email != null && !email.isEmpty() && !email.equals(user.getEmail()) && !userDao.isEmailDuplicate(email)) {
				userDto.setEmail(email);
				user = userDao.updateUserEmail(userDto);
				System.out.println("이메일 업데이트 완료: " + email);
			}
			
			if (telecom != null && !telecom.isEmpty() && phone != null && !phone.isEmpty() && (!telecom.equals(user.getTelecom()) || !phone.equals(user.getPhone()))) {
				userDto.setTelecom(telecom);
				userDto.setPhone(phone);
				user = userDao.updateUserPhone(userDto);
				System.out.println("통신사 업데이트 완료: " + telecom);
				System.out.println("휴대폰 번호 업데이트 완료: " + phone);
			}
			
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "업데이트 성공");
			
		} catch (Exception e) {
			jsonResponse.put("status", 401);
			jsonResponse.put("message", "업데이트 실패");
		}
		
		session.setAttribute("user", user);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());
	}
}
