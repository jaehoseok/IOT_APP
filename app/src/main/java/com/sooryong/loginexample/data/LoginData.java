package com.sooryong.loginexample.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("id")
    String userID;

    @SerializedName("password")
    String userPwd;

    public LoginData(String userID, String userPwd) {
        this.userID = userID;
        this.userPwd = userPwd;
    }
}
