package com.example.turing.retrofitinjavaexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.turing.retrofitinjavaexample.api.model.GitHubRepo;
import com.example.turing.retrofitinjavaexample.api.service.GitHubClient;
import com.example.turing.retrofitinjavaexample.ui.GitHubAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private ListView gitHubRepos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    gitHubRepos = findViewById(R.id.lv_gitrepos);

    Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    GitHubClient client = retrofit.create(GitHubClient.class);
    Call<List<GitHubRepo>> call = client.reposForUser("fs-opensource");

    call.enqueue(new Callback<List<GitHubRepo>>() {
      @Override
      public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
        List<GitHubRepo> repos = response.body();

        gitHubRepos.setAdapter(new GitHubAdapter(MainActivity.this, repos));

      }

      @Override
      public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Connection failed", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
