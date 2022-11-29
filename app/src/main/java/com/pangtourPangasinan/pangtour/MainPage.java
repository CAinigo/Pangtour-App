package com.pangtourPangasinan.pangtour;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.pangtourPangasinan.pangtour.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    private static final int GALLERY_INTENT_CODE= 1023;
    private DatabaseReference reference;
    StorageReference storageReference;
    private FirebaseAuth mAuth;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    ImageView profileImage;
    private FirebaseUser user;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        storageReference = FirebaseStorage.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
        mAuth= FirebaseAuth.getInstance();


        mViewPager =(ViewPager) findViewById(R.id.viewpager);
        mTabLayout =(TabLayout) findViewById(R.id.tabs);

        profileImage= (ImageView) findViewById(R.id.profile_image);
        profileImage.setOnClickListener(this);


        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);

        user= FirebaseAuth.getInstance().getCurrentUser();

        if(user !=null) {
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

            StorageReference profileRef= storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"profile.jpg");
            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(profileImage);
                }
            });

        }
    }


    private void setupViewPager (ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_1(), "Popular");
        adapter.addFragment(new Fragment_2(), "Hotels");
        adapter.addFragment(new Fragment_3(), "Beach");
        adapter.addFragment(new Fragment_4(), "Falls");
        adapter.addFragment(new Fragment_5(), "Church");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments= new ArrayList<>();
        private final List<String> mFragmentTitles= new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (user !=null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainPage.this);
            alertDialog.setTitle("Exit app");
            alertDialog.setMessage("Do you want to exit app? ");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            });
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        } else {
            startActivity(new Intent(MainPage.this, GuestLogin.class));
        }
    }


}