package com.example.thepokedex;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class pokeApi {
    public static final String baseurl = "https://pokeapi.co/api/v2/";
    public static final String pokemonUrl= "https://pokeapi.co/api/v2/pokemon/";
    public static final String typeUrl = "https://pokeapi.co/api/v2/type/";
    public static final String regionUrl = "https://pokeapi.co/api/v2/";
    public static final String regionPokemonUrl = "https://pokeapi.co/api/v2/pokedex/";
    public static final String itemUrl = "https://pokeapi.co/api/v2/";


    public static PokemonApiInterface pokeService = null;
    public static PokemonApiInterface getPokeService(){
        if(pokeService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            pokeService = retrofit.create(PokemonApiInterface.class);
        }
        return pokeService;
    }
public interface PokemonApiInterface{
    @GET("pokemon/?limit=964&offset=0")
    Call<pokemon_item> getpokemon_item();
    @GET("pokemon/{id}/")
  Call<PokemonDetailsData> getPokemonDetails(@Path("id") String id);

    @GET("item/?limit=1000")
    Call<itemdex_data> getItemsList();

   @GET("location/?limit=781")
    Call<locationdex_data> getLocationList();
    @GET("region/")
    Call<regions> getregionsList();
    @GET("pokedex/{id}/")
    Call<RegionPokemonList> getRegionDetails(@Path("id") int id );
    @GET("type/{id}/")
    Call<TypePokemonList> getTypeDetails(@Path("id") int id );
    @GET("type/")
    Call<PokeType> getPokemonTypes();


}
}
