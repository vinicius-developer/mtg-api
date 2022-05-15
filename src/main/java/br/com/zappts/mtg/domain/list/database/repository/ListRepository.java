package br.com.zappts.mtg.domain.list.database.repository;

import br.com.zappts.mtg.domain.list.dataStructure.ProjectIdAndName;
import br.com.zappts.mtg.domain.list.dataStructure.ShowListDto;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListRepository extends JpaRepository<ListEntity, Long> {

    Optional<ListEntity> findByUserAndId(UserEntity user, Long id);

    Page<ProjectIdAndName> findAllProjectedBy(Pageable pageable);

    boolean existsByUserAndId(UserEntity user, Long id);

}
