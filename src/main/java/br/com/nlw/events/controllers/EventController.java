package br.com.nlw.events.controllers;

import br.com.nlw.events.dtos.EventDto;
import br.com.nlw.events.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class EventController {

	@Autowired 
	private EventService eventService;

	@Operation(summary = "Cadastra um novo evento", method = "POST")
	@PostMapping("/events")
	public ResponseEntity<EventDto> addNewEvent(@RequestBody EventDto newEvent,
												UriComponentsBuilder uriComponentsBuilder){
		EventDto eventResponse = eventService.addNewEvent(newEvent);

		var uri = uriComponentsBuilder.path("/events/id/{id}").buildAndExpand(eventResponse.id()).toUri();

		return ResponseEntity.created(uri).body(eventResponse);
	}

	@Operation(summary = "Lista todos os eventos", method = "GET")
	@GetMapping("/events")
	public ResponseEntity<List<EventDto>> getEvents(){
		return ResponseEntity.status(200).body(eventService.findAllEvents());
	}

	@Operation(summary = "Lista um evento específico pelo prettyName do evento", method = "GET")
	@GetMapping("/events/{prettyName}")
	public ResponseEntity<EventDto> getEventByPrettyName(@PathVariable String prettyName){
		var event = eventService.findEventByPrettyName(prettyName);
		
		if(event != null) {
			return ResponseEntity.status(200).body(event);
		}		
		return ResponseEntity.status(404).build();
	}

	@Operation(summary = "Lista um evento específico pelo ID", method = "GET")
	@GetMapping("/events/specific")
	public ResponseEntity<EventDto> getEventById(@RequestParam Integer id){
		var event = eventService.findEventById(id);
		
		if(event != null) {
			return ResponseEntity.status(200).body(event);
		}
		return ResponseEntity.status(404).build();
	}
}
