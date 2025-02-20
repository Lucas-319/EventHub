package br.com.nlw.events.repositorys;

import br.com.nlw.events.dtos.SubscriptionRankingItem;
import br.com.nlw.events.models.Event;
import br.com.nlw.events.models.Subscription;
import br.com.nlw.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    public Subscription findByEventAndSubscriber(Event event, User subscriber);

    @Query(value = " SELECT COUNT(subscription_number) as quantidade_indicadacoes, indication_user_id as id_indicador, user_name as nome_indicador " +
            " from tbl_subscription INNER JOIN tbl_user " +
            " ON tbl_subscription.indication_user_id = tbl_user.user_id " +
            " where indication_user_id IS NOT NULL\n" +
            " AND event_id = :eventId " +
            " GROUP BY indication_user_id " +
            " ORDER BY quantidade_indicadacoes DESC ", nativeQuery = true)
    public List<SubscriptionRankingItem> generateRanking(@Param("eventId")Integer eventId);
}

