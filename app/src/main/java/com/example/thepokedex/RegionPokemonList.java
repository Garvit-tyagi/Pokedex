package com.example.thepokedex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegionPokemonList {
    @SerializedName("pokemon_entries")
    @Expose
    private List<PokemonEntry> pokemonEntries = null;

    public List<PokemonEntry> getPokemonEntries() {
        return pokemonEntries;
    }

    public void setPokemonEntries(List<PokemonEntry> pokemonEntries) {
        this.pokemonEntries = pokemonEntries;

    }
}
