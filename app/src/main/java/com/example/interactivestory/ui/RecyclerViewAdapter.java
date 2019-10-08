package com.example.interactivestory.ui;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.interactivestory.R;
import com.example.interactivestory.model.DatabaseHelper;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.interactivestory.R.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> mImageNames = new ArrayList<String>();
    //private ArrayList<String> mImages = new ArrayList<String>();
    private ArrayList<String> mDesc = new ArrayList<String>();
    private ArrayList<Integer> mImagesint = new ArrayList<Integer>();

    private Context mContext;
    DatabaseHelper db;


    public RecyclerViewAdapter(Context mContext, ArrayList<String> mImageNames, ArrayList<Integer> mImagesint, ArrayList<String> mDesc) {
        this.mImageNames = mImageNames;
       // this.mImages = mImages;
        this.mContext = mContext;
        this.mDesc = mDesc;
        this.mImagesint = mImagesint;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

        Log.d(TAG, "onBindViewHolder: called.");
        //db = new DatabaseHelper(mContext, "story.db",1);


        //         app:srcCompat="@drawable/logo_fmp"


        //Glide.with(mContext)
          //      .asBitmap()
            //    .load(mImagesint.get(i))
              //  .into(holder.image);

        Glide.with(mContext)
                .load(mImagesint.get(i))
                .into(holder.image);
        holder.imageName.setText(mImageNames.get(i));
        holder.textDescription.setText(mDesc.get(i));


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(i));
                Toast.makeText(mContext, mImageNames.get(i), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, StoryActivity.class);
                intent.putExtra("storylabel",mImageNames.get(i));
                mContext.startActivity(intent);


                //Intent intent = new Intent(mContext, MapsActivity.class);
                //intent.putExtra("longitude", db.getLongitude(mImageNames.get(i)));
                //intent.putExtra("latitude", db.getLatitude(mImageNames.get(i)));
                //mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    private static final String TAG = "RecyclerViewAdapter";

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;
        TextView textDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(id.image);
            imageName = itemView.findViewById(id.image_name);
            parentLayout = itemView.findViewById(id.parentLayout);
            textDescription = itemView.findViewById(id.textDescription);
        }
    }
}