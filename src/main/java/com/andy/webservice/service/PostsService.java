package com.andy.webservice.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.andy.webservice.domain.posts.PostsRepository;
import com.andy.webservice.dto.posts.PostsSaveRequestDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {

	private PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto dto) {
		return postsRepository.save(dto.toEntity()).getId();
	}
}
