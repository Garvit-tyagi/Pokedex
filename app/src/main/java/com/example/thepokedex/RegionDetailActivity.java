package com.example.thepokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    int id;
    ProgressBar loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_region_detail);
        recyclerView = findViewById(R.id.recyclerView);
        loader = findViewById(R.id.loader);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("regionName"));
        setSupportActionBar(toolbar);
        id = getIntent().getIntExtra("regionId", 1);
        getData();
    }

    public void getData() {
        Call<RegionPokemonList> data = pokeApi.getPokeService().getRegionDetails(id);
        data.enqueue(new Callback<RegionPokemonList>() {
            @Override
            public void onResponse(Call<RegionPokemonList> call, Response<RegionPokemonList> response) {
                RegionPokemonList pokemon = response.body();
                loader.setVisibility(View.GONE);
                recyclerView.setAdapter(new pokemon_itemAdapter(RegionDetailActivity.this, RegionDetailActivity.this, pokemon));

                //Toast.makeText(RegionDetailActivity.this, "Success region "+ id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegionPokemonList> call, Throwable t) {
                //Toast.makeText(RegionDetailActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
