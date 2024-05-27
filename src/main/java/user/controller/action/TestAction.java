package user.controller.action;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import user.controller.Action;

public class TestAction extends HttpServlet implements Action{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part file =  request.getPart("file");
		
		file.getContentType();
		
		InputStream inputStream =  file.getInputStream();
		byte[] bytes = inputStream.readAllBytes();
		
		response.getWriter().append("test~");
		
	}
	
}
