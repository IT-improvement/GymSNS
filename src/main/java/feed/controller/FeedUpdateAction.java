package feed.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import feed.model.FeedDAO;
import feed.model.FeedResponseDTO;

/**
 * Servlet implementation class FeedUpdateAction
 */
public class FeedUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("sdfwe");
	System.out.println(request.getPathInfo());
	
	}
	

}
