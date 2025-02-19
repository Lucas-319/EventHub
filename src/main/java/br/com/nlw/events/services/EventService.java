package br.com.nlw.events.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nlw.events.dtos.CadastroEvento;
import br.com.nlw.events.models.Event;
import br.com.nlw.events.repositorys.EventRepository;
	
@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public CadastroEvento addNewEvent(CadastroEvento dados) {
		var event = new Event(dados);

		event = eventRepository.save(event);

		return new CadastroEvento(event);
	}
	
	public List<CadastroEvento> findAllEvents(){
		
		List<Event> events = eventRepository.findAll();
		
		if(events != null) {
			return events.stream().map(CadastroEvento::new).toList();
		}
		
		return null;
	}
	
	public CadastroEvento findEventByPrettyName(String prettyName) {
		
		var event = eventRepository.findByPrettyName(prettyName);
		
		if(event != null) {
			return new CadastroEvento(event);
		}
		
		return null;				
	}
	
	public CadastroEvento findEventById(Integer id) {
		var event = eventRepository.getReferenceById(id);
		
		if(event != null) {			
			return new CadastroEvento(event);
		}
		
		return null;	
	}
}
