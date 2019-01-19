package com.example.jws.bzapp;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AnalysisActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double mLat = 0;
    double mLong = 0;
    LinearLayout pLayout1, pLayout2, pLayout3, pLayout4;
    ImageView pbtn1, pbtn2, pbtn3, pbtn4;
    ImageButton btnBack;
    ImageButton btnHome;
    Geocoder geocoder;
    int a;
    Button btnArea;
    Button btnCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        geocoder = new Geocoder(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        mLat = intent.getDoubleExtra("mLat", 0);
        mLong = intent.getDoubleExtra("mLong", 0);
        a = intent.getIntExtra("a", 0);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnCategory = (Button) findViewById(R.id.btnCategory);
        btnArea = (Button) findViewById(R.id.btnArea);


                /*
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AnalysisActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                mBuilder.setTitle("업종 선택");
                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinnertest);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AnalysisActivity.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.category));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);

                mBuilder.setPositiveButton("완료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("대분류")){
                            test2 = mSpinner.getSelectedItem().toString();
                            txtcategory.setText(test2);
                            Toast.makeText(AnalysisActivity.this,mSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }
                    }
                });

                mBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();*/


        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        /* final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        //범위 배열
        final String[] number1 = {"범위를 선택해주세요.", "100m", "200m", "500m", "1km"};

        //스피너에 배열 입력
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, number1);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //범위 값 변수
                test1 = spinner1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); */


        // 인구분석 버튼
        final LinearLayout pLayout1 = (LinearLayout) findViewById(R.id.pLayout1);
        final ImageView pbtn1 = (ImageView) findViewById(R.id.pbtn1);

        pbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pLayout1.getVisibility() == View.VISIBLE) {
                    pLayout1.setVisibility(View.GONE);
                    pbtn1.setImageResource(R.drawable.under);
                } else {
                    pLayout1.setVisibility(View.VISIBLE);
                    pbtn1.setImageResource(R.drawable.over);
                }
            }
        });

        // 매출분석 버튼
        final LinearLayout pLayout2 = (LinearLayout) findViewById(R.id.pLayout2);
        final ImageView pbtn2 = (ImageView) findViewById(R.id.pbtn2);

        pbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pLayout2.getVisibility() == View.VISIBLE) {
                    pLayout2.setVisibility(View.GONE);
                    pbtn2.setImageResource(R.drawable.under);
                } else {
                    pLayout2.setVisibility(View.VISIBLE);
                    pbtn2.setImageResource(R.drawable.over);
                }
            }
        });

        // 상권분석 버튼
        final LinearLayout pLayout3 = (LinearLayout) findViewById(R.id.pLayout3);
        final ImageView pbtn3 = (ImageView) findViewById(R.id.pbtn3);

        pbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pLayout3.getVisibility() == View.VISIBLE) {
                    pLayout3.setVisibility(View.GONE);
                    pbtn3.setImageResource(R.drawable.under);
                } else {
                    pLayout3.setVisibility(View.VISIBLE);
                    pbtn3.setImageResource(R.drawable.over);
                }
            }
        });

        // 점포현황 버튼
        final LinearLayout pLayout4 = (LinearLayout) findViewById(R.id.pLayout4);
        final ImageView pbtn4 = (ImageView) findViewById(R.id.pbtn4);

        pbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pLayout4.getVisibility() == View.VISIBLE) {
                    pLayout4.setVisibility(View.GONE);
                    pbtn4.setImageResource(R.drawable.under);
                } else {
                    pLayout4.setVisibility(View.VISIBLE);
                    pbtn4.setImageResource(R.drawable.over);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalysisActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalysisActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    //anal.xml의 android:onClick 이용해서 메서드 정의
    public void mOnPopupClick(View v) {
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, Dialog_Area.class);
        startActivityForResult(intent, 1);
    }

    //다이얼로그 실행후 결과값 받는 메서드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");
                btnArea.setText(result);
            }
        }
    }

    public void mOnPopupClick2(View v) {
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, Dialog_Category.class);
        startActivityForResult(intent, 1);
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
        if (a == 100) {
            onAddCircle100(100);
        } else if (a == 200) {
            onAddCircle100(200);
        } else if (a == 500) {
            onAddCircle100(500);
        } else if (a == 1000) {
            onAddCircle100(1000);
        }
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(mLat, mLong);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
//        onAddCircle(100);
    }

    //
    public void onAddCircle100(int a) {
        LatLng position = new LatLng(mLat, mLong);

        //나의 위치 마커
        //MarkerOptions mymarker = new MarkerOptions().position(position);

        //사이클 반경 설정
        CircleOptions circle = new CircleOptions().center(position).radius(a)//반경
                .strokeWidth(3)//선 넓이
                .fillColor(0x44808080)
                .strokeColor(Color.GRAY);
        //this.mGoogleMap.addMarker(mymarker);
        this.mMap.addCircle(circle);
    }

    public void onAddCircle200(int a) {
        LatLng position = new LatLng(mLat, mLong);

        //나의 위치 마커
        //MarkerOptions mymarker = new MarkerOptions().position(position);

        //사이클 반경 설정
        CircleOptions circle = new CircleOptions().center(position).radius(a)//반경
                .strokeWidth(3)//선 넓이
                .fillColor(0x44808080)
                .strokeColor(Color.GRAY);
        //this.mGoogleMap.addMarker(mymarker);
        this.mMap.addCircle(circle);
    }

    public void onAddCircle500(int a) {
        LatLng position = new LatLng(mLat, mLong);

        //나의 위치 마커
        //MarkerOptions mymarker = new MarkerOptions().position(position);

        //사이클 반경 설정
        CircleOptions circle = new CircleOptions().center(position).radius(a)//반경
                .strokeWidth(3)//선 넓이
                .fillColor(0x44808080)
                .strokeColor(Color.GRAY);
        //this.mGoogleMap.addMarker(mymarker);
        this.mMap.addCircle(circle);
    }

    public void onAddCircle1000(int a) {
        LatLng position = new LatLng(mLat, mLong);

        //나의 위치 마커
        //MarkerOptions mymarker = new MarkerOptions().position(position);

        //사이클 반경 설정
        CircleOptions circle = new CircleOptions().center(position).radius(a)//반경
                .strokeWidth(3)//선 넓이
                .fillColor(0x44808080)
                .strokeColor(Color.GRAY);
        //this.mGoogleMap.addMarker(mymarker);
        this.mMap.addCircle(circle);

    }
}