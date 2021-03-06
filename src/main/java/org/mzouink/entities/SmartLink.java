package org.mzouink.entities;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;

public class SmartLink {

    private final String id;
    private final String json;
    private final String ts;

    public SmartLink(String id, String json) {
        this.id = id;
        this.json = json;
        this.ts = String.valueOf(System.currentTimeMillis());
    }

    public static String generateID() {
        return RandomStringUtils.random(4, true, true);
    }

    public String getJson() {
        return json;
    }

    public static void main(String[] args) {
        System.out.println(generateID());
    }

    public String getID() {
        return id;
    }

    public String getTs() {
        return ts;
    }

    @Override
    public String toString() {
        return  new Gson().toJson(this);
    }

    public static SmartLink fromJson(String json){
        return new Gson().fromJson(json,SmartLink.class);
    }
}
