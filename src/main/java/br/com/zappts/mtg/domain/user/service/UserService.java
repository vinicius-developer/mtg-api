package br.com.zappts.mtg.domain.user.service;

import br.com.zappts.mtg.domain.user.dataStructure.UserCreateDto;
import br.com.zappts.mtg.domain.user.database.entities.UserEntity;
import br.com.zappts.mtg.domain.user.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Optional;

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
                userRequestCreateDto.getPassword(),
                userRequestCreateDto.getUsername()
        );

        this.userRepository.save(userEntity);

    }

    public boolean emailAlreadyExists(String email) {
        return this.userRepository
                .existsByEmail(email);
    }

    public boolean usernameAlreadyExists(String username) {
        return this.userRepository
                .existsByUsername(username);
    }

    public UserEntity getUserById(Long userId) {
        Optional<UserEntity> OptionalUser = this.userRepository.findById(userId);

        if(OptionalUser.isEmpty()) {
            throw new InvalidParameterException("Usuario com este id não existe");
        }

        return OptionalUser.get();
    }
}
