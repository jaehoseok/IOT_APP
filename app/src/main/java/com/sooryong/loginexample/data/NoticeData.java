package com.sooryong.loginexample.data;

import com.google.gson.annotations.SerializedName;

public class NoticeData {

    @SerializedName("serial")
    private String target_serial;

    @SerializedName("notice")
    private String notice;

    public NoticeData(String target_serial, String notice){
        this.target_serial = target_serial;
        this.notice = notice;
    }
}
