package com.example.aplikasiapoti;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;


public class ProgressFragment extends Fragment {
    private DbHealth MyDatabase;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private ArrayList NIKList;
    private ArrayList KategoriList;
    private ArrayList NamaPasienList;
    private ArrayList FotoList;

    public ProgressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NIKList = new ArrayList<>();
                KategoriList   = new ArrayList<>();
                NamaPasienList = new ArrayList<>();
                FotoList = new ArrayList<>();

                MyDatabase = new DbHealth(getActivity().getBaseContext());
                recyclerView = view.findViewById(R.id.rv_perkembangan);
                getData();
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                adapter = new RecyclerViewAdapter(NIKList,KategoriList, NamaPasienList, FotoList);
                //Memasang Adapter pada RecyclerView
                recyclerView.setAdapter(adapter);
                //Membuat Underline pada Setiap Item Didalam List
                DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
                itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.line));
                recyclerView.addItemDecoration(itemDecoration);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        KategoriList   = new ArrayList<>();
        NamaPasienList = new ArrayList<>();
        FotoList = new ArrayList<>();;
        NIKList = new ArrayList<>();

        MyDatabase = new DbHealth(getActivity().getBaseContext());
        recyclerView = view.findViewById(R.id.rv_perkembangan);
        getData();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(NIKList,KategoriList, NamaPasienList, FotoList);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);
        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);

        return view;
    }

    //Berisi Statement-Statement Untuk Mengambi Data dari Database
    @SuppressLint("Recycle")
    protected void getData() {
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM " + DbHealth.MyColumns.NamaTabel, null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for (int count = 0; count < cursor.getCount(); count++) {

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

//            Mengambil data dari sesuai kolom array

            KategoriList.add(cursor.getString(1));
            NamaPasienList.add(cursor.getString(5));
            FotoList.add(cursor.getString(0));
            NIKList.add(cursor.getString(0));
        }
    }

}