package com.sooryong.loginexample.network;

import com.sooryong.loginexample.data.JoinData;
import com.sooryong.loginexample.data.JoinResponse;
import com.sooryong.loginexample.data.LoginData;
import com.sooryong.loginexample.data.LoginResponse;
import com.sooryong.loginexample.data.ModeData;
import com.sooryong.loginexample.data.ModeResponse;
import com.sooryong.loginexample.data.NoticeData;
import com.sooryong.loginexample.data.NoticeResponse;
import com.sooryong.loginexample.data.SearchData;
import com.sooryong.loginexample.data.SearchResponse;
import com.sooryong.loginexample.data.StateData;
import com.sooryong.loginexample.data.StateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/sign/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/sign/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/notice/manholeID")
    Call<SearchResponse> manhole(@Body SearchData data);

    @POST("/notice/contentUpdate")
    Call<NoticeResponse> modify(@Body NoticeData data);

    @POST("/notice/postState")
    Call<StateResponse> check(@Body StateData data);

    @POST("/notice/state")
    Call<ModeResponse> change(@Body ModeData data);
}