package com.photosOrganizer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MovePhotoService {
	
	private static final String URL_SEPARATOR = "/";
	
	Logger log = LoggerFactory.getLogger(MovePhotoService.class);
	
	public void movePhoto(String originalUrl, String originalName, String newUrl, String newName) {
		
		try {
			Files.move(Paths.get(buildUrlAndFileName(originalUrl, originalName)), Paths.get(buildUrlAndFileName(newUrl, newName)), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		
	}
	
	private String buildUrlAndFileName(String url, String fileName) {		
		StringBuilder sb = new StringBuilder();
		sb.append(url).append(URL_SEPARATOR).append(fileName);
		return sb.toString();		
	}
	
//	public static void main(String[] args) {
//		MovePhotoService mps = new MovePhotoService();
//		mps.movePhoto("/home/davide/Downloads", "DATA_NOME_MAKE.jpg", "/home/davide/Documents", "DATA_NOME_MAKE2");
//	}

}
