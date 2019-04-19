package controler.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import exception.UpdateComputerError;
import exception.ValidatorException;
import mapper.TimeStampMapper;
import model.Company;
import model.Computer;
import model.builders.ComputerBuilder;
import services.CompanyServices;
import services.ComputerServices;

@Controller
//@WebServlet(name = "EditComputer",urlPatterns= {"/edit"})
public class EditComputer  {


	@Autowired
	private CompanyServices  companyService;
	@Autowired
	private ComputerServices computerService;

	@GetMapping({"/edit"})
	protected String editPage(Model model,
			@RequestParam(name = "id",required = true) String idString
			){
		
		Optional<Long> id = Optional.ofNullable(Long.parseLong(idString));
		Optional<List<Company>> list = companyService.listCompanies();
		if(list.isPresent()) {
			model.addAttribute(ServletData.COMPANIES_ATTRIBUTE, list.get());
		}else {
			model.addAttribute(ServletData.COMPANIES_ATTRIBUTE, new ArrayList<Company>());
		}
		
		if(!id.isPresent()) {
			model.addAttribute("exception",new Exception("Should have an id"));
			return "500";
		}else {
			model.addAttribute("id_computer", id.get());
			return "editComputer";
		}
	}

	@PostMapping({"/edit"})
	protected String editComputer(Model model,
			@RequestParam(name = "id",required = true) String idString,
			@RequestParam(name = ServletData.COMPUTER_NAME,required = true) String name,
			@RequestParam(name = ServletData.INTRODUCED_DATE,required = false) String introduced,
			@RequestParam(name = ServletData.DISCONTINUED_DATE,required = false) String discontinued,
			@RequestParam(name = ServletData.COMPANY_ID,required = false) String companyId
				)  {
		
		Optional<Timestamp> timestampIntr = TimeStampMapper.simpleStringToTimestamp(introduced);
		Optional<Timestamp> timestampDisc = TimeStampMapper.simpleStringToTimestamp(discontinued);

		Optional<Long> company = Optional.empty();
		if(companyId != null && !"".equals(companyId)){
			company = Optional.of(Long.parseLong(companyId));
		}
		
		Computer c = new ComputerBuilder()
						.withId(Long.parseLong(idString))
						.withName(name)
						.withCompanyId(company)
						.withIntroduced(timestampIntr)
						.withDiscontinued(timestampDisc)
						.build();
		
		try {
			computerService.updateComputer(c);
			return "editComputer";
		} catch (ValidatorException | UpdateComputerError e) {
			model.addAttribute("exception", e);
			return "500";
		}

	}


}
