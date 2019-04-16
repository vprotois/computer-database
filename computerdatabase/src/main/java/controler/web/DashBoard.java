package controler.web;

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
import exception.ComputerNotFoundException;
import exception.UpdateComputerError;
import model.Pages;
import model.dto.DTOComputer;
import services.ComputerServices;

@Controller
public class DashBoard {


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

	
	@GetMapping({"/dashboard","/"})
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
			} catch (UpdateComputerError e) {
				notDeleted.put(id, e.getMessage());
			}
		});

		model.addAttribute("notDeletedMap", notDeleted);
		return "dashboard";
	}



}
