package com.example.thepokedex;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class pokemons_fragment extends Fragment {
    RecyclerView recyclerView;
    EditText searchText;
   pokemon_item PokemonItem;
   ProgressBar loader;

    pokemon_itemAdapter pokemon_itemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.pokemons_fragment,container,false);
            recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchText=view.findViewById(R.id.search_text);
        loader=view.findViewById(R.id.loader);
        getData();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged: " + s);
                searchAction(s.toString());
            }
        });
        return view;
    }
    public void getData(){
        final Call<pokemon_item> pokemon_item= pokeApi.getPokeService().getpokemon_item();
        pokemon_item.enqueue(new Callback<com.example.thepokedex.pokemon_item>() {
            @Override
            public void onResponse(Call<com.example.thepokedex.pokemon_item> call, Response<com.example.thepokedex.pokemon_item> response) {
                PokemonItem=response.body();
                loader.setVisibility(View.GONE);
                searchText.setVisibility(View.VISIBLE);
                pokemon_itemAdapter=new pokemon_itemAdapter(getActivity(),getActivity(),PokemonItem.getResults());
                recyclerView.setAdapter(pokemon_itemAdapter);
            }

            @Override
            public void onFailure(Call<com.example.thepokedex.pokemon_item> call, Throwable t) {

            }
        });
    }
    public void searchAction(String text) {
        ArrayList<pokemon_item.Pokemon> filteredList = new ArrayList<>();
        for (pokemon_item.Pokemon pok : PokemonItem.getResults()) {
            if (pok.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(pok);
            }
        }
        pokemon_itemAdapter.filterList(filteredList);
    }
}
