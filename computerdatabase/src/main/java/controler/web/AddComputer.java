package controler.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import exception.CreateComputerError;
import exception.ValidatorException;
import mapper.TimeStampMapper;
import model.Company;
import model.Computer;
import model.builders.ComputerBuilder;
import services.CompanyServices;
import services.ComputerServices;

@Configurable
@WebServlet(name = "AddComputer",urlPatterns= {"/add"})
public class AddComputer extends HttpServlet {	

	private static final long serialVersionUID = 6730501184846318246L;

	
	@Autowired
	private ComputerServices computerService;
	@Autowired
	private CompanyServices  companyService;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


		Optional<List<Company>> list = companyService.listCompanies();

		if(list.isPresent()) {
			req.setAttribute(ServletData.COMPANIES_ATTRIBUTE, list.get());
		}else {
			req.setAttribute(ServletData.COMPANIES_ATTRIBUTE, new ArrayList<Company>());
		}

		this.getServletContext()
		.getRequestDispatcher(ServletData.VIEW_ADD_COMPUTERS)
		.forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		String name = req.getParameter(ServletData.COMPUTER_NAME);
		String introduced = req.getParameter(ServletData.INTRODUCED_DATE);
		String discontinued = req.getParameter(ServletData.DISCONTINUED_DATE);
		String companyId = req.getParameter(ServletData.COMPANY_ID);

		Optional<Timestamp> timestampIntr = TimeStampMapper.simpleStringToTimestamp(introduced);
		Optional<Timestamp> timestampDisc = TimeStampMapper.simpleStringToTimestamp(discontinued);

		Optional<Long> company = Optional.empty();
		if(companyId != null && !"".equals(companyId)){
			company = Optional.of(Long.parseLong(companyId));
		}

		Computer c = new ComputerBuilder()
				.withName(name)
				.withCompanyId(company)
				.withIntroduced(timestampIntr)
				.withDiscontinued(timestampDisc)
				.build();
		try {
			computerService.addComputer(c);
			resp.sendRedirect(ServletData.REDIRECT_LIST_COMPUTERS);
		} catch (CreateComputerError  | ValidatorException e) {
			req.setAttribute("exception", e);
			req.getServletContext()
			.getRequestDispatcher(ServletData.VIEW_ERROR_500)
			.forward(req, resp);
		}



	}

}
