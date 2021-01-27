package com.photosOrganizer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.photosOrganizer.model.PhotoBase;

public interface PhotoBaseRepository extends JpaRepository<PhotoBase, String> {

}
