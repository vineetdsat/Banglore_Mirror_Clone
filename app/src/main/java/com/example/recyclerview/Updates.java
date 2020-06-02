package com.example.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class Updates implements Parcelable {
    private String Head;
    private String Link;
    private String Img_URL;
    private String description;


    public Updates() {
    }

    public Updates(String head, String link, String Img_url, String Description) {
        Head = head;
        Link = link;
        Img_URL = Img_url;
        description = Description;
    }

    protected Updates(Parcel in) {
        Head = in.readString();
        description = in.readString();
    }

    public static final Creator<Updates> CREATOR = new Creator<Updates>() {
        @Override
        public Updates createFromParcel(Parcel in) {
            return new Updates(in);
        }

        @Override
        public Updates[] newArray(int size) {
            return new Updates[size];
        }
    };

    public String getImg_URL() {
        return Img_URL;
    }

    public void setImg_URL(String img_URL) {
        Img_URL = img_URL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Head);
        dest.writeString(description);
    }
}
