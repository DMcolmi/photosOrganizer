package com.photosOrganizer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.photosOrganizer.model.PhotoBase;

public interface PhotoBaseRepository extends JpaRepository<PhotoBase, String> {
	
	@Query("select u from PhotoBase u where u.originalUrlLocation = :url and u.fileExtension = :extension")
	public List<PhotoBase> fetchPhotosByUrlAndExtension(@Param("url")String url, @Param("extension") String extension);	
	
	@Query("select distinct u.originalUrlLocation from PhotoBase u")
	public List<String> fetchAllOriginalUrlLocation();

	@Query("select distinct u.fileExtension from PhotoBase u")
	public List<String> fetchAllFileExtension();
		
}

