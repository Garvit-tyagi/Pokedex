package com.example.thepokedex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepokedex.PokemonDetails.DetailAbilityAdapter;
import com.example.thepokedex.PokemonDetails.DetailMoveAdapter;
import com.example.thepokedex.PokemonDetails.DetailStatAdapter;
import com.example.thepokedex.PokemonDetails.DetailTypeAdapter;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeDetailsActivity extends AppCompatActivity {

    public static Integer id;
    Toast backToast;
    TextView pokeName, pokeHeight, pokeWeight, pokeExperience;
    RecyclerView recyclerType, recyclerStats, recyclerAbilities, recyclerMoves;
    ImageView pokeImage;
    PokemonDetailsData pokemonDetails;
    //LazyLoader lazyLoader;
    ProgressBar loader;
    RelativeLayout bgLayout;
    String originalID;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_details);
        Intent intent = getIntent();
        originalID = intent.getStringExtra("ID");

        pokeName = findViewById(R.id.pokeDetailName);
        pokeHeight = findViewById(R.id.pokeHeight);
        pokeWeight = findViewById(R.id.pokeWeight);
        pokeExperience = findViewById(R.id.pokeExperience);
        pokeImage = findViewById(R.id.pokeImage);
loader=findViewById(R.id.loader);
        recyclerType = findViewById(R.id.recyclerType);
        recyclerType.setLayoutManager(new LinearLayoutManager(PokeDetailsActivity.this, RecyclerView.HORIZONTAL, false));
        recyclerAbilities = findViewById(R.id.recyclerAbilities);
        recyclerAbilities.setLayoutManager(new LinearLayoutManager(PokeDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerStats = findViewById(R.id.recyclerStats);
        recyclerStats.setLayoutManager(new LinearLayoutManager(PokeDetailsActivity.this));
        recyclerMoves = findViewById(R.id.recyclerMoves);
        recyclerMoves.setLayoutManager(new LinearLayoutManager(PokeDetailsActivity.this));

        getData();
    }

    public void getData() {
        Call<PokemonDetailsData> pokeDetails = pokeApi.getPokeService().getPokemonDetails(originalID);
        pokeDetails.enqueue(new Callback<PokemonDetailsData>() {
            @Override
            public void onResponse(Call<PokemonDetailsData> call, Response<PokemonDetailsData> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Server Error !", Toast.LENGTH_SHORT).show();
                    return;
                }
                pokemonDetails = response.body();
             //   lazyLoader = findViewById(R.id.loader);
             //   lazyLoader.setVisibility(View.GONE);
                loader.setVisibility(View.GONE);
                bgLayout = findViewById(R.id.bgLayout);
                bgLayout.setVisibility(View.VISIBLE);

                setView();
            }

            @Override
            public void onFailure(Call<PokemonDetailsData> call, Throwable t) {
               // lazyLoader.setVisibility(View.GONE);
                AlertDialog.Builder dialog = new AlertDialog.Builder(PokeDetailsActivity.this);
                dialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                dialog.setTitle("Alert !!!");
                dialog.setIcon(android.R.drawable.ic_dialog_alert);
                dialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        });
                AlertDialog alertDialog = dialog.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void setView() {
        float height;
        float weight;
        if (pokemonDetails.getHeight() == null) {
            height = 0;

        } else {
            height = (float) pokemonDetails.getHeight() / 10;
        }
        if (pokemonDetails.getWeight() == null) {
            weight = 0;
        } else {
            weight = (float) pokemonDetails.getWeight() / 10;
        }
        pokeName.setText(pokemonDetails.getName());
        pokeHeight.setText("Height: " + height + "m");
        pokeWeight.setText("Weight: " + weight + "Kg");
        pokeExperience.setText("Base Experience: " + pokemonDetails.getBaseEx() + "xp");
        Picasso.with(PokeDetailsActivity.this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + originalID + ".png").into(pokeImage);
        recyclerType.setAdapter(new DetailTypeAdapter(PokeDetailsActivity.this, pokemonDetails.getTypes()));
        recyclerAbilities.setAdapter(new DetailAbilityAdapter(PokeDetailsActivity.this, pokemonDetails.getAbilities()));
        recyclerStats.setAdapter(new DetailStatAdapter(PokeDetailsActivity.this, pokemonDetails.getStats()));
        recyclerMoves.setAdapter(new DetailMoveAdapter(PokeDetailsActivity.this, pokemonDetails.getMoves()));
    }

//    @Override
//    public void onBackPressed() {
//
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//            backToast.cancel();
//            super.onBackPressed();
//            finish();
//        } else {
//            backToast = Toast.makeText(getBaseContext(), "Press back again to Exit", Toast.LENGTH_SHORT);
//            backToast.show();
//        }
//        backPressedTime = System.currentTimeMillis();
//    }
}

