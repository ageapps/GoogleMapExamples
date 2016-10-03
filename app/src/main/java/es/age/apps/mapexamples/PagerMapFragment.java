package es.age.apps.mapexamples;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Random;

import static com.google.android.gms.analytics.internal.zzy.i;
import static es.age.apps.mapexamples.R.id.map;

/**
 * Created by adricacho on 2/10/16.
 */

public class PagerMapFragment extends Fragment implements OnMapReadyCallback {


    private View view;
    private SupportMapFragment mapFragment;
    private PagerMapFragment.OnMapLoadedListener onMapLoadedListener;
    private GoogleMap mMap;
    private String TAG = "MapLocationFragment";
    public View map;


    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PagerMapFragment newInstance(int sectionNumber) {
        PagerMapFragment fragment = new PagerMapFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    // Container Activity must implement this interface
    public interface OnMapLoadedListener {
        public void onMapLoaded(GoogleMap map);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_multiple_maps_pager, container, false);
        FragmentManager fm = getChildFragmentManager();
        mapFragment = ((SupportMapFragment) fm.findFragmentById(R.id.map));
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        }
        map = mapFragment.getView();
        TextView textView = (TextView) view.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        mapFragment.getMapAsync(this);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (onMapLoadedListener != null) {
            onMapLoadedListener.onMapLoaded(googleMap);

        }
        Log.d(TAG, "onMapReady: ");
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        for (int i = 0; i < 10; i++) {
            LatLng location = new LatLng(-34 + i, 151 - i);
            mMap.addMarker(new MarkerOptions().position(location).title("Marker in Location " + i));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));
    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            onMapLoadedListener = (PagerMapFragment.OnMapLoadedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onMapLoadedListener");
        }
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            Log.d(TAG, "setMenuVisibility: " + "VISIBLE " + (view == null));
            if (view != null) {
                map = view.findViewById(R.id.container_maps);
                map.setVisibility(View.VISIBLE);
                onMapLoadedListener.onMapLoaded(mMap); // set current location
            }
        } else {
            Log.d(TAG, "setMenuVisibility: " + "GONE " + (view == null));
            if (view != null) {
                map = view.findViewById(R.id.container_maps);
                map.setVisibility(View.GONE);
            }
        }
    }



}