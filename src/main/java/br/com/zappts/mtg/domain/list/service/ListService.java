package br.com.zappts.mtg.domain.list.service;

import br.com.zappts.mtg.domain.list.dataStructure.FormatListDto;
import br.com.zappts.mtg.domain.list.database.entities.ListEntity;
import br.com.zappts.mtg.domain.list.database.repository.ListRepository;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class ListService {

    private final ListRepository listRepository;

    public ListService(@Autowired ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public ListEntity create(FormatListDto createListDto, UserEntity user) {

        ListEntity list = new ListEntity(createListDto.getName(), user);

        return this.listRepository.save(list);

    }

    public ListEntity findListById(Long id) {

        Optional<ListEntity> list = this.listRepository.findById(id);

        if(list.isEmpty()) {
            throw new InvalidParameterException("Essa lista nao existe");
        }

        return list.get();

    }

    public void updateListName(Long id, FormatListDto formatListDto, UserEntity user) {
        Optional<ListEntity> optionalList = this.listRepository.findByUserAndId(user, id);

        if(optionalList.isEmpty()) {
            throw new InvalidParameterException("Essa lista nao existe");
        }

        ListEntity list = optionalList.get();

        list.setName(formatListDto.getName());

        this.listRepository.save(list);
    }
}
