package br.com.zappts.mtg.domain.list.database.repository;

import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import org.springframework.data.repository.CrudRepository;

public interface ListRepository extends CrudRepository<ListEntity, Long> {
}
