package com.linuxzasve.mobile.rest;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailedImage implements Parcelable{
	private String url;
	private int width;
	private int height;
	
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public DetailedImage(){}


	// PARCELABLE
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(url);
		dest.writeInt(width);
		dest.writeInt(height);
	};
	
	public static final Parcelable.Creator<DetailedImage> CREATOR = new Parcelable.Creator<DetailedImage>() 
	{
        @Override
        public DetailedImage createFromParcel(Parcel source) {
            return new DetailedImage(source);
        }
 
        @Override
        public DetailedImage[] newArray(int size) {
            return new DetailedImage[size];
        }
    };
    
    private DetailedImage(Parcel in)
    {
    	this.url 	= in.readString();
    	this.width 	= in.readInt();
    	this.height = in.readInt();
    }
	
}
