package itmo.lab.web4.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import itmo.lab.web4.models.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

}
