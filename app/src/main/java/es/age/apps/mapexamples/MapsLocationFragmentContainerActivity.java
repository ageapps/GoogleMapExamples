package es.age.apps.mapexamples;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;

import es.age.apps.mapexamples.utils.LocationActivity;

public class MapsLocationFragmentContainerActivity extends LocationActivity implements MapLocationFragment.OnMapLoadedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_maps);

        MapLocationFragment mapLocationFragment = new MapLocationFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, mapLocationFragment, "mapLocationFragment");
        transaction.commit();
    }

    @Override
    public void onMapLoaded(GoogleMap map) {
        super.onLocationMapReady(map);
    }
}


