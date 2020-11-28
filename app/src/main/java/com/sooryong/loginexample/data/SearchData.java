package com.sooryong.loginexample.data;

import com.google.gson.annotations.SerializedName;

public class SearchData {
    @SerializedName("manholeID")
    private String manholeID;

    public SearchData(String manholeID){ this.manholeID = manholeID; }
}
