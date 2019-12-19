package com.apps.b4kwallpapers.WallpaperClasses;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps.b4kwallpapers.MainActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.apps.b4kwallpapers.Adapters.AbstractWallpapersAdapter;
import com.apps.b4kwallpapers.R;
import com.apps.b4kwallpapers.UploadPages.AbstractUpload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AbstractWallpapers extends AppCompatActivity {


    private AdView mAdView;
    private AbstractWallpapersAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<AbstractUpload> mUploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_wallpapers);


        mAdView = findViewById(R.id.abstractadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }

        });

       /* Toolbar myChildToolbar = (Toolbar) findViewById(R.id.abstracttoolbar);
        setSupportActionBar(myChildToolbar);


        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setTitle("");
        ab.setDisplayHomeAsUpEnabled(true);*/


        mRecyclerView = findViewById(R.id.abstract_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Abstract");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    AbstractUpload upload = postSnapshot.getValue(AbstractUpload.class);
                    mUploads.add(upload);
                }
                mAdapter = new AbstractWallpapersAdapter(AbstractWallpapers.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(AbstractWallpapers.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AbstractWallpapers.this, MainActivity.class );
        startActivity(intent);
        finish();
    }
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
