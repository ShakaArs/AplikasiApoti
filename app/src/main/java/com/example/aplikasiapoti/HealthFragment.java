package com.example.aplikasiapoti;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.theartofdev.edmodo.cropper.CropImage;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HealthFragment extends Fragment {
    private EditText KategoriPos, NamaPasien, TinggiBadan, BeratBadan,NIK,TensiDarah,upfoto;
    private String setKategoriPos, setNamaPasien, setTinggiBadan, setBeratBadan, setNIK, setTensiDarah,setFoto;
    private DbHealth dbhealth;
    Uri resultUri;

    public HealthFragment() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, container, false);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        Button simpan       = view.findViewById(R.id.buttonKirim);
        KategoriPos         = view.findViewById(R.id.kategori_pos);
        NamaPasien          = view.findViewById(R.id.nama_pasien);
        TinggiBadan         = view.findViewById(R.id.tinggibadan);
        BeratBadan          = view.findViewById(R.id.beratbadan);
        NIK                 = view.findViewById(R.id.nik_user);
        TensiDarah          = view.findViewById(R.id.tensidarah);
        upfoto              = view.findViewById(R.id.upfoto);


        //Inisialisasi dan Mendapatkan Konteks dari DbBook
        dbhealth = new DbHealth(getActivity().getBaseContext());
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                if (setKategoriPos.equals("") || setNamaPasien.equals("") || setTinggiBadan.equals("") || setBeratBadan.equals("") || setNIK.equals("") || setTensiDarah.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(),"Data Kesehatan Belum Lengkap atau Belum diisi, Lengkapi Dahulu!", Toast.LENGTH_SHORT).show();
                }else {
                    setData();
                    saveData();
                    Toast.makeText(getActivity().getApplicationContext(),"Data Kesehatan Tersimpan", Toast.LENGTH_SHORT).show();
                    clearData();
                }
            }
        });

        //intent eksternal untuk masuk kedalam folder atau galeri

        return view;
    }

    //Berisi Statement-Statement Untuk Mendapatkan Input Dari User
    private void setData(){
        setKategoriPos = KategoriPos.getText().toString();
        setTinggiBadan = TinggiBadan.getText().toString();
        setBeratBadan = BeratBadan.getText().toString();
        setNamaPasien = NamaPasien.getText().toString();
        setNIK = NIK.getText().toString();
        setTensiDarah = TensiDarah.getText().toString();
        setFoto  = upfoto.getText().toString();
    }

    //Berisi Statement-Statement Untuk Menyimpan Data Pada Database
    private void saveData() {
        //Mendapatkan Repository dengan Mode Menulis
        SQLiteDatabase create = dbhealth.getWritableDatabase();
        //Membuat Map Baru, Yang Berisi Judul Kolom dan Data Yang Ingin Dimasukan
        ContentValues values = new ContentValues();
        values.put(DbHealth.MyColumns.NIK, setNIK);
        values.put(DbHealth.MyColumns.KategoriPos, setKategoriPos);
        values.put(DbHealth.MyColumns.NamaPasien, setNamaPasien);
        values.put(DbHealth.MyColumns.TinggiBadan, setTinggiBadan);
        values.put(DbHealth.MyColumns.BeratBadan, setBeratBadan);
        values.put(DbHealth.MyColumns.TensiDarah, setTensiDarah);
        values.put(DbHealth.MyColumns.Foto, setFoto);

        create.insert(DbHealth.MyColumns.NamaTabel, null, values);
    }

    private void clearData(){
        KategoriPos.setText("");
        NamaPasien.setText("");
        TensiDarah.setText("");
        TinggiBadan.setText("");
        BeratBadan.setText("");
        NIK.setText("");
        upfoto.setText("");
    }



}