package com.photosOrganizer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.photosOrganizer.dao.PhotoBaseRepository;

@Service
public class CleanMetadataService {
	
	@Autowired
	PhotoBaseRepository pbr;
	
	public void cleanWrongPhotoModelMetadata (String checkboxInput) {
		List<String> modelsToKeep = new ArrayList<String>(Arrays.asList(checkboxInput.split(",")));
		
		pbr.cleanModelMetadata(modelsToKeep);
	}

}
