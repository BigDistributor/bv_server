package org.mzouink.entities;

import com.google.gson.Gson;

public class HistorySmartLink extends SmartLink {
    private final String tsUse;

    private HistorySmartLink(String id, String json) {
        super(id, json);
        this.tsUse = String.valueOf(System.currentTimeMillis());
    }

    public HistorySmartLink(SmartLink link) {
        this(formatID(link.getTs(), link.getID()), link.getJson());
    }

    private static String formatID(String st1, String st2) {
        return st1 + "_" + st2;
    }

    public static HistorySmartLink fromJson(String json){
        return new Gson().fromJson(json,HistorySmartLink.class);
    }
}
