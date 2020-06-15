package com.example.thepokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class locationdex_adapter extends RecyclerView.Adapter<locationdex_adapter.LocationViewHolder> {
    private Context context;
    private List<locationdex_data.Location> locationList;

    public locationdex_adapter(Context ctx, List<locationdex_data.Location> items) {
        context = ctx;
        locationList = items;
    }

    @NonNull
    @Override
    public locationdex_adapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.locationdex_item, parent, false);
        return new locationdex_adapter.LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull locationdex_adapter.LocationViewHolder holder, int position) {
        final locationdex_data.Location location = locationList.get(position);
        holder.itemName.setText(location.getName());
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
        }
    }
}
