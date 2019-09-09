package ro.bytechnology.springboot2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBoot2Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBoot2ApplicationTests {

	@Test
	void contextLoads() {
	}

}
