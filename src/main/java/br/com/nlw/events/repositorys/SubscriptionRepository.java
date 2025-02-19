package br.com.nlw.events.repositorys;

import br.com.nlw.events.models.Event;
import br.com.nlw.events.models.Subscription;
import br.com.nlw.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    public Subscription findByEventAndSubscriber(Event event, User subscriber);
}

