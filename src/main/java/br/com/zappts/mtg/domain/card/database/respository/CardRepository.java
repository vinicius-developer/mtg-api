package br.com.zappts.mtg.domain.card.database.respository;

import br.com.zappts.mtg.domain.card.database.entities.CardEntity;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
    Optional<CardEntity> findByListAndNameAndEditionAndLanguageAndIsFoilAndPrice(ListEntity list,
                                                                       String name,
                                                                       String edition,
                                                                       String cardLanguage,
                                                                       Boolean isFoil,
                                                                       String price
    );
}
