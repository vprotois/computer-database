package controler.servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pages;
import model.dto.DTOComputer;
import services.ComputerServices;

@WebServlet(name = "DashBoard",urlPatterns= {"/dashboard"})
public class DashBoard extends HttpServlet {
	
	/**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 5874765507120279702L;
	
	private static final String PAGE_COMPUTERS = "computerPage";
	private static final String NUMBER_COMPUTERS = "number_computers";
	private static final String NEXT_PAGE = "next_page_index";
	private static final String PREVIOUS_PAGE = "previous_page_index";
	private static final String PAGE_DATA = "page_data";
	
	
	private static final int DEFAULT_INDEX_PAGE = 0;
	private static final int DEFAULT_SIZE_PAGE = 10;
	
	
	private static final String ERROR_500 = "/ressources/views/500.jsp";
	private static final String VIEW_LIST_COMPUTERS = "/ressources/views/dashboard.jsp";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
		Integer size = getParamSize(req);
		Integer index= getParamIndex(req);
		String search = (String) req.getParameter("search");
		
		ComputerServices cont = new ComputerServices();
		Optional<Pages<DTOComputer>> optpages;
		if(search == null) {
			optpages = cont.pagesDTOComputer(size, index);			
		}else{
			optpages = cont.pagesComputerWithName(search, size, index);
		}
		
		if(!optpages.isPresent()) {
			resp.sendRedirect(ERROR_500);
		}
		else {
			Pages<DTOComputer> p = optpages.get();
			
			req.setAttribute(PAGE_COMPUTERS, p);
			req.setAttribute(NUMBER_COMPUTERS, p.getDataSize());
			req.setAttribute(PAGE_DATA, p.getPageData());
			req.setAttribute(NEXT_PAGE,p.nextIndex());
			req.setAttribute(PREVIOUS_PAGE,p.previousIndex());
			this.getServletContext()
			.getRequestDispatcher(VIEW_LIST_COMPUTERS)
			.forward(req, resp);
		}	
	}

	private Integer getParamIndex(HttpServletRequest req) throws ServletException {
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
