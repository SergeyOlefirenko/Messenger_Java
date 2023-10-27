package project.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.messenger.model.User;

import java.util.Optional;

@Repository
//public interface UserRepository extends JpaRepository<Message, Long> {
//    User findByUsername(String senderName);
//    // Здесь нужно будет не забыть добавить методы для работы с сообщениями
//}
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findById(Long id);
}
