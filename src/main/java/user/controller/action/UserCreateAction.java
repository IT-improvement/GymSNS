package user.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserRequestDto;

public class UserCreateAction extends HttpServlet implements Action {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 로직을 작성

		InputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String data = "";
		while(br.ready()) {
			data += br.readLine();
		}

		System.out.print("data : " + data);
		JSONObject object = new JSONObject(data);

		String id =  object.getString("id");
		String password = object.getString("password");
		String name = object.getString("name");

		String email = "";
		if (object.has("email") && !object.isNull("email")) {
			email = object.getString("email");
		} else {
			email = "";
		}
		String birth = object.getString("birth");
		String gender = object.getString("gender");
		String telecom = object.getString("telecom");
		String phone = object.getString("phone");

		UserDao dao = UserDao.getInstance();
		UserRequestDto userDto = new UserRequestDto(id, password, email, name, birth, gender, telecom, phone);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		JSONObject jsonResponse = new JSONObject();

		boolean isCreate = true;
		String message = "";

		if (object.has("id") && !object.isNull("id")) {
			id = object.getString("id");
		} else {
			isCreate = false;
			message += "아이디: 필수 정보입니다.";
		}

		if (object.has("password") && !object.isNull("password")) {
			password = object.getString("password");
		} else {
			isCreate = false;
			if (!message.equals(""))
				message += System.lineSeparator();
			message += "비밀번호: 필수 정보입니다.";
		}

		if (object.has("name") && !object.isNull("name")) {
			name = object.getString("name");
		} else {
			isCreate = false;
			if (!message.equals(""))
				message += System.lineSeparator();
			message += "이름: 필수 정보입니다.";
		}

		if (object.has("birth") && !object.isNull("birth")) {
			birth = object.getString("birth");
		} else {
			isCreate = false;
			if (!message.equals(""))
				message += System.lineSeparator();
			message += "생년월일: 필수 정보입니다.";
		}

		if (object.has("gender") && !object.isNull("gender")) {
			gender = object.getString("gender");
		} else {
			isCreate = false;
			if (!message.equals(""))
				message += System.lineSeparator();
			message += "성별: 필수 정보입니다.";
		}

		if (object.has("telecom") && !object.isNull("telecom")) {
			telecom = object.getString("telecom");
		} else {
			isCreate = false;
			if (!message.equals(""))
				message += System.lineSeparator();
			message += "통신사: 필수 정보입니다.";
		}

		if (object.has("phone") && !object.isNull("phone")) {
			phone = object.getString("phone");
		} else {
			isCreate = false;
			if (!message.equals(""))
				message += System.lineSeparator();
			message += "휴대폰 번호: 필수 정보입니다.";
		}

		if (dao.isIdDuplicate(id)) {
			isCreate = false;
			if (!message.equals(""))
				message += System.lineSeparator();
			message += "이미 사용 중인 ID입니다.";
		}

		if (dao.isEmailDuplicate(email)) {
			isCreate = false;
			if (!message.equals(""))
				message += System.lineSeparator();
			message += "이미 사용 중인 ID입니다.";
		}

		if (!isCreate) {
			jsonResponse.put("status", 401);
			jsonResponse.put("message", message);
		} else {
			try {
				dao.createUser(userDto);
				JSONObject userObj = new JSONObject();
				userObj.put("id", id);
				userObj.put("password", password);
				userObj.put("email", email);
				userObj.put("name", name);
				userObj.put("birth", birth);
				userObj.put("gender", gender);
				userObj.put("telecom", telecom);
				userObj.put("phone", phone);
				System.out.println("유저 생성 완료");
				jsonResponse.put("status", 200);
				jsonResponse.put("message", "회원가입 완료");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유저 생성 실패");
				jsonResponse.put("status", 401);
				jsonResponse.put("message", "회원가입 실패");
			}
		}

		response.getWriter().write(jsonResponse.toString());

	}

}