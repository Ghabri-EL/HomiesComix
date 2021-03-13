package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query("FROM User where email = ?1 and password = ?2")
    User findUserByEmailAndPassword(String email, String password);

    @Query("FROM User where email = ?1")
    User findUserByEmail(String email);
}
