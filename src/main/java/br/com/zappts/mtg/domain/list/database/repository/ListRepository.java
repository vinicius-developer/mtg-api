package br.com.zappts.mtg.domain.list.database.repository;

import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ListRepository extends CrudRepository<ListEntity, Long> {

    Optional<ListEntity> findByUserAndId(UserEntity user, Long id);

}
