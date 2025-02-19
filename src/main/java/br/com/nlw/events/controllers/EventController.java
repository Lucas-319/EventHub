package br.com.nlw.events.controllers;

import br.com.nlw.events.dtos.CadastroEvento;
import br.com.nlw.events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

	@Autowired 
	private EventService eventService;
	
	@PostMapping("/events")
	public ResponseEntity<CadastroEvento> addNewEvent(@RequestBody CadastroEvento newEvent){
		
		return ResponseEntity.ok(eventService.addNewEvent(newEvent));		
	}
	
	@GetMapping("/events")
	public ResponseEntity<List<CadastroEvento>> getEvents(){
		return ResponseEntity.status(200).body(eventService.findAllEvents());
	}
	
	@GetMapping("/events/{prettyName}")
	public ResponseEntity<CadastroEvento> getEventByPrettyName(@PathVariable String prettyName){
		var event = eventService.findEventByPrettyName(prettyName);
		
		if(event != null) {
			return ResponseEntity.status(200).body(event);
		}		
		return ResponseEntity.status(404).build();
	}
	
	@GetMapping("/events/id/{id}")
	public ResponseEntity<CadastroEvento> getEventById(@PathVariable Integer id){
		var event = eventService.findEventById(id);
		
		if(event != null) {
			return ResponseEntity.status(200).body(event);
		}
		return ResponseEntity.status(404).build();
	}
}
