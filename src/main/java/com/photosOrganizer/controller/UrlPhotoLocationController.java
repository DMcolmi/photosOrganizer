package com.photosOrganizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.photosOrganizer.dao.UrlPhotoLocationRepository;

@Controller
public class UrlPhotoLocationController {
	
	@Autowired
	private UrlPhotoLocationRepository repo;

}
