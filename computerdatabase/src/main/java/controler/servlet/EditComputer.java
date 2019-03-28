package controler.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.UpdateComputerError;
import exception.ValidatorException;
import mapper.TimeStampMapper;
import model.Computer;
import model.builders.ComputerBuilder;
import services.ComputerServices;


@WebServlet(name = "EditComputer",urlPatterns= {"/edit"})
public class EditComputer extends HttpServlet {

	private static final long serialVersionUID = -8458639712921797680L;

	private static final String COMPUTER_NAME = "computerName";
	private static final String INTRODUCED_DATE = "introduced";
	private static final String DISCONTINUED_DATE = "discontinued";
	private static final String COMPANY_ID = "companyId";
	
	private static final String VIEW_EDIT_COMPUTER = "/ressources/views/editComputer.jsp";
	private static final String REDIRECT_LIST_COMPUTERS = "/computerdatabase/dashboard";
	private static final String ERROR_500 = "/computerdatabase/500";
	

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		Optional<Long> id = getParamId(req);
		
		if(!id.isPresent()) {
			resp.sendRedirect(REDIRECT_LIST_COMPUTERS);
		}else {
			req.setAttribute("id_computer", id.get());
			
			this.getServletContext()
			.getRequestDispatcher(VIEW_EDIT_COMPUTER)
			.forward(req, resp);
		}
		
		

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		
		String name = req.getParameter(COMPUTER_NAME);
		String introduced = req.getParameter(INTRODUCED_DATE);
		String discontinued = req.getParameter(DISCONTINUED_DATE);
		String companyId = req.getParameter(COMPANY_ID);
		
		ComputerServices computerService = new ComputerServices();
		Optional<Timestamp> timestampIntr = TimeStampMapper.simpleStringToTimestamp(introduced);
		Optional<Timestamp> timestampDisc = TimeStampMapper.simpleStringToTimestamp(discontinued);

		Optional<Long> company = Optional.empty();
		if(companyId != null) {
			company = Optional.of(Long.parseLong(companyId));
		}
		
		Computer c = new ComputerBuilder()
						.withName(Optional.of(name))
						.withCompanyId(company)
						.withIntroduced(timestampIntr)
						.withDiscontinued(timestampDisc)
						.build();
		
		try {
			computerService.updateComputer(c);
			resp.sendRedirect(REDIRECT_LIST_COMPUTERS);
		} catch (ValidatorException | UpdateComputerError e) {
			resp.sendRedirect(ERROR_500);
		}


	}
	
	
	private Optional<Long> getParamId(HttpServletRequest req) {
		Optional<Long> size;
		if(req.getParameter("id")!=null) {
			size = Optional.of(Long.parseLong(req.getParameter("id")));
		}else{
			size = Optional.empty();
		}
		return size;
	}


}
