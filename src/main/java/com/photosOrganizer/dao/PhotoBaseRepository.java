package com.photosOrganizer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.photosOrganizer.model.PhotoBase;

public interface PhotoBaseRepository extends JpaRepository<PhotoBase, String> {
	
	@Query("select u from PhotoBase u where u.originalUrlLocation = :url and u.fileExtension = :extension")
	public List<PhotoBase> fetchPhotosByUrlAndExtension(@Param("url")String url, @Param("extension") String extension);	
	
	@Query("select distinct u.originalUrlLocation from PhotoBase u")
	public List<String> fetchAllOriginalUrlLocation();

	@Query("select distinct u.fileExtension from PhotoBase u")
	public List<String> fetchAllFileExtension();
	
	@Query("select distinct u.make from PhotoBase u where u.make is not null")
	public List<String> fetchAllMake();
	
	@Query("select distinct u.model from PhotoBase u where u.model is not null")
	public List<String> fetchAllModel();	
	
	@Transactional
	@Modifying
	@Query("update PhotoBase u set u.model = null where u.model not in :models")
	public void cleanModelMetadata(@Param("models") List<String> models);
	
	@Transactional
	@Query("select distinct year(coalesce(p.dateOriginal, p.date, sysdate() )) from PhotoBase p")
	public List<String> getPhotoYears();
	
		
}

