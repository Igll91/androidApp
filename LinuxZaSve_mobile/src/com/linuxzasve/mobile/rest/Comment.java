package com.linuxzasve.mobile.rest;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable{
	private int id;
	private String name;
	private String url;
	private String date;
	private String content;
	private String parent;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getParent() {
		return parent;
	}


	public void setParent(String parent) {
		this.parent = parent;
	}


	public Comment(){}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeStringArray(new String[]{ name, url, date, content, parent });
	};
	
	public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() 
	{
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }
 
        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
    
    private Comment(Parcel in)
    {
    	this.id = in.readInt();
    	
    	String[] s = new String[5];
    	in.readStringArray(s);
    	
    	this.name 	= s[0];
    	this.url 	= s[1];
    	this.date 	= s[2];
    	this.content= s[3];
    	this.parent = s[4];
    }
}
