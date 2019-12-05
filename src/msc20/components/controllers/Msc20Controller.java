package msc20.components.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import msc20.components.services.LoginService;
import msc20.components.services.UserManagmentService;

@Controller
@RequestMapping("/")
public class Msc20Controller {

	@Autowired
	protected LoginService loginService;

	@Autowired
	protected UserManagmentService userManagmentService;

	@GetMapping("/")
	@ResponseBody
	public String begin(HttpSession session, Model model) {
		return "sei nella roooot";
	}

	protected JSONObject createJsonObject(HttpServletResponse response) {
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		return json;
	}

}
