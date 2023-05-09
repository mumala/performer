package tw.project.performer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tw.project.performer.model.UserEntity;
import tw.project.performer.persistence.UserRepository;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity){
        if(userEntity == null || userEntity.getUsername() == null){
            throw new RuntimeException("Invalid argument");
        }

        final String username = userEntity.getUsername();
        if(userRepository.exsistsByUsername(username)){
            log.warn("Username already exsists {}", username);
            throw new RuntimeException("Username already exsists");
        }

        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String username, final String password, final PasswordEncoder encoder){
        final UserEntity originalUser = userRepository.findByUsername(username);

        if(originalUser != null &&
            encoder.matches(password, originalUser.getPassword())){
            return originalUser;
        }
        return null;
    }
}
