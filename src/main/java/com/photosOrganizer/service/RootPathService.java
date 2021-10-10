package com.photosOrganizer.service;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RootPathService {
	public static void main(String[] args) {
		
		Path pr = Paths.get(System.getProperty("user.home"));
		
		System.out.println(pr.toString());
	
	
	}

	
	
	
}
