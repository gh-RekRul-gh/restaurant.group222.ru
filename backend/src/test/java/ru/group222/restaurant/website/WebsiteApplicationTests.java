package ru.group222.restaurant.website;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class WebsiteApplicationTests {

	@Test
	void contextLoads() {
	}

}
