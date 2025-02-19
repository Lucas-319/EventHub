package br.com.nlw.events.repositorys;

import br.com.nlw.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
	
	public Event findByPrettyName(String prettyName);
	
}
