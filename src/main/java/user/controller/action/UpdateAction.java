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
import util.PasswordCrypto;

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

		UserDao userDao = UserDao.getInstance();
		UserResponseDto user = (UserResponseDto) session.getAttribute("user");
//		User user = (User) session.getAttribute("user");
//		UserRequestDto userDto = (UserRequestDto) user;
		UserRequestDto userDto = new UserRequestDto();
//		UserResponseDto userDto = new UserResponseDto(user);

//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");
//		String gender = request.getParameter("gender");
//		String birth = request.getParameter("birth");
//		String telecom = request.getParameter("telecom");
//		String phone = request.getParameter("phone");

		String id = (String) session.getAttribute("id");
		String gender = (String) session.getAttribute("gender");
		String birth = (String) session.getAttribute("birth");

		//User user = userDao.findUserById(id);
		//System.out.println("name : " + user.getName());

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

		String newPassword = PasswordCrypto.encrypt(password);

        System.out.println("id : " + id);
        System.out.println("name : " + name);
		System.out.println("password : " + password);
		System.out.println("email : " + email);
		System.out.println("telecom : " + telecom);
		System.out.println("phone : " + phone);

//		UserRequestDto userDto = new UserRequestDto();
		//UserRequestDto userDto = (UserRequestDto) user;

		System.out.println("setId 시작 전!!");
		userDto.setId(id);
		userDto.setGender(gender);
		userDto.setBirth(birth);

		JSONObject jsonResponse = new JSONObject();
		System.out.println("try 시작 전!");

		//user = userDao.findUserById(id);
		try {
			System.out.println("user try id : " + user.getId());
			System.out.println("user try name : " + user.getName());
			System.out.println("user try password : " + user.getPassword());
			String encryptedPassword = userDao.findPassword(user.getId(), user.getPassword());
			System.out.println("encryptedPassword 완료됨!!!");

			if (password != null && !password.equals("") && !PasswordCrypto.decrypt(password, encryptedPassword)) {
				userDto.setPassword(PasswordCrypto.encrypt(password));

				//user = userDao.findUserById(id);
				user = userDao.updateUserPassword(userDto, password);
				System.out.println("password update 성공!");
				//user = userDao.findUserById(id);
			}

			if (name != null && !name.equals("") && !name.equals(user.getName())) {
				userDto.setName(name);
				user = userDao.updateUserName(userDto);
				//user = userDao.findUserById(id);
				System.out.println("이름 업데이트 완료: " + name);
			}

			if (email != null && !email.equals("") && !email.equals(user.getEmail()) && !userDao.isEmailDuplicate(email)) {
				userDto.setEmail(email);
				user = userDao.updateUserEmail(userDto);
				System.out.println("이메일 업데이트 완료: " + email);
				//user = userDao.findUserById(id);
			}

			if (telecom != null && !telecom.equals("") && phone != null && !phone.equals("") && (!telecom.equals(user.getTelecom()) || !phone.equals(user.getPhone()))) {
				userDto.setTelecom(telecom);
				userDto.setPhone(phone);
				user = userDao.updateUserPhone(userDto);
				System.out.println("통신사 업데이트 완료: " + telecom);
				System.out.println("휴대폰 번호 업데이트 완료: " + phone);
				//user = userDao.findUserById(id);
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

		System.out.println(user.getName());
	}
}
