package com.jsol.jsol;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "login";
	}

	// Admin
	@RequestMapping(value = "/login")
	public String Login(Locale locale, Model model) {
		return "login";
	}

	@RequestMapping(value = "/loginAction")
	public String LoginAction(Locale locale, Model model) {
		return "loginAction";
	}

	@RequestMapping(value = "/visitor")
	public String Visitor(Locale locale, Model model) {
		return "visitor";
	}

	@RequestMapping(value = "/statistic")
	public String Statistic(Locale locale, Model model) {
		return "statistic";
	}
	
	@RequestMapping(value = "/getStatistic")
	public String GetStatistic(Locale locale, Model model) {
		return "getStatistic";
	}

	@RequestMapping(value = "/manage")
	public String Manage(Locale locale, Model model) {
		return "manage";
	}

	@RequestMapping(value = "/regAction")
	public String RegAction(Locale locale, Model model) {
		return "regAction";
	}

	@RequestMapping(value = "/modAction")
	public String ModAction(Locale locale, Model model) {
		return "modAction";
	}

	@RequestMapping(value = "/delAction")
	public String DelAction(Locale locale, Model model) {
		return "delAction";
	}

	// User
	@RequestMapping(value = "/loginUser")
	public String LoginUser(Locale locale, Model model) {
		return "loginUser";
	}

	@RequestMapping(value = "/loginActionUser")
	public String LoginActionUser(Locale locale, Model model) {
		return "loginActionUser";
	}

	@RequestMapping(value = "/user")
	public String User(Locale locale, Model model) {
		return "user";
	}

	@RequestMapping(value = "/process")
	public String Process(Locale locale, Model model) {
		return "process";
	}
}
