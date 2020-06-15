package com.example.thepokedex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class pokemon_itemAdapter extends RecyclerView.Adapter<pokemon_itemAdapter.PokemonViewHolder> {

    private Context context;
    private List<pokemon_item.Pokemon> pokemonList;
  private TypePokemonList typePokemonList;
    private RegionPokemonList regionPokemonList;
    Activity a;
    int typeList=0;
    public pokemon_itemAdapter(Context ctx,Activity a,List<pokemon_item.Pokemon> pokemons){
        typeList=0;
        this.a =a;
        context = ctx;
        pokemonList = pokemons;
    }
    public pokemon_itemAdapter(Context ctx,Activity a,TypePokemonList typePokemonList){
        typeList=1;
        this.a =a;
        context = ctx;
        this.typePokemonList = typePokemonList;
    }
    public pokemon_itemAdapter(Context ctx,Activity a,RegionPokemonList regionPokemonList){
        typeList=2;
        this.a =a;
        context = ctx;
        this.regionPokemonList = regionPokemonList;
    }




    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pokemon_item,parent,false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PokemonViewHolder holder, final int position) {
        if(typeList==0) {
            final pokemon_item.Pokemon pokemon = pokemonList.get(position);
            final String url = pokemonList.get(position).getUrl();
            String result = null;
            if(url.length()>0){
                result = url.substring(34,url.length()-1);
            }

            holder.pokemonName.setText(pokemon.getName());
            Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + (result) + ".png").into(holder.pokemonImage);
            final String finalResult = result;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PokeDetailsActivity.class);
                    intent.putExtra("ID", finalResult);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }else if(typeList==1){
            final String url = typePokemonList.getPokemon().get(position).getPokemon().getUrl();
            String result = null;
            if(url.length()>0){
                result = url.substring(34,url.length()-1);
            }
            holder.pokemonName.setText(typePokemonList.getPokemon().get(position).getPokemon().getName());

            Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + result + ".png").into(holder.pokemonImage);
            final String finalResult = result;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, PokeDetailsActivity.class);
                    intent.putExtra("ID", finalResult);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                }
            });

        }else{
            final String urlId = regionPokemonList.getPokemonEntries().get(position).getPokemonSpecies().getUrl();
            String result = null;
            if(urlId.length()>0){
                result = urlId.substring(42,urlId.length()-1);
            }
            holder.pokemonName.setText(regionPokemonList.getPokemonEntries().get(position).getPokemonSpecies().getName());

            Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + result + ".png").into(holder.pokemonImage);
            final String finalResult = result;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, PokeDetailsActivity.class);
                    intent.putExtra("ID", finalResult);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(typeList==0)
            return pokemonList.size();
       else if(typeList==1)
        return typePokemonList.getPokemon().size();
        else
            return regionPokemonList.getPokemonEntries().size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder{
        ImageView pokemonImage;

        TextView pokemonName;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImage = itemView.findViewById(R.id.pokemonImage);

            pokemonName = itemView.findViewById(R.id.pokemon_name);
        }
    }

    public void filterList(List<pokemon_item.Pokemon> pokemonList){
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }
}