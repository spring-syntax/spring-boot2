package ro.bytechnology.springboot2.db.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.bytechnology.springboot2.db.entities.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByRoleIgnoreCase() {
        // given
        User alex = new User("alex","Admin");
        User mihai = new User("mihai","User");
        entityManager.persist(alex);
        entityManager.persist(mihai);
        entityManager.flush();

        // when
        List<User> actual = userRepository.findByRoleIgnoreCase(alex.getRole());

        // then
        assertAll(() -> assertEquals(1,actual.size()),
                () -> assertEquals("alex",actual.get(0).getName()));
    }
}
