package com.apps.b4kwallpapers.UploadPages;

public class NatureUpload {
    public String id;
    private String mName;
    private String mImageUrl;

    public NatureUpload()
    {
        //empty constructor needed
    }
    public  NatureUpload( String name, String imageUrl)
    {
        if(name.trim().equals("")){
            name= "No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
    }
    public String getName(){
        return mName;
    }
    public void setName( String name){

        mName = name;
    }
    public String getImageUrl(){
        return  mImageUrl;
    }
    public void setImageUrl(String imageUrl){
        mImageUrl = imageUrl;
    }
}
