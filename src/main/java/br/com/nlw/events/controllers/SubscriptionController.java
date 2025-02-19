package br.com.nlw.events.controllers;

import br.com.nlw.events.dtos.SubscriptionResponse;
import br.com.nlw.events.models.User;
import br.com.nlw.events.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping({"/subscription/{prettyName}", "/subscription/{prettyName}/{userIndicadorId}"})
    public ResponseEntity<Object> createSubscription(@PathVariable String prettyName,
                                                     @RequestBody User subscriber,
                                                     @PathVariable (required = false) Integer userIndicadorId) {

            SubscriptionResponse res = subscriptionService.createNewSubscription(prettyName, subscriber, userIndicadorId);

            if (res != null) {
                return ResponseEntity.ok().body(res);
            }
            return ResponseEntity.badRequest().build();
    }
}
