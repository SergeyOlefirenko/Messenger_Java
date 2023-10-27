package project.messenger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import project.messenger.dto.UserDto;
import project.messenger.model.Message;
import project.messenger.dto.MessageDto;
import project.messenger.model.Status;
import project.messenger.model.User;
import project.messenger.repository.MessageRepository;
import project.messenger.repository.UserRepository;

import java.util.Date;

@RestController
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/join/User")
    @SendTo("/chatroom/public")
    public Message joinUser(@Payload UserDto dto) {
        User user = userRepository.save(new User(dto.getUsername()));
        Message message = new Message();
        message.setStatus(Status.JOIN);
        message.setCreatedAt(new Date());
        message.setSender(user);
        // Сохранение сообщения в базе данных
        Message savedMessage = messageRepository.save(message);
        return savedMessage;
    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message publicMessage(@Payload MessageDto dto) {
        Message message = new Message();
        message.setMessage(dto.getMessage());
//        message.setSender(userRepository.findById(dto.getSenderId()).get());
        message.setSender(userRepository.findById(dto.getSenderId()).orElse(null));
        message.setCreatedAt(new Date());
        // Сохранение сообщения в базе данных
        Message savedMessage = messageRepository.save(message);
        return savedMessage;
    }

    @MessageMapping("/private-message")
    public Message privateMessage(@Payload MessageDto dto) {
        Message message = new Message();
        message.setMessage(dto.getMessage());
//        message.setSender(userRepository.findById(dto.getSenderId()).get());
        message.setSender(userRepository.findById(dto.getSenderId()).orElse(null));
//        message.setReceiver(userRepository.findById(dto.getSenderId()).get());
        message.setReceiver(userRepository.findById(dto.getSenderId()).orElse(null));
        message.setCreatedAt(new Date());
        // Сохранение приватного сообщения в базе данных
        Message savedMessage = messageRepository.save(message);
        simpMessagingTemplate.convertAndSendToUser(savedMessage.getReceiver().getUsername(), "/private", savedMessage);
        return savedMessage;
    }
    // Добавить методы для редактирования, удаления и получения сообщений
}

