package com.bitsnbyte.productlist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import com.bitsnbyte.productlist.config.SecurityTestConfig;

@SpringBootTest
@Import(SecurityTestConfig.class)
@ActiveProfiles("test")
class ProductlistApplicationTests {

	@Test
	void contextLoads() {
	}

}
