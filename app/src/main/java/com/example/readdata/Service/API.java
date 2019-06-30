package com.example.readdata.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class API {
    public static String Request(){
        try {
            HttpResponse<String> response = Unirest.get("http://vip.service.keeng.vn/KeengBackendBiz/ws/edm/getHomeEdmV1")
                    .header("User-Agent", "PostmanRuntime/7.15.0")
                    .header("Accept", "*/*")
                    .header("Cache-Control", "no-cache")
                    .header("Postman-Token", "dff58759-e013-45a0-b572-4f6c8f10b016,011e5e37-2d56-4e42-899a-c0182f9410e7")
                    .header("Host", "vip.service.keeng.vn")
                    .header("accept-encoding", "gzip, deflate")
                    .header("Connection", "keep-alive")
                    .header("cache-control", "no-cache")
                    .asString();
            return response.getBody();
        } catch (UnirestException e) {
            return null;
        }
    }
}
