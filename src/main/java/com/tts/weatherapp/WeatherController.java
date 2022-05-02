package com.tts.weatherapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/* This class is going to be responsible for handling our web pages
 * requests...
 */
@Controller
public class WeatherController {
	//we don't have to instantiate it ourselves
	@Autowired
	WeatherService weatherService;
	
	/*This method will handle request to "/" */
	/* RequestMapping will handle any type of web request.
	 * GET/PUT/POST/PATCH/DELETE
	 */
	@GetMapping(value="/")
	public String getIndex(Model model) {
		/* a Model is an object that holds
		 * key value pairs--it is a lot like a HashMap...
		 * But it is specifically used to hold data you 
		 * want to pass between the Java code and your templating
		 * engine (Thymeleaf)
		 */
		
		Request request = new Request();
		model.addAttribute("request", request);
		return "index";
	}
	
	@PostMapping(value="/")
	public String postIndex(Request request, Model model) {
		Response data = weatherService.getForecast(request.getZipCode());
		model.addAttribute("data", data);
		return "index";
	}
}
