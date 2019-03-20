package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pages;
import model.dto.DTOComputer;
import services.ComputerServices;

@WebServlet("/DashBoardServlet")
public class DashBoard extends HttpServlet {
	
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;
	private static final String PAGES_COMPUTERS = "computerPages";
	private static final String VUE_LIST_COMPUTERS = "/WebContent/ressources/views/dashboard.jsp";
	private static final int DEFAULT_SIZE_PAGE = 10;
	private static final int DEFAULT_INDEX_PAGE = 0;
	private static final String NUMBER_COMPUTERS = "number_computer";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
		ComputerServices cont = new ComputerServices();
		Integer size = getParamSize(req);
		Integer index= getParamIndex(req);
		
		Pages<DTOComputer> p = cont.pagesDTOComputer(size, index);
		
		req.setAttribute(PAGES_COMPUTERS, p);
		req.setAttribute(NUMBER_COMPUTERS, p.getDataSize());
		
		this.getServletContext().getRequestDispatcher(VUE_LIST_COMPUTERS).forward(req, resp);
	}

	private Integer getParamIndex(HttpServletRequest req) {
		Integer index;
		if(req.getParameter("index")!=null) {
			index = Integer.parseInt(req.getParameter("index"));
		}else{
			index = DEFAULT_INDEX_PAGE;
		}
		return index;
	}

	private Integer getParamSize(HttpServletRequest req) {
		Integer size;
		if(req.getParameter("size")!=null) {
			size = Integer.parseInt(req.getParameter("size"));
		}else{
			size = DEFAULT_SIZE_PAGE;
		}
		return size;
	}

}
