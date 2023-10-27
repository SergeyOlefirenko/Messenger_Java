package project.messenger.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.messenger.model.User;
import project.messenger.repository.UserRepository;

import java.util.Optional;
@Data
@RequiredArgsConstructor
@Service
public class UserService {

//    @Autowired
    private static UserRepository userRepository;
    public static Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}

