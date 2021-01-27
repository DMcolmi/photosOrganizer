package com.photosOrganizer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.photosOrganizer.model.UrlPhotoLocation;

public interface UrlPhotoLocationRepository extends JpaRepository<UrlPhotoLocation, Integer> {

	@Query("select u from UrlPhotoLocation u where u.url = :insertedUrl")
	public List<UrlPhotoLocation> findByUrl(@Param("insertedUrl") String insertedUrl);
	
}
