package com.photosOrganizer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.photosOrganizer.model.UrlPhotoLocation;

public interface UrlPhotoLocationRepository extends JpaRepository<UrlPhotoLocation, String> {

}
