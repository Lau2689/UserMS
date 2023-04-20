package usermicroservice.repositories;


import org.springframework.data.jpa.repository.Query;
import usermicroservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, String> {
    @Query("select a from User a where a.name = :name")
    List<User> findByName(String name);
}
