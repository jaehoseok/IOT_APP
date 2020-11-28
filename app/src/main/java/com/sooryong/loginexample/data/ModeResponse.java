package com.sooryong.loginexample.data;

import com.google.gson.annotations.SerializedName;

public class ModeResponse {
    @SerializedName("realresult")
    private int realresult;

    public int getrealresult() {
        return realresult;
    }
}
