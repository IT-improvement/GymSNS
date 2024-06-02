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

		String id = (String) session.getAttribute("id");
		String gender = (String) session.getAttribute("gender");
		String birth = (String) session.getAttribute("birth");

		UserDao userDao = UserDao.getInstance();
		//UserResponseDto user = (UserResponseDto) session.getAttribute("user");
		UserResponseDto user = userDao.findUserById(id);
		UserRequestDto userDto = new UserRequestDto();

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
		String email = jsonRequest.optString("email", null);
		String name = jsonRequest.optString("name", null);
		String telecom = jsonRequest.optString("telecom", null);
		String phone = jsonRequest.optString("phone", null);
		String profileImage = jsonRequest.optString("profileImage", null);

		String newPassword = PasswordCrypto.encrypt(password);

		userDto.setId(id);
		userDto.setGender(gender);
		userDto.setBirth(birth);

		System.out.println("id : " + id);
		System.out.println("name : " + name);
		System.out.println("password : " + password);
		System.out.println("email : " + email);
		System.out.println("telecom : " + telecom);
		System.out.println("phone : " + phone);
		System.out.println("gender : " + user.getGender());
		System.out.println("birth : " + user.getBirth());

		JSONObject jsonResponse = new JSONObject();

		try {
			String encryptedPassword = userDao.findPassword(user.getId(), user.getPassword());
			if (password != null && !password.equals("")) {
				userDto.setPassword(password);
				userDao.updateUserPassword(userDto, password);
				user.setPassword(password);
				System.out.println("password update 성공!");
			}

			if (email != null && !email.equals("") && !email.equals(user.getEmail()) && !userDao.isEmailDuplicate(email)) {
				userDto.setEmail(email);
				userDao.updateUserEmail(userDto);
				user.setEmail(email);
				System.out.println("이메일 업데이트 완료: " + email);
			}

			if (name != null && !name.equals("") && !name.equals(user.getName())) {
				userDto.setName(name);
				userDao.updateUserName(userDto);
				user.setName(name);
				System.out.println("이름 업데이트 완료: " + name);
			}

			if (telecom != null && !telecom.equals("") && !telecom.equals(user.getTelecom())) {
				userDto.setTelecom(telecom);
				userDao.updateUserTelecom(userDto);
				user.setTelecom(telecom);
				System.out.println("통신사 업데이트 완료: " + telecom);
			}
			if (phone != null && !phone.equals("") && !phone.equals(user.getPhone())) {
				userDto.setPhone(phone);
				userDao.updateUserPhone(userDto);
				user.setPhone(phone);
				System.out.println("휴대폰 번호 업데이트 완료: " + phone);
			}

			if (profileImage != null && !profileImage.equals("") && !profileImage.equals(user.getProfileImage())) {
				userDto.setProfileImage(profileImage);
				userDao.updateUserEmail(userDto);
				user.setProfileImage(profileImage);
				System.out.println("이메일 업데이트 완료: " + email);
			}

//			JSONObject userObj = new JSONObject();
			jsonResponse.put("id", id);
			jsonResponse.put("password", password);
			jsonResponse.put("email", email);
			jsonResponse.put("name", name);
			jsonResponse.put("birth", birth);
			jsonResponse.put("gender", gender);
			jsonResponse.put("telecom", telecom);
			jsonResponse.put("phone", phone);
			jsonResponse.put("profileImage", profileImage);

			//userDao.updateUser(user);
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "업데이트 성공");

		} catch (Exception e) {
			jsonResponse.put("status", 401);
			jsonResponse.put("message", "업데이트 실패");
			e.printStackTrace();
		}
//		user = userDao.findUserById(user.getId());
//		System.out.println("1)id : " + user.getId());
		session.setAttribute("user", user);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());

		System.out.println("<수정 후>");
		System.out.println("id : " + user.getId());
		System.out.println("password : " + user.getPassword());
		System.out.println("name : " + user.getName());
		System.out.println("email : " + user.getEmail());
		System.out.println("gender : " + user.getGender());
		System.out.println("birth : " + user.getBirth());
		System.out.println("telecom : " + user.getTelecom());
		System.out.println("phone : " + user.getPhone());
	}
}
