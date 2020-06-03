package msc20.components.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import dto.CredentialsDTO;
import dto.UserDTO;



@Controller()
@RequestMapping("/userLogin")
public class LoginController extends Msc20Controller {

	
	@PostMapping("/login")
	@ResponseBody
	public void login(HttpSession session, HttpServletResponse response, @RequestBody CredentialsDTO credentials
			) {
		
		System.out.println("sono entarto in login");

		
		UserDTO userDTO = loginService.login(credentials.getUsername(),credentials.getPassword());
		JSONObject json = createJsonObject(response);
		
		try {
			if (userDTO == null)
				json.put("status", "error");
			else
				json.put("user", JSONObject.wrap(userDTO));
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
