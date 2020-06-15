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

public class regiondex_fragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar loader;

    public regiondex_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.regiondex_fragment, container, false);
        loader = view.findViewById(R.id.loader);
        recyclerView = view.findViewById(R.id.regionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return view;
    }

    public void getData() {
        Call<regions> region = pokeApi.getPokeService().getregionsList();
        region.enqueue(new Callback<regions>() {
            @Override
            public void onResponse(Call<regions> call, Response<regions> response) {
                regions region = response.body();
                loader.setVisibility(View.GONE);
                recyclerView.setAdapter(new regiondex_adapter(getActivity(), region));

                // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<regions> call, Throwable t) {
                // Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
