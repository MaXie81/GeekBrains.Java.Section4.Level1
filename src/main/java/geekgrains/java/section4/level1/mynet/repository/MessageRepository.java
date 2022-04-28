package geekgrains.java.section4.level1.mynet.repository;

import geekgrains.java.section4.level1.mynet.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByAuthorUserId(Long id);
}
