package es.hibernate.springbootdb.repository;

import es.hibernate.springbootdb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userEmail);
    User findByUserUsername(String userUsername);
}
