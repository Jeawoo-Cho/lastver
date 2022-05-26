package com.example.capstonefcls.map;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class PolylineSeter {
    public void set_poly(MapView mapView, DocumentReference docRef) {



        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String Coords = "";
                String[] splitedCoords;

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Polyline 좌표 지정.
                        mapView.removeAllPolylines();
                        for(int i=0; i<11; i++) {
                            Coords = (String) document.get(Integer.toString(i));
                            splitedCoords = Coords.split(" ");
                            MapPolyline polyline = new MapPolyline();
                            polyline.setLineColor(Color.argb(128, i*25, 50, 0));
                            polyline.setTag(i);
                            for(int x=0; x<splitedCoords.length/2; x++) {
                                polyline.addPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(splitedCoords[x*2+1]), Double.parseDouble(splitedCoords[x*2])));
                            }
                            mapView.addPolyline(polyline); // 폴리라인 객체 지도에 올리기
                        }

                        // 지도뷰의 중심좌표와 줌레벨을 Polyline이 모두 나오도록 조정.
                        mapView.fitMapViewAreaToShowAllPolylines();
                    } else {

                    }
                } else {

                }
            }
        });

    }
}