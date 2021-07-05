package bts.twitter.repository;

import bts.twitter.model.Message;
import bts.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
