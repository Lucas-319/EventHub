package br.com.nlw.events.services;

import br.com.nlw.events.dtos.EventDto;
import br.com.nlw.events.exceptions.EventNotFoundException;
import br.com.nlw.events.models.Event;
import br.com.nlw.events.repositorys.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
	
@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public EventDto addNewEvent(EventDto dados) {
		var event = new Event(dados);

		event = eventRepository.save(event);

		return new EventDto(event);
	}
	
	public List<EventDto> findAllEvents(){
		
		List<Event> events = eventRepository.findAll();
		
		if(events != null) {
			return events.stream().map(EventDto::new).toList();
		}else {
			throw new EventNotFoundException("No events found");
		}
	}
	
	public EventDto findEventByPrettyName(String prettyName) {
		
		var event = eventRepository.findByPrettyName(prettyName);
		
		if(event != null) {
			return new EventDto(event);
		}else {
			throw new EventNotFoundException("Event Not Found");
		}
	}
	
	public EventDto findEventById(Integer id) {
		var event = eventRepository.getReferenceById(id);
		
		if(event != null) {			
			return new EventDto(event);
		}else {
			throw new EventNotFoundException("Event Not Found");
		}
	}
}
