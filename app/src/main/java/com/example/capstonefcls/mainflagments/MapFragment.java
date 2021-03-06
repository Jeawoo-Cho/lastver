package com.example.capstonefcls.mainflagments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.capstonefcls.R;
import com.example.capstonefcls.map.CoorData;
import com.example.capstonefcls.map.CoordParser;
import com.example.capstonefcls.map.PolylineSeter;
import com.example.capstonefcls.map.SearchCoord;
import com.example.capstonefcls.map.SearchParser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import net.daum.mf.map.api.MapView;

import java.util.ArrayList;


public class MapFragment extends Fragment {

    public MapFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);

        v.findViewById(R.id.boardFrag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(v).navigate(R.id.action_mapFragment_to_boardFragment);
            }
        });

        v.findViewById(R.id.rankFrag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(v).navigate(R.id.action_mapFragment_to_rankFragment);
            }
        });

        v.findViewById(R.id.profileFrag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(v).navigate(R.id.action_mapFragment_to_profileFragment);
            }
        });


        /////////////////////////////////

        MapView mapView = new MapView(getActivity());


        ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view); //뷰그룹 사용
        mapViewContainer.addView(mapView);

        //트래킹 모드
        //mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);


        //Searchparser(테스트)
        SearchParser searchParser = new SearchParser();


        //Coordparse (테스트)
        CoordParser apiData = new CoordParser();


        //PolyLineSeter클래스
        PolylineSeter polylineSeter = new PolylineSeter();

        //텍스트뷰 (테스트)
        TextView textView1 = (TextView) v.findViewById(R.id.text1) ;
        System.out.println("안녕하세요");

        //파이어베이스 초기화 및 설정
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("mountains").document("봉의산");

        //alert 설정
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("미구현 항목").setMessage("현재 봉의산 까지 구현되었습니다. 봉의산을 검색해주세요");
        AlertDialog alertDialog = alert.create();

        //검색(SearchView) 테스트
        SearchView searchView = v.findViewById(R.id.SearchView1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.equals("봉의산")) {
                    polylineSeter.set_poly(mapView, docRef);
                }
                else alertDialog.show();


                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        /////////////////////////////////


        return v;
    }
}