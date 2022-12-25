package com.book.alone.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.book.alone.config.auth.LoginUser;
import com.book.alone.config.auth.dto.SessionUser;
import com.book.alone.service.PostsService;
import com.book.alone.web.dto.PostsResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {

	private final PostsService postsService;

	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) {
		model.addAttribute("posts", postsService.findAllDesc());

		if (user != null) {
			model.addAttribute("userName", user.getName());
		}

		return "index";
	}

	@GetMapping("/posts/save")
	public String postSave() {
		return "posts-save";
	}

	@GetMapping("/posts/update/{id}")
	public String postUpdate(@PathVariable Long id, Model model) {

		PostsResponse dto = postsService.findById(id);
		model.addAttribute("post", dto);

		return "posts-update";
	}

}
