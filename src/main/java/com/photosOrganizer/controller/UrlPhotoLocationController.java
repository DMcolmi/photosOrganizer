package com.photosOrganizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.photosOrganizer.dao.UrlPhotoLocationRepository;
import com.photosOrganizer.model.UrlPhotoLocation;
import com.photosOrganizer.service.FetchPhotoPropertiesService;

@Controller
public class UrlPhotoLocationController {
	
	@Autowired
	private UrlPhotoLocationRepository repo;
	
	@Autowired
	private FetchPhotoPropertiesService service;
	
	@RequestMapping("/InsertUrlForm")
	public ModelAndView insertUrlForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("InsertUrlForm");
		
		return mv;
	}


	@RequestMapping("/InsertUrl")
	public ModelAndView insertUrl(UrlPhotoLocation url) {
		ModelAndView mv = new ModelAndView();
		if (!StringUtils.hasText(url.getUrl()) || !repo.findByUrl(url.getUrl()).isEmpty()){
			mv.setViewName("UrlLocationPresente");
		} else {
			repo.save(url);
			mv.setViewName("InsertUrlForm");
		}
		return mv;
	}
	
	@RequestMapping("/ShowUrls")
	public ModelAndView showUrls() {
		ModelAndView mv = new ModelAndView();
		
		List<UrlPhotoLocation> urls = repo.findAll();		
		
		mv.addObject("urls", urls);
		mv.setViewName("ShowUrlS");
		
		return mv;
		
	}
	
	
	
	@RequestMapping("/InsertPhotosFromUrl")
	public ModelAndView insertPhotosFromUrl(UrlPhotoLocation url) {
		ModelAndView mv = new ModelAndView();
		service.saveAllPhotoFormUrl(url.getUrl());
		System.out.println(url.getUrl());
		mv.setViewName("InsertUrlForm");
	
		return mv;
	}
	
}
