package com.photosOrganizer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.photosOrganizer.dao.PhotoBaseRepository;
import com.photosOrganizer.dao.UrlPhotoLocationRepository;
import com.photosOrganizer.model.UrlPhotoLocation;
import com.photosOrganizer.service.CleanMetadataService;
import com.photosOrganizer.service.FetchPhotoPropertiesService;

@Controller
public class UrlPhotoLocationController {
	
	Logger log = LoggerFactory.getLogger(UrlPhotoLocationController.class);
	
	@Autowired
	private UrlPhotoLocationRepository repo;
	
	@Autowired
	private FetchPhotoPropertiesService service;
	
	@Autowired
	private PhotoBaseRepository photoRepo;
	
	@Autowired
	private CleanMetadataService cleanDataService;
	
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
		log.info(url.getUrl());
		mv.setViewName("InsertUrlForm");
	
		return mv;
	}
	
	@RequestMapping("/FetchUrlAndExt")
	public ModelAndView fetchUrlAndExt() {
		ModelAndView mv = new ModelAndView();
		
		List<String> urls = photoRepo.fetchAllOriginalUrlLocation();
		
		List<String> extensions = photoRepo.fetchAllFileExtension();
		
		List<String> makes = photoRepo.fetchAllMake();
		
		List<String> models = photoRepo.fetchAllModel();
		
		mv.setViewName("ProcessByUrlAndExtension");
		
		mv.addObject("urls", urls);
		mv.addObject("extensions", extensions);
		mv.addObject("makes", makes);
		mv.addObject("models", models);
		
		return mv;
	}
	
	@RequestMapping("/cleanModelMetadata")
	public ModelAndView cleanModelMetadata(String model) {
		ModelAndView mv = new ModelAndView();
		
		cleanDataService.cleanWrongPhotoModelMetadata(model);
		
		mv.setViewName("InsertUrlForm");
	
		return mv;
	}
	
} 
