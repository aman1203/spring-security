package person.zeng.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import person.zeng.security.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public User findByUsername(String username);
}
