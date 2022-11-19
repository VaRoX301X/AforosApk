package com.example.aforosapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aforosapk.Interface.JsonPlaceHolderApi;
import com.example.aforosapk.Models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;

    private TextView jsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button changeTextButton = findViewById(R.id.changeTextBtn);

        changeTextButton.setOnClickListener(changeTextListener);

        jsonTxtView = findViewById(R.id.jsonText);

    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    jsonTxtView.setText(response.code());
                    return;
                }

                List<Post> postList = response.body();
                for (Post p : postList) {
                    String content = "";
                    content += "UserId: " + p.getUserId() + "\n";
                    content += "Title: " + p.getTitle() + "\n";
                    content += "id: " + p.getId() + "\n";
                    jsonTxtView.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                jsonTxtView.setText(t.getMessage());
            }
        });

    }

    private View.OnClickListener changeTextListener = view -> {

        getPosts();
        counter += 1;



        TextView text = findViewById(R.id.hello);
        text.setText("Pulsado " + counter + " veces.");

        TextView locText = findViewById(R.id.locText);
    };
}