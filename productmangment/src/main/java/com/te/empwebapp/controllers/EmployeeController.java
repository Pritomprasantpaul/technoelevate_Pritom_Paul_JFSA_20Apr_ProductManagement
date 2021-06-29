package com.te.empwebapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.te.empwebapp.beans.EmployeeInfoBean;
import com.te.empwebapp.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired(required = false)
	private EmployeeService service;

	@GetMapping("/login")
	public String getEmpForm() {
		return "empLogin";
	}// getEmpForm

	@PostMapping("/login")
	public String authenticate(int id, String password, HttpServletRequest request, ModelMap map) {
		EmployeeInfoBean infoBean = service.authenticate(id, password);
		System.out.println(infoBean);
		if (infoBean != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedIn", infoBean);
//			session.setMaxInactiveInterval(3600);
			map.addAttribute("name", infoBean.getName());
			return "Home";
		} else {
			map.addAttribute("errMsg", "Invalid Credentials");
			return "empLogin";
		}
	}// authenticate

	@GetMapping("/logout")
	public String logout(HttpSession session, ModelMap map) {
		session.invalidate();
		map.addAttribute("msg", "logout successfull");
		return "empLogin";
	}// logout

//	@RequestMapping(value = "/searchPage", method = RequestMethod.GET)
//	public ModelAndView getSearchPage(HttpSession session, ModelMap map) {
//		return new  ModelAndView("redirect:/ProductController/searchPage");
//	}
	
}
