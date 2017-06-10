package org.androidtown.cheerup;

/**
 * Created by USER on 2017-06-10.
 */

public class SingerItem {
    String name;
    String date;
    int resId;

    public SingerItem(String name, String date){
        this.name = name;
        this.date = date;
    }

    public SingerItem(String name, String date, int resId){
        this.name = name;
        this.date = date;
        this.resId = resId;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public int getResId(){
        return resId;
    }

    public void setResId(int resId){
        this.resId = resId;
    }

}
