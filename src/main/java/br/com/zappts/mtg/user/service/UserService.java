package br.com.zappts.mtg.user.service;

import br.com.zappts.mtg.user.entity.UserEntity;
import br.com.zappts.mtg.user.repository.UserRepository;
import br.com.zappts.mtg.user.dataStrucuture.request.UserRequestCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(UserRequestCreateDto userRequestCreateDto) {

        UserEntity userEntity = new UserEntity(
                userRequestCreateDto.getEmail(),
                userRequestCreateDto.getPassword()
        );

        this.userRepository.save(userEntity);

    }

    public boolean emailAlreadyExists(UserRequestCreateDto userRequestCreateDto) {
        return this.userRepository
                .existsByEmail(userRequestCreateDto.getEmail());
    }
}
