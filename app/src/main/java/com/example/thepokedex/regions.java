package com.example.thepokedex;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class regions {
    @SerializedName("results")
    private List<Region> results = null;
    public List<Region> getResults() {
        return results;
    }

    public class Region{
        @SerializedName("url")
        private String url;
        @SerializedName("name")
        private String name;

        public String getUrl() {
            return url;
        }
        public String getName() {
            return name;
        }

    }
}

