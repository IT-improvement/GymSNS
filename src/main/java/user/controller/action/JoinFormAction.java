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

import user.model.UserDao;
import user.model.UserRequestDto;

/**
 * Servlet implementation class JoinFormAction
 */
public class JoinFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinFormAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
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
		
		try {
			dao.createUser(userDto);
			int code =dao.findById(id);
			dao.joinRoutine(code);
			System.out.println("유저 생성 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("유저 생성 실패");
		}
		
		JSONObject jsonResponse = new JSONObject();
		response.getWriter().write(jsonResponse.toString());
	}

}
