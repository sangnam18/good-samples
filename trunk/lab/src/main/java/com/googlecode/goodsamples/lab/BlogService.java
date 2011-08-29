package com.googlecode.goodsamples.lab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	private BlogRepository blogRepository;
	
	@Autowired
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	public List<Visitor> visitors() {
		return blogRepository.findAllVisitors();
	}

	public List<Article> articles() {
		return blogRepository.findAllArticles();
	}

	public Profile profile() {
		return blogRepository.findProfile();
	}

	public List<Comment> comments() {
		return blogRepository.findComments();
	}

}
