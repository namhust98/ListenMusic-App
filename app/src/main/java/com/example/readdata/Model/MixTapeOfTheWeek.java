package com.example.readdata.Model;

import java.io.Serializable;

/**
 * Created by Lonely on 1/9/2017.
 */

public class MixTapeOfTheWeek implements Serializable {
    private String urlimage;
    private String name;
    private String title;
    private String docquyen;
    private String urlmedia;
    private String luotnghe;
    private String loibaihat;

    public MixTapeOfTheWeek(String urlimage, String name, String title, String docquyen, String urlmedia, String luotnghe, String loibaihat) {
        this.urlimage = urlimage;
        this.name = name;
        this.title = title;
        this.docquyen = docquyen;
        this.urlmedia = urlmedia;
        this.luotnghe = luotnghe;
        this.loibaihat = loibaihat;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public String getUrlmedia() {
        return urlmedia;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDocquyen() {
        return docquyen;
    }

    public String getLuotnghe(){
        return luotnghe;
    }

    public String getLoibaihat(){
        return loibaihat;
    }

}