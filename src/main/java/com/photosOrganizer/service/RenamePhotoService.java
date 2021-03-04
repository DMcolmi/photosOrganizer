package com.photosOrganizer.service;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.photosOrganizer.model.PhotoBase;

@Service
public class RenamePhotoService{
	
	private final String DATE_FORMAT = "yyyyMMdd_HHmmss.SSS";
	private final DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	private final String SEPARATOR = "_";
	
	Utility<String> stringNvl = new Utility<String>();
	Utility<Date> dateNvl = new Utility<Date>();
	
	public String buildNewPhotoName(PhotoBase photo) {		
		
		Date dateComponent = dateNvl.nvl(photo.getDate(), photo.getDateOriginal());
		
		if (dateComponent == null)
			return photo.getOriginalName();
		
		String nameComponent = stringNvl.nvl(stringNvl.nvl(photo.getModel(), photo.getMake()), "IMG");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(formatData(dateComponent)).append(SEPARATOR).append(trimStringToMax12Char(removeWhiteSpaces(nameComponent))).append(photo.getFileExtension());
		
		return sb.toString();
	}
	
	
	private String removeWhiteSpaces(String originalName) {
		return originalName.replaceAll("\\s","");
	}
	
	private String trimStringToMax12Char(String originalName) {
		return originalName.substring(0, Math.min(originalName.length(), 12));
	}
	
	private String formatData(Date date) {		
		return df.format(date);		
	}	
	
	private static class Utility<E>  {
		
		public E nvl(E first, E second){
			return first != null ? first : second;
		}

	}
	
}
