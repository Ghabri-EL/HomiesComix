package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("FROM Admin where email = ?1 and password = ?2")
    Admin findAdminByEmailAndPassword(String email, String password);

    @Query("FROM Admin where email = ?1")
    Admin findAdminByEmail(String email);
}
