package com.pangtourPangasinan.pangtour;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pangtourPangasinan.pangtour.R;

import java.util.ArrayList;
import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder>{

    Context context;
    ArrayList<Places> placesArrayList;
    List<Photos> mList;

    public PlacesAdapter(Context context, ArrayList<Places> placesArrayList) {
        this.context = context;
        this.placesArrayList= placesArrayList;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void add(Places places) {
        placesArrayList.add(places);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public PlacesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.places_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Places places=placesArrayList.get(position);
        getItemId(position);
        holder.name.setText(places.getpName());

        Glide.with(context).load(places.getpImage())
                .into(holder.image);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(context, PlaceView.class);
                intent.putExtra("pId", getItemCount());
                intent.putExtra("categories", placesArrayList.get(position).getcategories());
                intent.putExtra("pName", placesArrayList.get(position).getpName());
                intent.putExtra("pImage", placesArrayList.get(position).getpImage());
                intent.putExtra("pDescription", placesArrayList.get(position).getpDescription());
                intent.putExtra("pLocation", placesArrayList.get(position).getpLocation());
                intent.putExtra("otherImage_1", placesArrayList.get(position).getotherImage_1());
                intent.putExtra("otherImage_2", placesArrayList.get(position).getotherImage_2());

                context.startActivity(intent);


            }
        });




    }

    @Override
    public int getItemCount() {
        return placesArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, description, location;
        private ImageView image, aPhoto;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            description= itemView.findViewById(R.id.description);
            location= itemView.findViewById(R.id.location);
            aPhoto= itemView.findViewById(R.id.aPhoto);

            cardView= itemView.findViewById(R.id.cardView);

        }


    }
}
