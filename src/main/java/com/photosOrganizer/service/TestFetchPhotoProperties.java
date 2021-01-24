package com.photosOrganizer.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class TestFetchPhotoProperties {
	
	public static List<String> getPhotosName() {
		File fileObj = new File("/home/davide/Downloads/");
		
		return Arrays.asList(fileObj.list());		
	}
	
	public static Metadata getMetadata(String photoName) {
		try {
			return ImageMetadataReader.readMetadata(new File("/home/davide/Downloads/"+photoName));
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
	
	
	public static void main(String[] args) {
		for(String s : getPhotosName()) {
			System.out.println(s);
			printTagsMetadata(getMetadata(s));
			System.out.println();
		}
		
	}
}
