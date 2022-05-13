package br.com.zappts.mtg.domain.user.service;

import br.com.zappts.mtg.domain.user.dataStructure.UserCreateDto;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import br.com.zappts.mtg.domain.user.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(UserCreateDto userRequestCreateDto) {

        UserEntity userEntity = new UserEntity(
                userRequestCreateDto.getEmail(),
                userRequestCreateDto.getPassword()
        );

        this.userRepository.save(userEntity);

    }

    public boolean emailAlreadyExists(String email) {
        return this.userRepository
                .existsByEmail(email);
    }
}
