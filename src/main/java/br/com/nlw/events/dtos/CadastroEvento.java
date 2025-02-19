package br.com.nlw.events.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.nlw.events.models.Event;

public record CadastroEvento(
		
		Integer id,
		
		String title,
		
		String prettyName,
		
		String location,

		Double price,

		LocalDate startDate,

		LocalDate endDate,

		LocalTime startTime,

		LocalTime endTime) {
	
	public CadastroEvento(Event event) {
		this(
				event.getEventId(),
				event.getTitle(),
				event.getPrettyName(),
				event.getLocation(),
				event.getPrice(),
				event.getStartDate(),
				event.getEndDate(),
				event.getStartTime(),
				event.getEndTime()
			);		
	}
}
