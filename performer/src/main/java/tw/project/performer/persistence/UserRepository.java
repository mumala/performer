package tw.project.performer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.project.performer.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    @Query("select u from UserEntity u where u.username = ?1")
    UserEntity findByUsername(String username);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM UserEntity u WHERE u.username = ?1")
    Boolean exsistsByUsername(String username);
    @Query("select u from UserEntity u where u.username = ?1 and u.password = ?2")
    UserEntity findByUsernameAndPassword(String username, String password);
}
