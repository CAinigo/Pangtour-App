package com.pangtourPangasinan.pangtour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pangtourPangasinan.pangtour.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PlaceView extends AppCompatActivity {

    ImageView image, otherImage1, otherImage2;
    TextView name, description, location;
    CollapsingToolbarLayout categorie;
    CardView cardView;
    Dialog dialog, dialog2;
    List<Photos> mList;
    FloatingActionButton actionButton;
    RecyclerView recyclerView;
    PhotoAdapter photoAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

/*
    ArrayList<Integer> mImageIds = new ArrayList<>(Arrays.asList(
            R.drawable.balunggao_hilltop, R.drawable.h_lenox_hotel, R.drawable.f_antong_falls,
            R.drawable.f_tara_falls, R.drawable.f_busay_falls, R.drawable.f_mapita_falls,
            R.drawable.f_tara_falls, R.drawable.f_busay_falls
    ));

 */

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_view);

        /*progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show(); */

        image = findViewById(R.id.imageView);
        otherImage1 = findViewById(R.id.otherImage_1);
        otherImage2 = findViewById(R.id.otherImage_2);
        name = findViewById(R.id.nameView);
        categorie= findViewById(R.id.collapsingToolbarCategories);
        description = findViewById(R.id.descriptionView);
        location = findViewById(R.id.locationView);
        dialog= new Dialog(this);
        dialog2 = new Dialog(this);

        actionButton= findViewById(R.id.appBarMap);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlaceView.this, MapActivity.class));
            }
        });


        /*
        //recyclerView = findViewById(R.id.main_container);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        db= FirebaseFirestore.getInstance();
        mList =new ArrayList<Photos>();
        photoAdapter= new PhotoAdapter(PlaceView.this, mList);

        recyclerView.setAdapter(photoAdapter);
        //ImageEventListener();

*/

        /*
        GridView gridView = findViewById(R.id.myGrid);
        gridView.setAdapter(new ImageAdaptor(mImageIds, this));

         */

        Intent intent = getIntent();
        String categories = intent.getExtras().getString("categories");
        String pName = intent.getExtras().getString("pName");
        String pImage = intent.getStringExtra("pImage");
        String pDescription = intent.getExtras().getString("pDescription");
        String pLocation = intent.getExtras().getString("pLocation");
        String otherImage_1 = intent.getStringExtra("otherImage_1");
        String otherImage_2 = intent.getStringExtra("otherImage_2");


        if(otherImage_1 == null) {
            otherImage1.setImageResource(R.drawable.ic_baseline_image_24);
        }
        else{
            Glide.with(this).load(otherImage_1).into(otherImage1);
        }

        if(otherImage_2 == null) {
            otherImage2.setImageResource(R.drawable.ic_baseline_image_24);
        }
        else{
            Glide.with(this).load(otherImage_2).into(otherImage2);
        }
        Glide.with(this).load(pImage).into(image);

        if(categories == null){
            categorie.setTitle("Pangtour");
        }
        else {
            categorie.setTitle(categories);
        }
        name.setText(pName);
        description.setText(pDescription);
        location.setText(pLocation);

        cardView= findViewById(R.id.image1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.custom_dialog);

                ImageView Image= dialog.findViewById(R.id.img);
                Glide.with(getApplication()).load(otherImage_1).into(Image);

                dialog.show();

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        onClick(R.layout.custom_dialog);
                    }

                    private void onClick(int placeView) {
                        dialog.dismiss();
                    }
                });
            }
        });

        cardView= findViewById(R.id.image2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.custom_dialog);

                ImageView Image= dialog.findViewById(R.id.img);
                Glide.with(getApplication()).load(otherImage_2).into(Image);

                dialog.show();

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        onClick(R.layout.custom_dialog);
                    }

                    private void onClick(int placeView) {
                        dialog.dismiss();
                    }
                });
            }
        });

/*
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item_pos = mImageIds.get(position);

               // showDialogBox(item_pos);
            }
        });
    }

  */

     /* public void showDialogBox(int item_pos) {
        final Dialog dialog= new Dialog(this);

        dialog.setContentView(R.layout.custom_dialog);

        ImageView Image= dialog.findViewById(R.id.img);
        TextView btn_Full= dialog.findViewById(R.id.btn_full);
        TextView btn_close= dialog.findViewById(R.id.btn_close);

        String title = getResources().getResourceName(item_pos);


        Image.setImageResource(item_pos);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_Full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(PlaceView.this, Full_View.class);
                i.putExtra("img_id", item_pos);
                startActivity(i);
            }
        });

        dialog.show();
    }


    }

    private void ImageEventListener() {
        db.collectionGroup("Photos")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error !=null) {

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error", error.getMessage());
                            return;
                        } else {
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }

                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                mList.add(dc.getDocument().toObject(Photos.class));
                            }

                            photoAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }

                    }
                });


      */
    }
}