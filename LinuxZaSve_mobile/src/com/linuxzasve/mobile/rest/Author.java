package com.linuxzasve.mobile.rest;

import android.os.Parcel;
import android.os.Parcelable;

public class Author implements Parcelable{
	private int id;
	private String slug;
	private String name;
	private String first_name;
	private String last_name;
	private String nickname;
	private String url;
	private String description;
	
	
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Author(){}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeStringArray(new String[] { slug, name, first_name, last_name, nickname, url, description });
	};
	
	public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() 
	{
        @Override
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }
 
        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
    
    private Author(Parcel in)
    {
    	this.id = in.readInt();
    	
    	String[] s = new String[7];
    	in.readStringArray(s);
    	
    	this.slug		= s[0];
    	this.name		= s[1];
    	this.first_name	= s[2];
    	this.last_name	= s[3];
    	this.nickname	= s[4];
    	this.url		= s[5];
    	this.description= s[6];
    }
}
