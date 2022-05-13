package br.com.zappts.mtg.domain.card.database.respository;

import br.com.zappts.mtg.domain.card.database.entities.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
}
