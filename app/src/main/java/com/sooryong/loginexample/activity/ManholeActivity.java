package com.sooryong.loginexample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.sooryong.loginexample.R;
import com.sooryong.loginexample.data.ModeData;
import com.sooryong.loginexample.data.ModeResponse;
import com.sooryong.loginexample.data.NoticeData;
import com.sooryong.loginexample.data.NoticeResponse;
import com.sooryong.loginexample.data.SearchData;
import com.sooryong.loginexample.data.SearchResponse;
import com.sooryong.loginexample.data.StateData;
import com.sooryong.loginexample.data.StateResponse;
import com.sooryong.loginexample.network.RetrofitClient;
import com.sooryong.loginexample.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManholeActivity extends AppCompatActivity {

    private EditText serial_num;
    private Button search_btn;
    private ServiceApi service;
    private ProgressBar searchprogressView;
    private EditText notice;
    private Button notice_btn;
    private ToggleButton type;
    private String target;
    private String state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhole);

        serial_num = (EditText) findViewById(R.id.serial_num);
        search_btn = (Button) findViewById(R.id.search_btn);
        searchprogressView = (ProgressBar) findViewById(R.id.search_progress);
        notice = (EditText) findViewById(R.id.notice);
        notice_btn = (Button) findViewById(R.id.notice_btn);
        type = (ToggleButton) findViewById(R.id.type);


        service = RetrofitClient.getClient().create(ServiceApi.class);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSearch();
            }
        });

        notice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice_modify(new NoticeData(target,notice.getText().toString()));
            }
        });

        type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    mode_change(new ModeData(target));
                    type.setChecked(true);
                }
                else{
                    mode_change(new ModeData(target));
                    type.setChecked(false);
                }

            }
        });

    }
    private void attemptSearch(){

        String serial = serial_num.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (serial.isEmpty()) {
            focusView = serial_num;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            target = serial;
            startSearch(new SearchData(serial));
            showProgress(true);
        }
    }
    private void startSearch(SearchData data) {
        service.manhole(data).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse result = response.body();
               // Toast.makeText(ManholeActivity.this, result.getrealresult(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                notice.setText(result.getrealresult());
                state_check(new StateData(target));
                if(state .equals('1'))
                    type.setChecked(false);
                else
                    type.setChecked(true);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(ManholeActivity.this, "검색 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("검색 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }


    private void notice_modify(NoticeData data) {
        service.modify(data).enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                NoticeResponse result = response.body();
                Toast.makeText(ManholeActivity.this, result.getrealresult(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                notice.setText(result.getrealresult());
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                Toast.makeText(ManholeActivity.this, "변경 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("변경 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }
    private void state_check(StateData data) {
        service.check(data).enqueue(new Callback<StateResponse>() {
            @Override
            public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {
                StateResponse result = response.body();
                Toast.makeText(ManholeActivity.this, result.getrealresult(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                state=result.getrealresult();
            }

            @Override
            public void onFailure(Call<StateResponse> call, Throwable t) {
                showProgress(false);
            }
        });
    }

    private void mode_change(ModeData data) {
        service.change(data).enqueue(new Callback<ModeResponse>() {
            @Override
            public void onResponse(Call<ModeResponse> call, Response<ModeResponse> response) {
                ModeResponse result = response.body();
                Toast.makeText(ManholeActivity.this, result.getrealresult(), Toast.LENGTH_SHORT).show();
                showProgress(false);
            }

            @Override
            public void onFailure(Call<ModeResponse> call, Throwable t) {
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        searchprogressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
