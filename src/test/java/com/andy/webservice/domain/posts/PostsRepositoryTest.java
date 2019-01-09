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
	
	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		/**
		�׽�Ʈ �޼ҵ� ���� ������ repository ��ü ���� �ڵ�  
		**/
		postsRepository.deleteAll();
	}
	
	@Test
	public void �Խñ�����_�ҷ�����() {
		postsRepository.save(Posts.builder()
				.title("�׽�Ʈ �Խñ�")
				.content("�׽�Ʈ ����")
				.author("andy@abcd.com")
				.build());
		
		List<Posts> postsList = postsRepository.findAll();
		
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle(), is("�׽�Ʈ �Խñ�"));
		assertThat(posts.getContent(), is("�׽�Ʈ ����"));
	}
	
	@Test
	public void BaseTimeEntity_��� () {
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder()
				.title("�׽�Ʈ �Խñ�")
				.content("�׽�Ʈ ����")
				.author("andy@abcd.com")
				.build());
		
		List<Posts> postsList = postsRepository.findAll();
		
		Posts posts = postsList.get(0);
		assertTrue(posts.getCreatedDate().isAfter(now));
		assertTrue(posts.getModifiedDate().isAfter(now));
	}

}