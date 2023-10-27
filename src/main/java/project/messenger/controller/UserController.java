package project.messenger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import project.messenger.model.Message;
import project.messenger.model.User;
import project.messenger.repository.MessageRepository;
import project.messenger.repository.UserRepository;
import project.messenger.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "/messages/{userId}")
    public List<Message> getAllMessageByUserId (@PathVariable Long userId){
        User user = userRepository.findById(userId).orElse(new User());
        return user.getMessages();
    }
    @CrossOrigin
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        //Нашел в интернете но по, моему, это - бесполезно
        if (user.getId() == null) {
            UUID newId = UUID.randomUUID();
            user.setId(Long.valueOf(newId.toString()));
        }

        userRepository.save(user);

        return ResponseEntity.ok("Пользователь успешно сохранен");
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = UserService.findById(id);

//        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
        if (user.isPresent()) {
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
}











//    @CrossOrigin
//    @GetMapping("/users/register")
//    public List<User> getAllMessages() {
//        return userRepository.findAll();
//    }
//    @GetMapping("/users/messages")
//    public List<User> getAllMessages() {
//        return userRepository.findAll();
//    }

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
//
//    public void doSomething() {
//        log.info("Information message");
//    }
//}





