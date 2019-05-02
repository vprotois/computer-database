package com.excilys.webcontroler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.builders.ComputerBuilder;
import com.excilys.dto.DTOComputer;
import com.excilys.exception.ComputerNotFoundException;
import com.excilys.exception.CreateComputerException;
import com.excilys.exception.UpdateComputerException;
import com.excilys.exception.ValidatorException;
import com.excilys.mapper.TimeStampMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Pages;
import com.excilys.service.CompanyServices;
import com.excilys.service.ComputerServices;

@Controller
public class ComputerControler {

	private static final String PAGE_COMPUTERS = "computerPage";
	private static final String NUMBER_COMPUTERS = "number_computers";
	private static final String NEXT_PAGE = "next_page_index";
	private static final String PREVIOUS_PAGE = "previous_page_index";
	private static final String PAGE_DATA = "page_data";
	private static final String PAGE_SIZE = "size";
	private static final String PAGE_ORDER = "order";
	private static final String PAGE_SEARCH = "search";


	@Autowired
	private ComputerServices computerService;
	@Autowired
	private CompanyServices  companyService;	
	

	
	@GetMapping({"/dashboard"})
	protected String listComputer(Model model,
						@RequestParam(name = "index",required = false,defaultValue="0") String index,
						@RequestParam(name = "size",required = false, defaultValue="10") String size,
						@RequestParam(name = "order",required = false,defaultValue="")String order,
						@RequestParam(name = "search",required = false,defaultValue="")String search,
						@RequestParam(name = "asc",required = false,defaultValue ="false")String asc) {

		Integer sizeInt = Integer.parseInt(size);
		Integer indexInt= Integer.parseInt(index);

		Optional<Pages<DTOComputer>> optpages = Optional.empty();

		optpages = getOptPages(sizeInt, indexInt, search, order, asc, computerService);

		if(!optpages.isPresent()) {
			model.addAttribute("exception", new ComputerNotFoundException("Error while getting the computer list"));
			return "500";
		}
		else {
			setDashboardAttributes(model, sizeInt, search, order, optpages);
			return "dashboard"; 	
		}
	}

	private Optional<Pages<DTOComputer>> getOptPages(Integer size, Integer index, String search, String order,
			String asc, ComputerServices computerServ) {
		Optional<Pages<DTOComputer>> optpages;
		if(search == null || "".equals(search)) {
			optpages = computerServ.pagesDTOComputer(size, index,order,"true".equals(asc));			
		}else{
			optpages = computerServ.pagesComputerWithName(search, size, index,order,"true".equals(asc));
		}
		return optpages;
	}

	private void setDashboardAttributes(Model model,Integer size, String search, String order,
			Optional<Pages<DTOComputer>> optpages) {
		Pages<DTOComputer> p = optpages.get();
		model.addAttribute(PAGE_COMPUTERS, p);
		model.addAttribute(NUMBER_COMPUTERS, p.getDataSize());
		model.addAttribute(PAGE_DATA, p.getPageData());
		model.addAttribute(NEXT_PAGE,p.nextIndex());
		model.addAttribute(PREVIOUS_PAGE,p.previousIndex());
		model.addAttribute(PAGE_SIZE, size);
		model.addAttribute(PAGE_ORDER, order);
		model.addAttribute(PAGE_SEARCH, search);
	}

	
	@PostMapping({"/delete"})
	protected String deleteComputers(Model model,
				@RequestParam(name = "selection",required = true) String toDeleteList){


		List<Long> list = Arrays.asList(toDeleteList.split(","))
				.stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());		

		Map<Long,String> notDeleted = new HashMap<>();
		
		list.forEach(id -> {
			try {
				computerService.deleteComputer(id);
			} catch (UpdateComputerException e) {
				notDeleted.put(id, e.getMessage());
			}
		});

		model.addAttribute("notDeletedMap", notDeleted);
		return "deleted";
	}
	
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

	@PostMapping("/add")
	protected String add(Model model,
			@RequestParam(name = ServletData.COMPUTER_NAME,required = true) String name,
			@RequestParam(name = ServletData.INTRODUCED_DATE,required = false) String introduced,
			@RequestParam(name = ServletData.DISCONTINUED_DATE,required = false) String discontinued,
			@RequestParam(name = ServletData.COMPANY_ID,required = false) String companyString
				) {
		
		Optional<Timestamp> timestampIntr = TimeStampMapper.simpleStringToTimestamp(introduced);
		Optional<Timestamp> timestampDisc = TimeStampMapper.simpleStringToTimestamp(discontinued);

		Optional<Company> company = Optional.empty();
		if(!"".equals(companyString)) {
			String[] companyArgs = companyString.split(" : ");
			company = Optional.of(new Company(Long.parseLong(companyArgs[0]),companyArgs[1]));
		}
		

		Computer c = new ComputerBuilder()
				.withName(name)
				.withCompany(company)
				.withIntroduced(timestampIntr)
				.withDiscontinued(timestampDisc)
				.build();
		try {
			computerService.addComputer(c);
			return "addComputer";
		} catch (CreateComputerException  | ValidatorException e) {
			model.addAttribute("exception", e);
			return "500";
		}
	}
	
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
		} catch (ValidatorException | UpdateComputerException e) {
			model.addAttribute("exception", e);
			return "500";
		}

	}

}
