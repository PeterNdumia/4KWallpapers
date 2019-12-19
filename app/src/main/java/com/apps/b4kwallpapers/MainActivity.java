package com.apps.b4kwallpapers;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.apps.b4kwallpapers.WallpaperClasses.AbstractWallpapers;
import com.apps.b4kwallpapers.WallpaperClasses.AnimalsWallpaper;
import com.apps.b4kwallpapers.WallpaperClasses.BlackWallpapers;
import com.apps.b4kwallpapers.WallpaperClasses.CitiesWallpapers;
import com.apps.b4kwallpapers.WallpaperClasses.CreepyWallpapers;
import com.apps.b4kwallpapers.WallpaperClasses.DoubleExposureWallpapers;
import com.apps.b4kwallpapers.WallpaperClasses.NatureWallpapers;
import com.apps.b4kwallpapers.WallpaperClasses.PatternsWallpapers;
import com.apps.b4kwallpapers.WallpaperClasses.PyroWallpapers;
import com.apps.b4kwallpapers.WallpaperClasses.SlicesSkyWallpapers;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, "ca-app-pub-3609953011019731~7349667013");

        mAdView = findViewById(R.id.mainadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }

        });


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3609953011019731/4787669582");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "4K Wallpapers");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
            // Handle the camera action
        } else if (id == R.id.nav_rate) {

            Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
            }

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void abstractImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, AbstractWallpapers.class );
        startActivity(intent);
        finish();


    }
    public void animalsImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, AnimalsWallpaper.class );
        startActivity(intent);
        finish();
        mInterstitialAd.show();


    }
    public void blackImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, BlackWallpapers.class );
        startActivity(intent);
        finish();



    }
    public void citiesImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, CitiesWallpapers.class );
        startActivity(intent);
        finish();
        mInterstitialAd.show();


    }
    public void creepyImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, CreepyWallpapers.class );
        startActivity(intent);
        finish();


    }
    public void doubleExposureImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, DoubleExposureWallpapers.class );
        startActivity(intent);
        finish();
        mInterstitialAd.show();

    }
    public void natureImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, NatureWallpapers.class );
        startActivity(intent);
        finish();
        mInterstitialAd.show();

    }
    public void patternsImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, PatternsWallpapers.class );
        startActivity(intent);
        finish();


    }
    public void pyroImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, PyroWallpapers.class );
        startActivity(intent);
        finish();
        mInterstitialAd.show();

    }
    public void sliceskyImageonClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, SlicesSkyWallpapers.class );
        startActivity(intent);
        finish();
        mInterstitialAd.show();

    }

}
