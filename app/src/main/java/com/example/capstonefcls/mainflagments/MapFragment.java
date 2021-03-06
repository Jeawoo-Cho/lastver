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


        ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view); //????????? ??????
        mapViewContainer.addView(mapView);

        //????????? ??????
        //mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);


        //Searchparser(?????????)
        SearchParser searchParser = new SearchParser();


        //Coordparse (?????????)
        CoordParser apiData = new CoordParser();


        //PolyLineSeter?????????
        PolylineSeter polylineSeter = new PolylineSeter();

        //???????????? (?????????)
        TextView textView1 = (TextView) v.findViewById(R.id.text1) ;
        System.out.println("???????????????");

        //?????????????????? ????????? ??? ??????
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("mountains").document("?????????");

        //alert ??????
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("????????? ??????").setMessage("?????? ????????? ?????? ?????????????????????. ???????????? ??????????????????");
        AlertDialog alertDialog = alert.create();

        //??????(SearchView) ?????????
        SearchView searchView = v.findViewById(R.id.SearchView1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.equals("?????????")) {
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