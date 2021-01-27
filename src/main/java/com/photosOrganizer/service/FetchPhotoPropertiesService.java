package com.photosOrganizer.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.photosOrganizer.dao.PhotoBaseRepository;
import com.photosOrganizer.model.PhotoBase;

public class FetchPhotoPropertiesService {
	
	@Autowired
	PhotoBase photo;
	
	@Autowired
	PhotoBaseRepository photoRepo;
	
	public static List<String> getPhotosNameFromUrl(String url) {
		File fileObj = new File(url);		
		return Arrays.asList(fileObj.list());		
	}
	
	public static Metadata getMetadataFromPhoto(String photoPathName) {
		try {
			return ImageMetadataReader.readMetadata(new File(photoPathName));
		} catch (ImageProcessingException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void printTagsMetadata(Metadata metadata) {
		for (Directory directory : metadata.getDirectories()) {
		    for (Tag tag : directory.getTags()) {
		        System.out.println(tag);
		    }
		}
	}
	
	
	private void savePhotoMetadata(Metadata photoMetadata) {
		
		
		
	}
	
	
	public static void main(String[] args) {
		for(String s : getPhotosNameFromUrl("/home/davide/Downloads")) {
			System.out.println(s);
			printTagsMetadata(getMetadataFromPhoto("/home/davide/Downloads" + "/" + s));
			System.out.println();
		}
		
	}
}
