package controler.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import exception.CreateComputerError;
import exception.ValidatorException;
import mapper.TimeStampMapper;
import model.Company;
import model.Computer;
import model.builders.ComputerBuilder;
import services.CompanyServices;
import services.ComputerServices;

@Controller
public class AddComputer {	
	
	@Autowired
	private ComputerServices computerService;
	@Autowired
	private CompanyServices  companyService;	
	
	@GetMapping("/add")
	protected String showPage(Model model){


		Optional<List<Company>> list = companyService.listCompanies();

		if(list.isPresent()) {
			model.addAttribute(ServletData.COMPANIES_ATTRIBUTE, list.get());
		}else {
			model.addAttribute(ServletData.COMPANIES_ATTRIBUTE, new ArrayList<Company>());
		}

		return "addComputer";

	}

	@PostMapping
	protected String add(Model model,
			@RequestParam(name = ServletData.COMPUTER_NAME,required = true) String name,
			@RequestParam(name = ServletData.INTRODUCED_DATE,required = false) String introduced,
			@RequestParam(name = ServletData.DISCONTINUED_DATE,required = false) String discontinued,
			@RequestParam(name = ServletData.COMPANY_ID,required = false) String companyId
				) throws IOException, ServletException {
		
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
			return "addComputer";
		} catch (CreateComputerError  | ValidatorException e) {
			model.addAttribute("exception", e);
			return "500";
		}



	}

}
