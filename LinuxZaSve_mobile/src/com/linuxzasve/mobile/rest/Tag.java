package com.linuxzasve.mobile.rest;

import android.os.Parcel;
import android.os.Parcelable;

public class Tag implements Parcelable{
	private int id;
	private String slug;
	private String title;
	private String description;
	private String Tag_count;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTag_count() {
		return Tag_count;
	}

	public void setTag_count(String Tag_count) {
		this.Tag_count = Tag_count;
	}

	public Tag() {}

	// PARCELABLE
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeStringArray(new String[]{ slug, title, description, Tag_count });
	}
	
	public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() 
	{
        @Override
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }
 
        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };
		    
    private Tag(Parcel in)
    {
    	this.id = in.readInt();
    	
    	String[] s = new String[4];
    	in.readStringArray(s);
    	
    	this.slug 		 = s[0];
    	this.title 		 = s[1];
    	this.description = s[2];
    	this.Tag_count 	 = s[3];
    }
}

