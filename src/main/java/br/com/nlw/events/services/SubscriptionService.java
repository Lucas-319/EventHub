package br.com.nlw.events.services;

import br.com.nlw.events.dtos.SubscriptionResponse;
import br.com.nlw.events.exceptions.EventNotFoundException;
import br.com.nlw.events.exceptions.SubscriptionConflictException;
import br.com.nlw.events.exceptions.UserIndicadorNotFoundException;
import br.com.nlw.events.models.Subscription;
import br.com.nlw.events.models.User;
import br.com.nlw.events.repositorys.EventRepository;
import br.com.nlw.events.repositorys.SubscriptionRepository;
import br.com.nlw.events.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userIndicadorId) {

        var event = eventRepository.findByPrettyName(eventName);
        if(event == null) { //Caso alternativo 2
            throw new EventNotFoundException("Evento "+eventName+" nao encontrado.");
        }

        User userRecovered = userRepository.findByEmail(user.getEmail());
        if(userRecovered == null) { //Caso alternativo 1
            userRecovered = userRepository.save(user);
        }

        User indicador = null;
        if(userIndicadorId != null) {
             indicador = userRepository.findById(userIndicadorId).orElse(null);
            if (indicador == null) {
                throw new UserIndicadorNotFoundException("Usuario " + userIndicadorId + " indicador nao existe.");
            }
        }

        Subscription subscription = new Subscription();
        subscription.setEvent(event);
        subscription.setSubscriber(userRecovered);
        subscription.setIndication(indicador);

        Subscription tmpSubscription = subscriptionRepository.findByEventAndSubscriber(event, userRecovered);
        if(tmpSubscription != null) { //Caso alternativo 3
            throw new SubscriptionConflictException("Usuario "+ userRecovered.getName() + " ja inscrito no evento "+event.getTitle()+".");
        }

        Subscription res = subscriptionRepository.save(subscription);

        return new SubscriptionResponse(res.getSubscriptionNumber(), "http://codecraft.com/subscription/"+res.getEvent().getPrettyName()+"/"+res.getSubscriber().getId()+".");
    }
}
