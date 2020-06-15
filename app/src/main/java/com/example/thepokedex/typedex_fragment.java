package com.example.thepokedex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class typedex_fragment extends Fragment {


    RecyclerView recyclerView;
    ProgressBar loader;

    public typedex_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.typedex_fragment, container, false);
        recyclerView = view.findViewById(R.id.typeList);
        loader = view.findViewById(R.id.loader);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return view;
    }

    public void getData() {
        Call<PokeType> type = pokeApi.getPokeService().getPokemonTypes();
        type.enqueue(new Callback<PokeType>() {
            @Override
            public void onResponse(Call<PokeType> call, Response<PokeType> response) {
                PokeType pokemon = response.body();
                loader.setVisibility(View.GONE);
                pokemon.getResults().remove(pokemon.getResults().size() - 1);
                pokemon.getResults().remove(pokemon.getResults().size() - 1);
                recyclerView.setAdapter(new TypeListAdapter(getActivity(), pokemon));

                // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PokeType> call, Throwable t) {
                // Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
