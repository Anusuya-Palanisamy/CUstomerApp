package com.aspire.CustomerApp.Data;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
	public class Image {
	    @Id
	    private String id;
	    
	    private String title;
	        
	    private Binary image;

		public Image( ) {
			super();
			// TODO Auto-generated constructor stub
		}

		public Image(String id, String title, Binary image) {
			super();
			this.id = id;
			this.title = title;
			this.image = image;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Binary getImage() {
			return image;
		}

		public void setImage(Binary image) {
			this.image = image;
		}

		@Override
		public String toString() {
			return "Image [id=" + id + ", title=" + title + ", image=" + image + "]";
		}
	
}
