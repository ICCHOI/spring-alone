package com.book.alone.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.alone.service.PostsService;
import com.book.alone.web.dto.PostsSaveRequest;
import com.book.alone.web.dto.PostsResponse;
import com.book.alone.web.dto.PostsUpdate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostsApiController {

	private final PostsService postsService;

	@PostMapping
	public Long save(@RequestBody PostsSaveRequest dto) {
		return postsService.save(dto);
	}

	@PutMapping("/{id}")
	public Long update(@PathVariable Long id, @RequestBody PostsUpdate dto) {
		return postsService.update(id, dto);
	}

	@GetMapping("/{id}")
	public PostsResponse findById(@PathVariable Long id) {
		return postsService.findById(id);
	}

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable Long id) {
		postsService.delete(id);
		return id;
	}
}
