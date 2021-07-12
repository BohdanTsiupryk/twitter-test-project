package bts.twitter.repository;

import bts.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByName(String userName);

    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmationCode(String code);
}
