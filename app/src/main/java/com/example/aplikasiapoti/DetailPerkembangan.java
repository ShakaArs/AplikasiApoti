package com.example.aplikasiapoti;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
public class DetailPerkembangan extends AppCompatActivity{
    private DbHealth MyDatabase;
    private TextView ShowKategoriPos, ShowNamaPasien, ShowTinggiBadan, ShowBeratBadan, SHowNIK, ShowTensiDarah;
    private String Id;
    private ImageView Back;
    private Button Update;
    private String KodeSend = "KODE";
    private String sendVal = "id";
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_perkembangan);

        MyDatabase = new DbHealth(getBaseContext());

        Update =    findViewById(R.id.buttonupdate);
        Back = findViewById(R.id.back);
        ShowKategoriPos = findViewById(R.id.dtl_kategori);
        ShowNamaPasien = findViewById(R.id.dtl_nama);
        ShowTinggiBadan = findViewById(R.id.dtl_tinggibadan);
        ShowBeratBadan = findViewById(R.id.dtl_beratbadan);
        SHowNIK = findViewById(R.id.dtl_NIK);
        ShowTensiDarah = findViewById(R.id.dtl_tensidarah);

        getData();

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Kode = SHowNIK.getText().toString();
                if (Kode != null && Kode != ""){
                    Intent i = new Intent(DetailPerkembangan.this, UpdateActivity.class);
                    i.putExtra(KodeSend, Kode);
                    startActivity(i);

                }
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Kode = SHowNIK.getText().toString();
                Intent intent = new Intent(DetailPerkembangan.this,MainActivity.class);
                intent.putExtra(sendVal,Kode);
                startActivity(intent);
            }
        });


    }
    private void getData(){

        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
            Cursor cursor = ReadData.rawQuery("SELECT * FROM " + DbHealth.MyColumns.NamaTabel  , null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String KategoriPos = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.KategoriPos));
            @SuppressLint("Range") String NamaPasien = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.NamaPasien));
            @SuppressLint("Range") String TinggiBadan = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.TinggiBadan));
            @SuppressLint("Range") String BeratBadan = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.BeratBadan));
            @SuppressLint("Range") String NIK = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.NIK));
            @SuppressLint("Range") String TensiDarah = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.TensiDarah));


            ShowKategoriPos.setText(KategoriPos);
            ShowNamaPasien.setText(NamaPasien);
            ShowTensiDarah.setText(TensiDarah);
            ShowTinggiBadan.setText(TinggiBadan);
            ShowBeratBadan.setText(BeratBadan);
            SHowNIK.setText(NIK);


        }
    }
}
