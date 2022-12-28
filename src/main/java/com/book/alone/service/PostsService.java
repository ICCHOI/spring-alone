package com.book.alone.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.alone.domain.PostRepository;
import com.book.alone.domain.Posts;
import com.book.alone.web.dto.PostsListResponse;
import com.book.alone.web.dto.PostsResponse;
import com.book.alone.web.dto.PostsSaveRequest;
import com.book.alone.web.dto.PostsUpdate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

	private final PostRepository postRepository;

	@Transactional
	public Long save(PostsSaveRequest dto) {
		return postRepository.save(dto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdate dto) {
		Posts posts = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

		posts.update(dto.getTitle(), dto.getContent());

		return id;
	}

	@Transactional(readOnly = true)
	public PostsResponse findById(Long id) {
		Posts entity = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));


		return new PostsResponse(entity);
	}

	@Transactional(readOnly = true)
	public List<PostsListResponse> findAllDesc() {
		return postRepository.findAllDesc().stream()
			.map(PostsListResponse::new)
			.toList();
	}

	@Transactional
	public void delete(Long id) {
		Posts posts = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

		postRepository.delete(posts);
	}
}
