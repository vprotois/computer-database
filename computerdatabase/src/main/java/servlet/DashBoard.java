package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.ComputerControler;
import model.Computer;
import model.Pages;

@SuppressWarnings("serial")
public class DashBoard extends HttpServlet {
	
	private static final String PAGES_COMPUTERS = "computerPages";
	private static final String VUE_LIST_COMPUTERS = "/Ressources/views/dashBoard.jsp";
	private static final int DEFAULT_SIZE_PAGE = 10;
	private static final int DEFAULT_INDEX_PAGE = 0;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
		ComputerControler cont = new ComputerControler();
		Integer size = getParamSize(req);
		Integer index= getParamIndex(req);
		
		Pages<Computer> p = cont.getListComputer(size, index);
		
		req.setAttribute(PAGES_COMPUTERS, p);
		
		this.getServletContext().getRequestDispatcher(VUE_LIST_COMPUTERS).forward(req, resp);
	}

	private Integer getParamIndex(HttpServletRequest req) {
		Integer index;
		if(req.getParameter("index")==null) {
			index = Integer.parseInt(req.getParameter("index"));
		}else{
			index = DEFAULT_INDEX_PAGE;
		}
		return index;
	}

	private Integer getParamSize(HttpServletRequest req) {
		Integer size;
		if(req.getParameter("size")==null) {
			size = Integer.parseInt(req.getParameter("size"));
		}else{
			size = DEFAULT_SIZE_PAGE;
		}
		return size;
	}

}
