package com.booking.app;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@SpringBootTest(classes = BookmyhikingApplication.class)
class BookmyhikingApplicationTests {

	@Test
	void contextLoadTest_PassWhenNoException() {
	}

}
