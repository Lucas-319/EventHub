package br.com.nlw.events.services;

import br.com.nlw.events.dtos.SubscriptionRankingByUser;
import br.com.nlw.events.dtos.SubscriptionRankingItem;
import br.com.nlw.events.dtos.SubscriptionResponse;
import br.com.nlw.events.exceptions.EventNotFoundException;
import br.com.nlw.events.exceptions.SubscriptionConflictException;
import br.com.nlw.events.exceptions.UserIndicatorNotFoundException;
import br.com.nlw.events.models.Event;
import br.com.nlw.events.models.Subscription;
import br.com.nlw.events.models.User;
import br.com.nlw.events.repositorys.EventRepository;
import br.com.nlw.events.repositorys.SubscriptionRepository;
import br.com.nlw.events.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userIndicatorId) {

        var event = eventRepository.findByPrettyName(eventName);
        if(event == null) { //Caso alternativo 2
            throw new EventNotFoundException("Event "+eventName+" Not Found.");
        }

        User userRecovered = userRepository.findByEmail(user.getEmail());
        if(userRecovered == null) { //Caso alternativo 1
            userRecovered = userRepository.save(user);
        }

        User indicador = null;
        if(userIndicatorId != null) {
             indicador = userRepository.findById(userIndicatorId).orElse(null);
            if (indicador == null) {
                throw new UserIndicatorNotFoundException("Indicator user " + userIndicatorId + " does not exist.");
            }
        }

        Subscription subscription = new Subscription();
        subscription.setEvent(event);
        subscription.setSubscriber(userRecovered);
        subscription.setIndication(indicador);

        Subscription tmpSubscription = subscriptionRepository.findByEventAndSubscriber(event, userRecovered);
        if(tmpSubscription != null) { //Caso alternativo 3
            throw new SubscriptionConflictException("User "+ userRecovered.getName() + " already registered for the event: "+event.getTitle()+".");
        }

        Subscription res = subscriptionRepository.save(subscription);

        return new SubscriptionResponse(res.getSubscriptionNumber(), "http://codecraft.com/subscription/"+res.getEvent().getPrettyName()+"/"+res.getSubscriber().getId()+".");
    }

    public List<SubscriptionRankingItem> getCompleteRanking(String prettyName) {
        Event event = eventRepository.findByPrettyName(prettyName);
        if(event == null) {
            throw new EventNotFoundException("There is no ranking for this event: "+prettyName+".");
        }

        return subscriptionRepository.generateRanking(event.getEventId());
    }

    public SubscriptionRankingByUser getRankingByUser(String prettyName,Integer userId) {
        List<SubscriptionRankingItem> ranking = getCompleteRanking(prettyName);

        SubscriptionRankingItem item = ranking.stream().filter(i -> i.userId().equals(userId)).findFirst().orElse(null);
        if(item == null) {
            throw new UserIndicatorNotFoundException("There are no user "+userId+" in the system.");
        }

        Integer posicao = IntStream.range(0, ranking.size())
                          .filter(pos -> ranking.get(pos).userId().equals(userId))
                          .findFirst().getAsInt();

        return new SubscriptionRankingByUser(item, posicao+1);
    }
}
