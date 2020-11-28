package com.sooryong.loginexample.data;

import com.google.gson.annotations.SerializedName;

public class ModeData {
    @SerializedName("serial")
    private String target_Serial;

    public ModeData(String target_Serial){
        this.target_Serial = target_Serial;
    }
}
