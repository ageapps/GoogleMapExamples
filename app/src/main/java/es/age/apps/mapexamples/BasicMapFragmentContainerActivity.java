package es.age.apps.mapexamples;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by adricacho on 3/10/16.
 */

public class BasicMapFragmentContainerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_maps);

        BasicMapFragment mapLocationFragment = new BasicMapFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, mapLocationFragment, "mapLocationFragment");
        transaction.commit();
    }

}
