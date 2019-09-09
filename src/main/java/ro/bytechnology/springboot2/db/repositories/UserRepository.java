package ro.bytechnology.springboot2.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ro.bytechnology.springboot2.db.entities.User;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByRoleIgnoreCase(@Param("role") String userRole);
}
