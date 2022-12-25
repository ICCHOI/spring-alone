package com.book.alone.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostsRepositoryTest {

	@Autowired
	PostRepository postRepository;

	@AfterEach
	void after() {
		postRepository.deleteAll();
	}

	@Test
	void 게시글저장_불러오기() {
		//given
		String title = "테스트 게시글";
		String content = "테스트 본문";

		postRepository.save(Posts.builder()
				.title(title)
				.content(content)
				.build());

		//when
		List<Posts> postsList = postRepository.findAll();

		//then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);
	}

	@Test
	void baseTimeEntity_등록() {
		//given
		LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
		postRepository.save(Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build());
		//when
		List<Posts> postsList = postRepository.findAll();

		//then
		Posts posts = postsList.get(0);

		assertThat(posts.getCreatedDate()).isAfter(now);
		assertThat(posts.getModifiedDate()).isAfter(now);
	}
}