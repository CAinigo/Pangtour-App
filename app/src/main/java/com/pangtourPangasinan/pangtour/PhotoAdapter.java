package com.pangtourPangasinan.pangtour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pangtourPangasinan.pangtour.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    Context context2;
    List<Photos> mList;

    public PhotoAdapter(Context context, List<Photos> mList){
        this.context2= context;
        this.mList= mList;

    }

    public void add(Photos photos) {
        mList.add(photos);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context2).inflate(R.layout.each_image,parent,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {

        Photos photos=mList.get(position);
        Glide.with(context2).load(photos.getImage())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView imageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.imageView);
        }
    }
}
