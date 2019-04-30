package com.excilys.webcontroler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserControler {

	
	@GetMapping({"/login"})
	public String loginPage() {
		return "login";
	}
	
	
	@PostMapping({"/logged"})
	public String logedPage() {
		return "login";
	}
}
