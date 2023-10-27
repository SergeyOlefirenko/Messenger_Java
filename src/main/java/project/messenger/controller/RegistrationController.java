package project.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.messenger.dto.UserDto;
import project.messenger.model.User;
import project.messenger.repository.UserRepository;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @CrossOrigin
    @PostMapping("/register")
    @SendTo("/chatroom/public")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        try {
            // Создание User из DTO
            User user = new User(userDto.getUsername());
            // Сохранение пользователя в базе данных через UserRepository
            userRepository.save(user);
            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Не удалось зарегистрировать пользователя");
        }
    }
}

