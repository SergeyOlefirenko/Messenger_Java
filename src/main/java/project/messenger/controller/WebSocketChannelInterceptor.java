//package project.messenger.controller;
//
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.messaging.support.ChannelInterceptorAdapter;
//
//public class WebSocketChannelInterceptor extends ChannelInterceptorAdapter {
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//            // Добавляем статус "PRIVATE_CHAT" при подключении
//            accessor.getSessionAttributes().put("status", "PRIVATE_CHAT");
//        }
//        return message;
//    }
//}

