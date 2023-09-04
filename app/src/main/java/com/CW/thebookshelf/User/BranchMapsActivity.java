package com.CW.thebookshelf.User;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.CW.thebookshelf.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.CW.thebookshelf.databinding.ActivityBranchMapsBinding;

public class BranchMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityBranchMapsBinding binding;

    public void onBackPressed() {
        Intent intent = new Intent(BranchMapsActivity.this, DashBordActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBranchMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng unawatuna = new LatLng(6.0174, 80.2489);
        LatLng Galle = new LatLng(6.0329, 80.2168);
        LatLng Matara = new LatLng(5.9496, 80.5469);
        LatLng Colombo = new LatLng(6.9271, 79.8612);
        mMap.addMarker(new MarkerOptions().position(unawatuna).title("Book Shelf unawatuna"));
        mMap.addMarker(new MarkerOptions().position(Galle).title("Book Shelf Galle"));
        mMap.addMarker(new MarkerOptions().position(Matara).title("Book Shelf Matara"));
        mMap.addMarker(new MarkerOptions().position(Colombo).title("Book Shelf Colombo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Galle));
    }
}