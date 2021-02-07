package com.photosOrganizer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="t_photo_base")
public class PhotoBase {
	
	@Id
	@GeneratedValue
	private int id;	
	private String originalName;
	private String originalUrlLocation;
	private String make;
	private String model;
	private Date date;
	private Date dateOriginal;
	
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getOriginalUrlLocation() {
		return originalUrlLocation;
	}
	public void setOriginalUrlLocation(String originalUrlLocation) {
		this.originalUrlLocation = originalUrlLocation;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDateOriginal() {
		return dateOriginal;
	}
	public void setDateOriginal(Date dateOriginal) {
		this.dateOriginal = dateOriginal;
	}
	
}
