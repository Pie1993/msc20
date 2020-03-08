package msc20.components.controllers;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import dto.EventDTO;
import dto.EventRequestDTO;
import dto.EventsContainerDTO;
import dto.JoinDeleteEventRequestDTO;


@Controller()
@RequestMapping("/msc20Events")
public class EventController extends Msc20Controller {

	@PostMapping("/events")
	@ResponseBody
	public void events(HttpSession session, HttpServletResponse response,
			@RequestBody EventRequestDTO eventRequestDTO) {
		System.out.println("RICHIESTA EVENTI");
		
		System.out.println(JSONObject.wrap(eventRequestDTO).toString());
		EventsContainerDTO eventsContainerDTO = eventsService.getEvents(eventRequestDTO);
	
		
		System.out.println("Stampoooooooooooooooooooooooooooooo"+eventsContainerDTO.getUserEvents().size());
		for (EventDTO event : eventsContainerDTO.getUserEvents()) {
			System.out.println(event.getStructureName()+"  "+event.getId());
		}
		
		System.out.println("Stampoooooooooooooooooooooooooooooo");
		for (EventDTO event : eventsContainerDTO.getEvents()) {
			System.out.println(event.getStructureName()+"  "+event.getId());
		}
		
		System.out.println("Stampoooooooooooooooooooooooooooooo");
		for (EventDTO event : eventsContainerDTO.getOtherEvents()) {
			System.out.println(event.getStructureName()+"  "+event.getId());
		}
		System.out.println("Stampoooooooooooooooooooooooooooooo");
		
		
		
		JSONObject json = createJsonObject(response);
		try {
			
			json.put("events", JSONObject.wrap(eventsContainerDTO));
			
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	@PostMapping("/joinDeleteEvent")
	@ResponseBody
	public void joinDeleteEvent(HttpSession session, HttpServletResponse response,
			@RequestBody JoinDeleteEventRequestDTO joinDeleteEventRequestDTO) {

		System.out.println("JOIN DELETE EVENT");
		
		eventsService.joinDeleteEvent(joinDeleteEventRequestDTO);
		
		JSONObject json = createJsonObject(response);
		try {
			json.put("status", "success");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	}


}
