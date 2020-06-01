package com.example.recyclerview;

public class Updates{
    private String Head;
    private String Link;
    private String Img_URL;


    public Updates() {
    }

    public Updates(String head, String link, String Img_url) {
        Head = head;
        Link = link;
        Img_URL = Img_url;
    }

    public String getImg_URL() {
        return Img_URL;
    }

    public void setImg_URL(String img_URL) {
        Img_URL = img_URL;
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
}
