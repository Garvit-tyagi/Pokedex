package com.example.thepokedex.PokemonDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thepokedex.R;

import java.util.List;

public class DetailTypeAdapter extends RecyclerView.Adapter<DetailTypeAdapter.MyViewHolder> {
    Context context;
    List<Type> pokeTypes;

    public DetailTypeAdapter(Context context, List<Type> pokeTypes) {
        this.context = context;
        this.pokeTypes = pokeTypes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pokedetails_type_item, parent, false);
        return new DetailTypeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pokedetailsType.setText(pokeTypes.get(position).getType().getName().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return pokeTypes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pokedetailsType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pokedetailsType = itemView.findViewById(R.id.pokedetailsType);
        }
    }
}
