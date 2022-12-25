package com.book.alone.web.dto;

import java.time.LocalDateTime;

import com.book.alone.domain.Posts;

import lombok.Getter;

@Getter
public class PostsListResponse {

	private Long id;
	private String title;
	private String author;
	private LocalDateTime modifiedDate;

	public PostsListResponse(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.author = entity.getAuthor();
		this.modifiedDate = entity.getModifiedDate();
	}
}
