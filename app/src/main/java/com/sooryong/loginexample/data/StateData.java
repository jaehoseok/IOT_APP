package com.sooryong.loginexample.data;

import com.google.gson.annotations.SerializedName;

public class StateData {
    @SerializedName("serial")
    private String target_Serial;

    public StateData(String target_Serial){
        this.target_Serial = target_Serial;
    }
}
