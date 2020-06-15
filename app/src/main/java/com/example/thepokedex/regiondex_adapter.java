package com.example.thepokedex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class regiondex_adapter extends RecyclerView.Adapter<regiondex_adapter.RegionViewHolder> {

    Context context;
    regions regions;



    public regiondex_adapter(Context context, regions regions) {

        this.context = context;
        this.regions = regions;
    }



    @NonNull
    @Override
    public RegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.regiondex_item,parent,false);
        return new regiondex_adapter.RegionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionViewHolder holder, final int position) {

            holder.regionName.setText(regions.getResults().get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RegionDetailActivity.class);
                    intent.putExtra("regionName", regions.getResults().get(position).getName().toUpperCase());
                    intent.putExtra("regionId", getRegionId(position));
                    context.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {

            return regions.getResults().size();
    }

    public class RegionViewHolder extends RecyclerView.ViewHolder {
        TextView regionName;
        public RegionViewHolder(@NonNull View itemView) {
            super(itemView);
            regionName = itemView.findViewById(R.id.regionName);
        }
    }

    public int getRegionId(int position) {
        switch (position) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 8;
            case 5:
                return 12;
            case 6:
                return 16;
            default:
                return 1;

        }


    }
}