package com.aspire.CustomerApp.Service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aspire.CustomerApp.Data.Image;
import com.aspire.CustomerApp.Repo.ImageRepo;

@Service
	public class ImageService {

	    @Autowired
	    private ImageRepo photoRepo;

	    public String addPhoto(String title, MultipartFile file) throws IOException { 
	        Image image = new Image(); 
	        image.setImage(
	          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
	        image = photoRepo.insert(image); return image.getId(); 
	    }

	    public Image getPhoto(String id) { 
	        return photoRepo.findById(id).get(); 
	    }
	
}
