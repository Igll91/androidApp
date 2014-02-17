package com.linuxzasve.mobile.rest;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable{
	private DetailedImage full;
	private DetailedImage thumbnail;
	private DetailedImage medium;
	private DetailedImage large;
	private DetailedImage wptouch_new_thumbnail;
	
	
	public DetailedImage getFull() {
		return full;
	}


	public void setFull(DetailedImage full) {
		this.full = full;
	}


	public DetailedImage getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(DetailedImage thumbnail) {
		this.thumbnail = thumbnail;
	}


	public DetailedImage getMedium() {
		return medium;
	}


	public void setMedium(DetailedImage medium) {
		this.medium = medium;
	}


	public DetailedImage getLarge() {
		return large;
	}


	public void setLarge(DetailedImage large) {
		this.large = large;
	}


	public DetailedImage getWptouch_new_thumbnail() {
		return wptouch_new_thumbnail;
	}


	public void setWptouch_new_thumbnail(DetailedImage wptouch_new_thumbnail) {
		this.wptouch_new_thumbnail = wptouch_new_thumbnail;
	}


	public Image(){}

	// PARCELABLE

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(full, PARCELABLE_WRITE_RETURN_VALUE);
		dest.writeParcelable(thumbnail, PARCELABLE_WRITE_RETURN_VALUE);	
		dest.writeParcelable(medium, PARCELABLE_WRITE_RETURN_VALUE);
		dest.writeParcelable(large, PARCELABLE_WRITE_RETURN_VALUE);
		dest.writeParcelable(wptouch_new_thumbnail, PARCELABLE_WRITE_RETURN_VALUE);
	};
	
	public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() 
	{
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }
 
        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    
    private Image(Parcel in)
    {
    	this.full 					= in.readParcelable(DetailedImage.class.getClassLoader());
    	this.thumbnail 				= in.readParcelable(DetailedImage.class.getClassLoader());
    	this.medium 				= in.readParcelable(DetailedImage.class.getClassLoader());
    	this.large 					= in.readParcelable(DetailedImage.class.getClassLoader());
    	this.wptouch_new_thumbnail 	= in.readParcelable(DetailedImage.class.getClassLoader());
    }
}
