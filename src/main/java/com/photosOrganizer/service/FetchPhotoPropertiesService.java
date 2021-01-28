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
import com.drew.metadata.exif.ExifIFD0Directory;
import com.photosOrganizer.dao.PhotoBaseRepository;
import com.photosOrganizer.model.PhotoBase;

// documentation at https://javadoc.io/doc/com.drewnoakes/metadata-extractor/latest/index.html
// https://github.com/drewnoakes/metadata-extractor/wiki/Getting-Started-(Java)#2-query-tags
// https://javadoc.io/doc/com.drewnoakes/metadata-extractor/latest/com/drew/metadata/exif/ExifIFD0Directory.html

public class FetchPhotoPropertiesService {

	@Autowired
	PhotoBase photo;

	@Autowired
	PhotoBaseRepository photoRepo;

	public void saveAllPhotoFormUrl(String url) {

		List<String> photos = getPhotosNameFromUrl(url);

		Metadata metadata;

		for (String photo : photos) {
			metadata = getMetadataFromPhoto(url + "/" + photo);
			savePhotoBaseMetadata(metadata, photo, url);
		}
	}

	private List<String> getPhotosNameFromUrl(String url) {
		File fileObj = new File(url);
		return Arrays.asList(fileObj.list());
	}

	private Metadata getMetadataFromPhoto(String photoPathName) {
		try {
			return ImageMetadataReader.readMetadata(new File(photoPathName));
		} catch (ImageProcessingException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void savePhotoBaseMetadata(Metadata photoMetadata, String originalName, String originalUrlLocation) {

		Directory dir = photoMetadata.getFirstDirectoryOfType(ExifIFD0Directory.class);

		photo.setDate(dir.getDate(ExifIFD0Directory.TAG_DATETIME));
		photo.setDateOriginal(dir.getDate(ExifIFD0Directory.TAG_DATETIME_ORIGINAL));
		photo.setMake(dir.getString(ExifIFD0Directory.TAG_MAKE));
		photo.setModel(dir.getString(ExifIFD0Directory.TAG_MODEL));
		photo.setOriginalName(originalName);
		photo.setOriginalUrlLocation(originalUrlLocation);

		photoRepo.save(photo);

	}

	private void printTagsMetadata(Metadata metadata) {
		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				System.out.println(tag);
			}
		}
	}

}
