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

public class itemdex_fragment extends Fragment {
    RecyclerView recyclerView;
    ProgressBar loader;

    public itemdex_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.itemdex_fragment, container, false);
        recyclerView = view.findViewById(R.id.itemList);
        loader = view.findViewById(R.id.loader);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return view;
    }

    public void getData() {
        Call<itemdex_data> item = pokeApi.getPokeService().getItemsList();
        item.enqueue(new Callback<itemdex_data>() {
            @Override
            public void onResponse(Call<itemdex_data> call, Response<itemdex_data> response) {
                itemdex_data pokemon = response.body();
                loader.setVisibility(View.GONE);

                recyclerView.setAdapter(new itemdex_adapter(getActivity(), pokemon.getResults()));

                // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<itemdex_data> call, Throwable t) {
                // Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
