package project.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.messenger.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Добавить необходимые методы для работы с сообщениями
}
