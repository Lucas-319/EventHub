package br.com.nlw.events.dtos;

import br.com.nlw.events.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventDto(

		@Schema(description = "ID do evento (não deve ser enviado no cadastro)", hidden = true)
		Integer id,

		@Schema(description = "Título do evento", example = "CodeCraft Summit 2025")
		String title,

		@Schema(description = "PrettyName do evento (não deve ser enviado no cadastro)", hidden = true)
		String prettyName,

		@Schema(description = "Local do evento", example = "Online")
		String location,

		@Schema(description = "Preço do evento", example = "0.0")
		Double price,

		@Schema(description = "Data de início do evento", example = "2025-03-16")
		LocalDate startDate,

		@Schema(description = "Data de término do evento", example = "2025-03-18")
		LocalDate endDate,

		@Schema(description = "Hora de início do evento", example = "19:00:00")
		LocalTime startTime,

		@Schema(description = "Hora de término do evento", example = "21:00:00")
		LocalTime endTime) {
	
	public EventDto(Event event) {
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
