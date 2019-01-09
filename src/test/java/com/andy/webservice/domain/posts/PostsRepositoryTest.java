package com.andy.webservice.domain.posts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
	/**
	~/h2-console JDBC URL : jdbc:h2:mem:testdb  
	**/
	
	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		/**
		테스트 메소드 끝날 때마다 repository 전체 비우는 코드  
		**/
		postsRepository.deleteAll();
	}
	
	@Test
	public void 게시글저장_불러오기() {
		postsRepository.save(Posts.builder()
				.title("테스트 게시글")
				.content("테스트 본문")
				.author("andy@abcd.com")
				.build());
		
		List<Posts> postsList = postsRepository.findAll();
		
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle(), is("테스트 게시글"));
		assertThat(posts.getContent(), is("테스트 본문"));
	}
	
	@Test
	public void BaseTimeEntity_등록 () {
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder()
				.title("테스트 게시글")
				.content("테스트 본문")
				.author("andy@abcd.com")
				.build());
		
		List<Posts> postsList = postsRepository.findAll();
		
		Posts posts = postsList.get(0);
		assertTrue(posts.getCreatedDate().isAfter(now));
		assertTrue(posts.getModifiedDate().isAfter(now));
	}

}
