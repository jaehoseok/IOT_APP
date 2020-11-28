package com.sooryong.loginexample.data;

import com.google.gson.annotations.SerializedName;

public class StateResponse {
    @SerializedName("realresult")
    private String realresult;

    public String getrealresult() {
        return realresult;
    }
}
