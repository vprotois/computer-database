package com.excilys.webcontroler.restcontroller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.dto.DTOComputer;
import com.excilys.exception.ComputerNotFoundException;
import com.excilys.service.ComputerServices;

@RestController
@RequestMapping("/api/computer")
public class ComputerRestController {

	private static Logger log= LoggerFactory.getLogger(ComputerRestController.class);


	@Autowired
	ComputerServices computerService;

	public ComputerRestController(ComputerServices computerService) {
		this.computerService = computerService;
	}

	@GetMapping
	public ResponseEntity<List<DTOComputer>> getAll() {
		Optional<List<DTOComputer>> optList = computerService.listDTOComputer();
		if(optList.isPresent()) {
			return new ResponseEntity<List<DTOComputer>>(optList.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<List<DTOComputer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<DTOComputer> getComputer(@PathVariable long id) {
		try {
			return new ResponseEntity<DTOComputer> ( computerService.getComputerDTO(id),HttpStatus.OK);
		} catch (ComputerNotFoundException e) {
			log.error(e.getMessage());
			return new ResponseEntity<DTOComputer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/search/{name}")
	public ResponseEntity<List<DTOComputer>> searchComputer(@PathVariable String name){
		Optional<List<DTOComputer>> optList = computerService.listComputerWithName(name,"id", true);
		if(optList.isPresent()){
			return new ResponseEntity<List<DTOComputer>>(optList.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<List<DTOComputer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping({"/search/{name}/{order}","/search/{name}/{order}/{asc}"})
	public ResponseEntity<List<DTOComputer>> searchComputer(@PathVariable String name,
															@PathVariable String order,
															@PathVariable(required = false) String asc){
		Optional<List<DTOComputer>> optList;
		if(StringUtils.isEmpty(asc)) {
			optList = computerService.listComputerWithName(name,order,true);			
		}else {
			optList = computerService.listComputerWithName(name,order,"asc".equals(asc));
		}
		if(optList.isPresent()){
			return new ResponseEntity<List<DTOComputer>>(optList.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<List<DTOComputer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
