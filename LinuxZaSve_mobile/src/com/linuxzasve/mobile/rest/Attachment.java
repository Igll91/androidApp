package com.linuxzasve.mobile.rest;

import android.os.Parcel;
import android.os.Parcelable;

public class Attachment implements Parcelable{
	private int id;
	private String url;
	private String slug;
	private String title;
	private String description;
	private String caption;
	private int parent;
	private String mime_type;
	private Image images;
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getSlug() {
		return slug;
	}



	public void setSlug(String slug) {
		this.slug = slug;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getCaption() {
		return caption;
	}



	public void setCaption(String caption) {
		this.caption = caption;
	}



	public int getParent() {
		return parent;
	}



	public void setParent(int parent) {
		this.parent = parent;
	}



	public String getMime_type() {
		return mime_type;
	}



	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}



	public Image getImages() {
		return images;
	}



	public void setImages(Image images) {
		this.images = images;
	}



	public Attachment(){}

	// PARCELABLE

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeStringArray(new String[]{ url, slug, title, description, caption });
		dest.writeInt(parent);
		dest.writeString(mime_type);
		dest.writeParcelable(images, PARCELABLE_WRITE_RETURN_VALUE);
	};
	
	public static final Parcelable.Creator<Attachment> CREATOR = new Parcelable.Creator<Attachment>() 
	{
        @Override
        public Attachment createFromParcel(Parcel source) {
            return new Attachment(source);
        }
 
        @Override
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };
    
    private Attachment(Parcel in)
    {
    	this.id = in.readInt();
    	
    	String[] s = new String[5];
    	in.readStringArray(s);
    	
    	this.url 		 = s[0];
    	this.slug 		 = s[1];
    	this.title 		 = s[2];
    	this.description = s[3];
    	this.caption 	 = s[4];
    }
}
