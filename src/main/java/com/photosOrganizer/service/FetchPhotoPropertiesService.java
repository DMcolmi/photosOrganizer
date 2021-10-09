package com.photosOrganizer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.file.FileSystemDirectory;
import com.photosOrganizer.dao.PhotoBaseRepository;
import com.photosOrganizer.model.PhotoBase;



// documentation at https://javadoc.io/doc/com.drewnoakes/metadata-extractor/latest/index.html
// https://github.com/drewnoakes/metadata-extractor/wiki/Getting-Started-(Java)#2-query-tags
// https://javadoc.io/doc/com.drewnoakes/metadata-extractor/latest/com/drew/metadata/exif/ExifIFD0Directory.html

@Service
public class FetchPhotoPropertiesService {

	@Autowired
	PhotoBaseRepository photoRepo;
	
	@Autowired
	FetchFileTypeService ftps;
	
	Logger log = LoggerFactory.getLogger(FetchPhotoPropertiesService.class);

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
			log.error(e.getMessage());
		}
		return null;
	}

	@Transactional
	private void savePhotoBaseMetadata(Metadata photoMetadata, String originalName, String originalUrlLocation) {

		PhotoBase photo = new PhotoBase();		
		
		try {
			Directory dirFD0 = photoMetadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
			Directory dirSubIFD = photoMetadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
			photo.setDate(dirFD0.getDate(ExifIFD0Directory.TAG_DATETIME));
			photo.setMake(dirFD0.getString(ExifIFD0Directory.TAG_MAKE));
			photo.setModel(dirFD0.getString(ExifIFD0Directory.TAG_MODEL));
			photo.setDateOriginal(dirSubIFD.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
		} catch (NullPointerException e){
			log.error(originalName + " ha il tag ExifIFD0Directory nullo");
			log.error(e.getMessage());
			try {
				Directory dirFileSys = photoMetadata.getFirstDirectoryOfType(FileSystemDirectory.class);
				photo.setDate(dirFileSys.getDate(FileSystemDirectory.TAG_FILE_MODIFIED_DATE));				
			} catch(NullPointerException e2){
				log.error(e2.getMessage());
				return;
			}
		}
			
		photo.setOriginalName(originalName);
		photo.setOriginalUrlLocation(originalUrlLocation);
		photo.setFileExtension(ftps.getFileExtension(originalName));

		photoRepo.saveAndFlush(photo);

	}

	private void printTagsMetadata(Metadata metadata) {
		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				log.info(tag.toString());
			}
		}
	}
	
		
	public static void main(String[] args) throws FileNotFoundException {
		
//		File file = new File("/home/davide/Downloads/papa-francesco.epub");
//		BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
//		try {
//			FileTypeDetector.detectFileType(stream);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		try {
			 ImageMetadataReader.readMetadata(new File("/home/davide/Downloads/70x100_su_carta_bianca.pdf"));
		} catch (ImageProcessingException | IOException e) {
			e.printStackTrace();
		}
	}

}
