package br.com.zappts.mtg.domain.list.service;

import br.com.zappts.mtg.domain.list.dataStructure.CreateListDto;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import br.com.zappts.mtg.domain.list.database.repository.ListRepository;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService {

    private final ListRepository listRepository;

    public ListService(@Autowired ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public ListEntity create(CreateListDto createListDto, UserEntity user) {

        ListEntity list = new ListEntity(createListDto.getName(), user);

        return this.listRepository.save(list);

    }

}
