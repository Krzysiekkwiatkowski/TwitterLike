package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(Long id);
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(Long id);
    List<Message> findByReceiver(User sender);
    List<Message> findByReceiverOrSender(User sender, User receiver);
}
