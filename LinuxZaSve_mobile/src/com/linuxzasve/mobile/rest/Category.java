package com.linuxzasve.mobile.rest;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable{
	private int id;
	private String slug;
	private String title;
	private String description;
	private int parent;
	private int Category_count;
	
	
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


	public int getParent() {
		return parent;
	}


	public void setParent(int parent) {
		this.parent = parent;
	}


	public int getCategory_count() {
		return Category_count;
	}


	public void setCategory_count(int Category_count) {
		this.Category_count = Category_count;
	}


	public Category() {}

	// PARCELABLE 
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeInt(id);
		dest.writeString(slug);
		dest.writeString(title);
		dest.writeString(description);
		dest.writeInt(parent);
		dest.writeInt(Category_count);
	}
	
	public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() 
	{
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }
 
        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
    
    private Category(Parcel in)
    {
    	this.id 			= in.readInt();
    	this.slug 			= in.readString();
    	this.title 			= in.readString();
    	this.description 	= in.readString();
    	this.parent 		= in.readInt();
    	this.Category_count = in.readInt();
    }
}
