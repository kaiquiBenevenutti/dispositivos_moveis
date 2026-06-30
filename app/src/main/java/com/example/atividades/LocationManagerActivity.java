package com.example.atividades;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;

public class LocationManagerActivity extends AppCompatActivity {
    private static final double DEFAULT_ZOOM = 16.0;

    LocationManager locationManager;
    TextView textView;
    MapView map;
    Marker marker;
    Location currentLocation;
    boolean mapaCentralizado = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location_manager);

        AppCompatImageButton buttonCenterLocation = findViewById(R.id.buttonCenterLocation);
        textView = findViewById(R.id.textView);
        map = findViewById(R.id.mapView);

        buttonCenterLocation.setOnClickListener(v -> {
            mapaCentralizado = true;
            centerMapOnCurrentLocation();
        });

        initMap();

        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", MODE_PRIVATE));
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (checkPermission()) {
            startLocationUpdates();
        }

    }

    public void initMap() {
        map.getController().setZoom(DEFAULT_ZOOM);
        map.setMultiTouchControls(true);
        map.getOverlays().add(new org.osmdroid.views.overlay.ScaleBarOverlay(map));

        map.getOverlays().add(new Overlay() {
            @Override
            public boolean onTouchEvent(android.view.MotionEvent event, MapView mapView) {
                if (event.getActionMasked() == android.view.MotionEvent.ACTION_DOWN) {
                    mapaCentralizado = false;
                }
                return false;
            }
        });
    }

    public void showLocationInMap(Location location) {
        currentLocation = location;
        textView.setText(getString(
                R.string.location_coordinates,
                location.getLatitude(),
                location.getLongitude()
        ));

        if (marker == null) {
            marker = new Marker(map);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            map.getOverlays().add(marker);
        }

        GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
        marker.setPosition(geoPoint);

        if (mapaCentralizado) {
            centralizeMap(geoPoint);
        }

        map.invalidate();
    }

    private void startLocationUpdates() {
        if (!checkPermission()) {
            return;
        }

        Location lastKnownLocation = getLastKnownLocation();
        if (lastKnownLocation != null) {
            showLocationInMap(lastKnownLocation);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this::showLocationInMap);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this::showLocationInMap);
    }

    private void centerMapOnCurrentLocation() {
        if (currentLocation == null) {
            currentLocation = getLastKnownLocation();
        }

        if (currentLocation == null) {
            Toast.makeText(this, R.string.current_location_unavailable, Toast.LENGTH_SHORT).show();
            return;
        }

        GeoPoint geoPoint = new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
        centralizeMap(geoPoint);
    }

    private void centralizeMap(GeoPoint geoPoint) {
        map.getController().setZoom(DEFAULT_ZOOM);
        map.getController().animateTo(geoPoint);
        map.invalidate();
    }

    private Location getLastKnownLocation() {
        if (!checkPermission()) {
            return null;
        }

        Location gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (gpsLocation == null) {
            return networkLocation;
        }

        if (networkLocation == null) {
            return gpsLocation;
        }

        return gpsLocation.getTime() >= networkLocation.getTime() ? gpsLocation : networkLocation;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        } else {
            textView.setText(R.string.permission_denied);
        }
    }

    public boolean checkPermission() {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return false;
        }
        return true;
    }
}