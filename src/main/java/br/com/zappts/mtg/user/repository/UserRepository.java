package br.com.zappts.mtg.user.repository;

import br.com.zappts.mtg.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

}
