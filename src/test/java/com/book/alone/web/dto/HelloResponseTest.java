package com.book.alone.web.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloResponseTest {

	@Test
	void lombok_test() {
		//given
		String name = "test";
		int amount = 1000;

		//when
		HelloResponse dto = new HelloResponse(name, amount);

		//then
		assertThat(dto.getName()).isEqualTo(name);
		assertThat(dto.getAmount()).isEqualTo(amount);
	}
}