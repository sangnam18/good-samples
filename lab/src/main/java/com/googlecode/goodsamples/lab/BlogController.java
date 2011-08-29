package com.googlecode.goodsamples.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {
	private BlogService blogService;
	
	@Autowired
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(value = "/blog", method = {RequestMethod.GET})
	public ModelAndView section(String name) {
		ModelAndView result = new ModelAndView();
		result.addObject("visitors", blogService.visitors());
//		result.addObject(blogService.otherArticles());
//		result.addObject(blogService.lastArticle());
//		result.addObject(blogService.profile());
//		result.addObject(blogService.comments());
		return result;
	}
}
