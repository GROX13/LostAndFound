package com.steps.lostfound.activities;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.steps.lostfound.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    LatLng mSelectedPoint;
    Circle mPointCircle;
    Marker mMarker;
    SeekBar mRadiusSeekBar;


    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "long";
    public static final String RADIUS = "rad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar(toolbar);

        mRadiusSeekBar = (SeekBar) findViewById(R.id.radius);
        final TextView textView = (TextView) findViewById(R.id.radius_text_view);
        final String radiusStr = getString(R.string.radius);

        mRadiusSeekBar.setInterpolator(new DecelerateInterpolator(4));

        mRadiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 1)
                    progress = 1;
                if (mPointCircle != null) {
                    int radius = countRadiusForProgress(progress);
                    mPointCircle.setRadius(radius);
                    textView.setText(radiusStr + " : " + radius);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    private int countRadiusForProgress(int progress) {
        switch (progress) {
            case 0:
                return 5;
            case 1:
                return 10;
            case 2:
                return 25;
            case 3:
                return 35;
            case 4:
                return 50;
            case 5:
                return 75;
            case 6:
                return 100;
            case 7:
                return 150;
            case 8:
                return 200;
            case 9:
                return 300;
            case 10:
                return 400;
            case 11:
                return 500;
            case 12:
                return 750;
            case 13:
                return 1000;
            case 14:
                return 1500;
            case 15:
                return 2500;
            case 16:
                return 5000;
            case 17:
                return 7500;
            case 18:
                return 10000;
        }
        return 10000;
    }

    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_activity, menu);
        MenuItem item = menu.findItem(R.id.done);

        if (mSelectedPoint != null) {
            if (item != null)
                item.setVisible(true);

        } else {
            if (item != null)
                item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent returnIntent = new Intent();

        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED, returnIntent);
                finish();
                return true;
            case R.id.done:
                if (mSelectedPoint == null || mPointCircle == null) {
                    return false;
                }
                returnIntent.putExtra(RADIUS, (int)Math.round(mPointCircle.getRadius()));
                returnIntent.putExtra(LONGITUDE, mSelectedPoint.longitude);
                returnIntent.putExtra(LATITUDE, mSelectedPoint.latitude);

                setResult(RESULT_OK, returnIntent);
                finish();
                return true;
        }
        return false;
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        map.getUiSettings().setMapToolbarEnabled(false);
        map.setMyLocationEnabled(true);


        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);
        Location location = service.getLastKnownLocation(provider);
        if (location != null) {
            LatLng myLocation = new LatLng(location.getLatitude(),
                    location.getLongitude());
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,
                    15));
        }

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                if (mSelectedPoint != null) {
                    adjust(point);
                    mMarker.setPosition(mSelectedPoint);
                    return;
                }
                invalidateOptionsMenu();
                mSelectedPoint = point;
                mMarker = map.addMarker(new MarkerOptions()
                        .position(point).draggable(true)
                        .title(getString(R.string.item)));

                addCircleToMap(map);
                map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {
                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {
                        adjust(marker.getPosition());
                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                        adjust(marker.getPosition());
                    }
                });

            }
        });
    }

    private void adjust(LatLng newPos) {
        mSelectedPoint = newPos;
        if (mPointCircle == null || mSelectedPoint == null)
            return;
        mPointCircle.setCenter(mSelectedPoint);
    }

    private void addCircleToMap(GoogleMap map) {

        CircleOptions circleOptions = new CircleOptions()
                .center(mSelectedPoint)
                .radius(countRadiusForProgress(mRadiusSeekBar.getProgress()))
                .fillColor(getResources().getColor(R.color.circle_color))
                .strokeColor(getResources().getColor(R.color.circle_stroke_color))
                .strokeWidth(5.0f); // In meters

        mPointCircle = map.addCircle(circleOptions);
    }
}