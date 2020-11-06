package br.com.fico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping
	public String index(Model model) {
		model.addAttribute("module", "dash");
		return "index";
	}

	@RequestMapping("/payments")
	public String payments(Model model) {
		model.addAttribute("module", "payments");
		return "launch";
	}
	
	@RequestMapping("/receipts")
	public String receipts(Model model) {
		model.addAttribute("module", "receipts");
		return "launch";
	}
}
