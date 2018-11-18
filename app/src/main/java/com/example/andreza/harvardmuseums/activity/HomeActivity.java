package com.example.andreza.harvardmuseums.activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.andreza.harvardmuseums.R;
import com.example.andreza.harvardmuseums.fragment.ArtworkDetailFragment;
import com.example.andreza.harvardmuseums.fragment.ArtworkFragment;
import com.example.andreza.harvardmuseums.fragment.ExhibitionDetailFragment;
import com.example.andreza.harvardmuseums.fragment.ExhibitionFragment;
import com.example.andreza.harvardmuseums.fragment.FilterFragment;
import com.example.andreza.harvardmuseums.fragment.Museum1DetailFragment;
import com.example.andreza.harvardmuseums.fragment.Museum2DetailFragment;
import com.example.andreza.harvardmuseums.fragment.Museum3DetailFragment;
import com.example.andreza.harvardmuseums.fragment.MuseumFragment;
import com.example.andreza.harvardmuseums.fragment.UserFragment;
import com.example.andreza.harvardmuseums.interfaces.ArtworkListenerDetail;
import com.example.andreza.harvardmuseums.interfaces.ListenerArtwork;
import com.example.andreza.harvardmuseums.pojo.Artwork;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements MuseumFragment.Listener,
        ExhibitionFragment.Listener, ExhibitionDetailFragment.Listener,
        UserFragment.Listener, ListenerArtwork, ArtworkListenerDetail {

    public static final String TITULO = "titulo";
    public static final String OBJ_ARTWORK = "artwork";
    private static final String TAG = "HomeActivity";

    private BottomNavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.navigation_museum:
                        fragment = new MuseumFragment();
                        showFragment(fragment);
                        break;
                    case R.id.navigation_exhibition:
                       //TODO
                        fragment = new ExhibitionFragment();
                        showFragment(fragment);
                        break;
                    case R.id.navigation_artwork:
                        fragment = new ArtworkFragment();
                        showFragment(fragment);
                        break;
                    case R.id.navigation_user:
                        fragment = new UserFragment();
                        showFragment(fragment);
                        break;
                }
                return true;
            }
        });
        showFragment(new MuseumFragment());

    }

    public void showFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void goToMuseumDetail(int museumID) {

        if (museumID == 1) {
            showFragment(new Museum1DetailFragment());
        }
        if (museumID == 2) {
            showFragment(new Museum2DetailFragment());
        }
        if (museumID == 3) {
            showFragment(new Museum3DetailFragment());
        }

    }


  @Override
    public void goToExhibitionDetail(){
        showFragment(new ExhibitionDetailFragment());
  }


    public void goToFilter(){
        showFragment(new FilterFragment());
    }

    public void goToArtwork(){
        showFragment(new ArtworkFragment());
    }

    @Override
    public void goToArtworkDetail() {
    }

    @Override //TODO pelo que entendi essa interface pode ser deletada agora "goToArtwork"
    public void goToArtworkDetail(Object obj) {
        /*ArtworkDetailFragment detailFragment = new ArtworkDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putString(OBJ_ARTWORK,obj);
        detailFragment.setArguments(bundle);
        showFragment(detailFragment);*/
    }

    @Override
    public void iniciarFragmentArtworkDetail(Artwork artwork) {
        ArtworkDetailFragment detailFragment = new ArtworkDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(OBJ_ARTWORK,artwork);
        detailFragment.setArguments(bundle);

        showFragment(detailFragment);
    }
}