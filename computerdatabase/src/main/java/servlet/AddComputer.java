package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.CreateComputerError;
import mapper.TimeStampMapper;
import model.Company;
import model.Computer;
import model.builders.ComputerBuilder;
import services.CompanyServices;
import services.ComputerServices;

@WebServlet(name = "AddComputer",urlPatterns= {"/add"})
public class AddComputer extends HttpServlet {	
	/**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 6730501184846318246L;

	
	private static final String COMPANIES_ATTRIBUTE = "companies";
	
	private static final String COMPUTER_NAME = "computerName";
	private static final String INTRODUCED_DATE = "introduced";
	private static final String DISCONTINUED_DATE = "discontinued";
	private static final String COMPANY_ID = "companyId";
	
	
	private static final String ERROR_500 = "/ressources/views/500.jsp";
	private static final String VIEW_ADD_COMPUTERS = "/ressources/views/addComputer.jsp";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		
		CompanyServices service = new CompanyServices();
		
		Optional<List<Company>> list = service.listCompanies();
		if(list.isPresent()) {
			req.setAttribute(COMPANIES_ATTRIBUTE, list.get());
		}else {
			req.setAttribute(COMPANIES_ATTRIBUTE, new ArrayList<Company>());
		}
		
		
		this.getServletContext()
		.getRequestDispatcher(VIEW_ADD_COMPUTERS)
		.forward(req, resp);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		

		String name = req.getParameter(COMPUTER_NAME);
		String introduced = req.getParameter(INTRODUCED_DATE);
		String discontinued = req.getParameter(DISCONTINUED_DATE);
		String companyId = req.getParameter(COMPANY_ID);
		
		
		ComputerServices cont = new ComputerServices();
		
		Computer c = new ComputerBuilder()
						.withName(name)
						.withCompanyId(Long.parseLong(companyId))
						.withIntroduced(TimeStampMapper.stringToTimestamp(introduced))
						.withDiscontinued(TimeStampMapper.stringToTimestamp(discontinued))
						.build();
		
		try {
			cont.addComputer(c);
		} catch (CreateComputerError e) {
			resp.sendRedirect(ERROR_500);
		}
		
		doGet(req, resp);
		
	}
	
}
