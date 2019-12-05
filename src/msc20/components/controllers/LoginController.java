package msc20.components.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import msc20.components.model.User;


@Controller()
@RequestMapping("/userLogin")
public class LoginController extends Msc20Controller {

	
	@GetMapping("/login")
	@ResponseBody
	public void login(HttpSession session, HttpServletResponse response, @RequestParam String username,
			@RequestParam String password) {

		User user = loginService.login(username, password);

		JSONObject json = createJsonObject(response);

		try {
			if (user == null)
				json.append("status", "error");
			else
				json.append("status", "success");

			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
