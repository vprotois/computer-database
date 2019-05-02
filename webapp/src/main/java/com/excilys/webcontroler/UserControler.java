package com.excilys.webcontroler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserControler {
	
	@GetMapping({"/login"})
	public String loginPage() {		
		return "login";
	}
	
}
