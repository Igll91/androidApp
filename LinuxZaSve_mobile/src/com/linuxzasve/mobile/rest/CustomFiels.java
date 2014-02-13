package com.linuxzasve.mobile.rest;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomFiels implements Parcelable{
	private List<String> views;
	
		
	public List<String> getViews() {
		return views;
	}


	public void setViews(List<String> views) {
		this.views = views;
	}


	public CustomFiels(){}

	// PARCELABLE

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringList(views);
	};
	
	public static final Parcelable.Creator<CustomFiels> CREATOR = new Parcelable.Creator<CustomFiels>() 
	{
        @Override
        public CustomFiels createFromParcel(Parcel source) {
            return new CustomFiels(source);
        }
 
        @Override
        public CustomFiels[] newArray(int size) {
            return new CustomFiels[size];
        }
    };
    
    private CustomFiels(Parcel in)
    {
    	in.readStringList(views);
    }
}
