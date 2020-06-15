package com.example.thepokedex;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class locationdex_data {
    @SerializedName("results")
    private List<locationdex_data.Location> results;

    public List<locationdex_data.Location> getResults() {   return results; }
    public void setResults(List<locationdex_data.Location> results) {   this.results = results; }

    class Location {

        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
