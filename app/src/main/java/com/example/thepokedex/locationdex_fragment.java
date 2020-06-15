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

public class locationdex_fragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar loader;

    public locationdex_fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.locationdex_fragment, container, false);
        recyclerView = view.findViewById(R.id.locationList);
        loader = view.findViewById(R.id.loader);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return view;
    }

    public void getData() {
        Call<locationdex_data> location = pokeApi.getPokeService().getLocationList();
        location.enqueue(new Callback<locationdex_data>() {
            @Override
            public void onResponse(Call<locationdex_data> call, Response<locationdex_data> response) {
                locationdex_data locations = response.body();
                loader.setVisibility(View.GONE);
                recyclerView.setAdapter(new locationdex_adapter(getActivity(), locations.getResults()));

                // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<locationdex_data> call, Throwable t) {
                // Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
