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


@Controller()
@RequestMapping("/userManagment")
public class UserManagmentController extends Msc20Controller {

	@GetMapping("/signup")
	@ResponseBody
	public void signup(HttpSession session, HttpServletResponse response, @RequestParam String email,
			@RequestParam String username, @RequestParam String name, @RequestParam String surname,
			@RequestParam String password) {

		boolean exist = userManagmentService.existUser(username);
		JSONObject json = createJsonObject(response);
		try {
			if (exist) {
				json.append("status", "error");
			} else {
				userManagmentService.createUser(username, email, name, surname, password);
				json.append("status", "success");
			}
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
