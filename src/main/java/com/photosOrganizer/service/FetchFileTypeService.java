package com.photosOrganizer.service;

import org.springframework.stereotype.Service;

@Service 
public class FetchFileTypeService {

	public String getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
	}	
}
