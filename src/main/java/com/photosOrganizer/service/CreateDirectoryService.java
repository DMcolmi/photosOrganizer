package com.photosOrganizer.service;

import java.io.File;

public class CreateDirectoryService {
	
	public void createDirectory(String path) {
		File dir = new File(path);
		dir.mkdirs();
	}

}
