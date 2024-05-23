package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		String gender = (String) session.getAttribute("gender");
		String birth = (String) session.getAttribute("birth");

		UserDao userDao = UserDao.getInstance();
		UserResponseDto user = (UserResponseDto) session.getAttribute("user");
		
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String telecom = request.getParameter("telecom");
        String phone = request.getParameter("phone");
        
        UserRequestDto userDto = new UserRequestDto();
		
		userDto.setId(user.getId());
		userDto.setGender(user.getGender());
		userDto.setBirth(user.getBirth());
		
		if(user.getPassword() != null && !user.getPassword().equals(password)) {
			user = userDao.updateUserPassword(userDto, password);
		} else {
			userDto.setId(user.getId());
		}
		
		if(user.getName() != null && !user.getName().equals(name)) {
			userDto.setEmail(name);
			user = userDao.updateUserEmail(userDto);
		} else {
			userDto.setPassword(user.getName());
		}
		
		if(user.getEmail() != null && !user.getEmail().equals(email)) {
			userDto.setEmail(email);
			user = userDao.updateUserEmail(userDto);
		} else {
			userDto.setEmail(user.getEmail());
		}
		
        if(!user.getTelecom().equals(telecom) || !user.getPhone().equals(phone)) {
			userDto.setTelecom(telecom);
			userDto.setPhone(phone);
			user = userDao.updateUserPhone(userDto);
        } else {
        	userDto.setTelecom(user.getTelecom());
        	userDto.setPhone(user.getPhone());
        }
        
        session.setAttribute("user", user);
	}
}
