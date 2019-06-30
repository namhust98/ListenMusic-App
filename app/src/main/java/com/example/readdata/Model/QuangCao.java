package com.example.readdata.Model;

import java.io.Serializable;

/**
 * Created by Lonely on 1/9/2017.
 */

public class QuangCao implements Serializable {
    private String urlimage;
    private String name;
    private String urlmedia;

    public QuangCao(String urlimage, String name, String urlmedia) {
        this.urlimage = urlimage;
        this.name = name;
        this.urlmedia = urlmedia;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public String getName() {
        return name;
    }

    public String getUrlmedia() {
        return urlmedia;
    }

}