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

import dto.UserDTO;

@Controller()
@RequestMapping("/userManagment")
public class UserManagmentController extends Msc20Controller {

	@PostMapping("/signup")
	@ResponseBody
	public void signup(HttpSession session, HttpServletResponse response, @RequestBody UserDTO userDTO) {

		boolean exist = userManagmentService.existUser(userDTO.getUsername());
		JSONObject json = createJsonObject(response);
		try {
			if (exist) {
				json.put("status", "error");
			} else {
				userManagmentService.createUser(userDTO.getUsername(), userDTO.getEmail(), userDTO.getName(),
						userDTO.getSurname(), userDTO.getPassword(), userDTO.getCompany());
				UserDTO userDTOResponse = loginService.login(userDTO.getUsername(), userDTO.getPassword());
				json.put("user", JSONObject.wrap(userDTOResponse));
			}
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
