package com.sooryong.loginexample.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("id")
    private String userID;

    @SerializedName("name")
    private String userName;

    @SerializedName("password")
    private String userPwd;

    public JoinData(String userID, String userName, String userPwd) {
        this.userID = userID;
        this.userName = userName;
        this.userPwd = userPwd;
    }
}
