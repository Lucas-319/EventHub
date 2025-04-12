package br.com.nlw.events.controllers;

import br.com.nlw.events.dtos.SubscriptionResponse;
import br.com.nlw.events.models.User;
import br.com.nlw.events.services.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Operation(summary = "Inscrição para o evento e Inscrição por indicação", method = "POST")
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

    @Operation(summary = "Retorna o ranking geral de indicações para um evento específico", method = "GET")
    @GetMapping("/subscription/{prettyName}/ranking")
    public ResponseEntity<Object> generateRankingByEvent(@PathVariable String prettyName) {
        var ranking = subscriptionService.getCompleteRanking(prettyName);
        if (ranking.size() >= 3){
            return ResponseEntity.ok(ranking.subList(0, 3));
        }

        return ResponseEntity.ok(ranking);
    }

    @Operation(summary = "Retorna a posição de um usuário específico no ranking de um evento", method = "GET")
    @GetMapping("/subscription/{prettyName}/ranking/{userId}")
    public ResponseEntity<Object> generateRankingByEventAndUser(@PathVariable String prettyName,
                                                                @PathVariable Integer userId) {

        return ResponseEntity.ok(subscriptionService.getRankingByUser(prettyName, userId));
    }
}
