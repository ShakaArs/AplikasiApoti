package com.example.aplikasiapoti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private Button ebook;
    private ImageSlider imageSlider;
    private ImageView bukupanduan1,bukupanduan2,bukupanduan3,anakanak,ibuhamil,remajaputri,lansia;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bukupanduan1 = view.findViewById(R.id.bukupanduan1);
        bukupanduan2= view.findViewById(R.id.bukupanduan2);
        bukupanduan3 = view.findViewById(R.id.bukupanduan3);
        remajaputri = view.findViewById(R.id.btn_remajaputri);
        anakanak= view.findViewById(R.id.Btn_anak);
        ibuhamil = view.findViewById(R.id.btn_ibuhamil);
        lansia = view.findViewById(R.id.btn_lansia);
        ebook = view.findViewById(R.id.button2);
        imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://promkes.kemkes.go.id/imagex/content/95SLIDER_WEB.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://moondoggiesmusic.com/wp-content/uploads/2019/12/Iklan-Posyandu.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://hypeabis.id/assets/content/20210824164002000000vMixCapture24August2021121129.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://moondoggiesmusic.com/wp-content/uploads/2019/12/Iklan-Posyandu.jpg", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        bukupanduan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://drive.google.com/file/d/1WrbOr1wAR9rS94eCUGSX87OCGIyGdMWo/view?usp=sharing";
                Intent buku1= new Intent(Intent.ACTION_VIEW);
                buku1.setData(Uri.parse(url));
                startActivity(buku1);
            }
        });
        bukupanduan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://drive.google.com/file/d/1Mm6vsAGk-xbY_-I2CFYtTlTAo0lERYG-/view?usp=sharing";
                Intent buku2= new Intent(Intent.ACTION_VIEW);
                buku2.setData(Uri.parse(url));
                startActivity(buku2);
            }
        });
        bukupanduan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://drive.google.com/file/d/1gxhwvmifZwRKf-f7BvPZcnDJoFNkSjJL/view?usp=sharing";
                Intent buku3= new Intent(Intent.ACTION_VIEW);
                buku3.setData(Uri.parse(url));
                startActivity(buku3);
            }
        });
        ebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://drive.google.com/drive/folders/1DuD3sYmK9xXMLf8N4BZ_FwDHWIbHZhhg?usp=share_link";
                Intent ebook= new Intent(Intent.ACTION_VIEW);
                ebook.setData(Uri.parse(url));
                startActivity(ebook);
            }
        });
        anakanak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://www.wartaexpress.com/babinsa-aktif-dampingi-nakes-monitoring-anak-stunting/";
                Intent anak= new Intent(Intent.ACTION_VIEW);
                anak.setData(Uri.parse(url));
                startActivity(anak);
            }
        });
        ibuhamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://lenggerong.desa.id/posyandu-melati-desa-lenggerong-gelar-senam-ibu-hamil/";
                Intent ibuhamil= new Intent(Intent.ACTION_VIEW);
                ibuhamil.setData(Uri.parse(url));
                startActivity(ibuhamil);
            }
        });
        remajaputri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://puskesmas.kuburayakab.go.id/punggur/read/87/posyandu-remaja";
                Intent remajaputri= new Intent(Intent.ACTION_VIEW);
                remajaputri.setData(Uri.parse(url));
                startActivity(remajaputri);
            }
        });
        lansia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://puskesmas.kuburayakab.go.id/punggur/read/94/kegiatan-posyandu-lansia-melati";
                Intent lansia= new Intent(Intent.ACTION_VIEW);
                lansia.setData(Uri.parse(url));
                startActivity(lansia);
            }
        });

        return view;
    }



}