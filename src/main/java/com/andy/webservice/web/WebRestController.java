package com.andy.webservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.andy.webservice.dto.posts.PostsSaveRequestDto;
import com.andy.webservice.service.PostsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	
	private PostsService postsService;
	
	@ApiOperation(value="헬로")
	@GetMapping("/hello")
	public String hello() {
		return "HelloWorld";
	}
	
	@ApiOperation(value="게시글 등록")
	@PostMapping("/posts")
	public void savePosts(@RequestBody PostsSaveRequestDto dto) {
		postsService.save(dto);
	}

}
