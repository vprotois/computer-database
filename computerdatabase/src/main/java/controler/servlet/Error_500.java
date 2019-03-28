package controler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ERROR500",urlPatterns= {"/500"})
public class Error_500 extends HttpServlet {
	
	private static final long serialVersionUID = -4218185890333762548L;
	
	private static final String ERROR_500 = "/computerdatabase/500";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
		
		
		this.getServletContext()
		.getRequestDispatcher(ERROR_500)
		.forward(req, resp);
		
	}

}
