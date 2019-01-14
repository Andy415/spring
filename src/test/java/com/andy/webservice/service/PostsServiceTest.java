package com.andy.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.andy.webservice.domain.posts.Posts;
import com.andy.webservice.domain.posts.PostsRepository;
import com.andy.webservice.dto.PostsMainResponseDto;
import com.andy.webservice.dto.posts.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

	@Autowired
	private PostsService postsService;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void DTO데이터가_posts테이블에_저장된다() {
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
				.author("andy@abcd.com")
				.content("테스트")
				.title("테스트 타이틀")
				.build();
		
		postsService.save(dto);
		
		Posts posts = postsRepository.findAll().get(0);
		assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
		assertThat(posts.getContent()).isEqualTo(dto.getContent());
		assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
	}
	
	@Test
	public void 게시글_시간역순불러오기() {
		List<Posts> postsList = postsRepository.findAll();
		
		List<PostsMainResponseDto> postsListDesc = postsService.findAllDesc();
		
		assertThat(postsList.get(0).getTitle()).isEqualTo(postsListDesc.get(1).getTitle());
		assertThat(postsList.get(1).getTitle()).isEqualTo(postsListDesc.get(0).getTitle());
	}
}
