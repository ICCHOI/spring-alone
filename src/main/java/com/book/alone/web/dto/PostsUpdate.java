package com.book.alone.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdate {

	private String title;
	private String content;

	@Builder
	public PostsUpdate(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
