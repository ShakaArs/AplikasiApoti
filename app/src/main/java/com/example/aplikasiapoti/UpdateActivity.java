package com.example.aplikasiapoti;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class UpdateActivity extends AppCompatActivity {
    private DbHealth MyDatabase;
    private EditText NewKategoriPos, NewNamaPasien, NewTinggiBadan, NewBeratBadan, NewNIK, NewTensiDarah;
    private Button NewImage;
    private String getNewKode;
    private Button Update, Open;
    private String KodeSend = "KODE";
    private ImageView Back;
    Uri resultUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_perkembangan);
        MyDatabase = new DbHealth(getBaseContext());

        NewKategoriPos = findViewById(R.id.edt_kategori);
        NewNamaPasien = findViewById(R.id.edt_nama_pasien);
        NewTinggiBadan = findViewById(R.id.edt_tinggibadan);
        NewBeratBadan = findViewById(R.id.edt_beratbadan);
        NewNIK = findViewById(R.id.edt_lingkarlengan);
        NewTensiDarah = findViewById(R.id.edt_tensidarah);

        Back = findViewById(R.id.back);

        Bundle extras = getIntent().getExtras();
        getNewKode = extras.getString(KodeSend);
        Update = findViewById(R.id.edt_buttonKirim);

        SQLiteDatabase ReadDb = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadDb.rawQuery("SELECT * FROM " + DbHealth.MyColumns.NamaTabel + " WHERE " + DbHealth.MyColumns.NIK + "=" + getNewKode, null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String KategoriPos = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.KategoriPos));
            @SuppressLint("Range") String NamaPasien = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.NamaPasien));
            @SuppressLint("Range") String TinggiBadan = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.TinggiBadan));
            @SuppressLint("Range") String BeratBadan = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.BeratBadan));
            @SuppressLint("Range") String NIK = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.NIK));
            @SuppressLint("Range") String TensiDarah = cursor.getString(cursor.getColumnIndex(DbHealth.MyColumns.TensiDarah));;


            NewKategoriPos.setText(KategoriPos);
            NewNamaPasien.setText(NamaPasien);
            NewTinggiBadan.setText(TinggiBadan);
            NewBeratBadan.setText(BeratBadan);
            NewNIK.setText(NIK);
            NewTensiDarah.setText(TensiDarah);

            Update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setUpdateData();
                    startActivity(new Intent(UpdateActivity.this, MainActivity.class));

                }
            });
//            Back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                finish();
//                }
//            });
//            Open.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    CropImage.startPickImageActivity(UpdateActivity.this);
//                }
//            });
        }
    }

    private void setUpdateData() {
        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();

            String getKategori = NewKategoriPos.getText().toString().trim();
            String getTinggiBadan = NewTinggiBadan.getText().toString().trim();
            String getBeratBadan = NewBeratBadan.getText().toString().trim();
            String getNIK = NewNIK.getText().toString().trim();
            String getTensiDarah = NewTensiDarah.getText().toString().trim();
            String getNamaPasien = NewNamaPasien.getText().toString().trim();


            //Memasukan Data baru pada
            ContentValues values = new ContentValues();
            values.put(DbHealth.MyColumns.KategoriPos, getKategori);
            values.put(DbHealth.MyColumns.TensiDarah, getTensiDarah);
            values.put(DbHealth.MyColumns.TinggiBadan, getTinggiBadan);
            values.put(DbHealth.MyColumns.BeratBadan, getBeratBadan);
            values.put(DbHealth.MyColumns.NIK, getNIK);
            values.put(DbHealth.MyColumns.NamaPasien, getNamaPasien);
            values.put(DbHealth.MyColumns.Foto, String.valueOf(resultUri));

            //Untuk Menentukan Data/Item yang ingin diubah, berdasarkan NIM
            String selection = DbHealth.MyColumns.NIK + " LIKE ?";
            String[] selectionArgs = {getNewKode};
            ReadData.update(DbHealth.MyColumns.NamaTabel, values, selection, selectionArgs);
            Toast.makeText(getApplicationContext(), "Berhasil Diubah", Toast.LENGTH_SHORT).show();
        }
//
//    @TargetApi(Build.VERSION_CODES.M)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
//                && resultCode == Activity.RESULT_OK) {
//            Uri imageuri = CropImage.getPickImageResultUri(this, data);
//            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
//                resultUri = imageuri;
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
//                        , 0);
//            } else {
//                startCrop(imageuri);
//            }
//        }
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                NewImage.setImageURI(result.getUri());
//                resultUri = result.getUri();
//            }
//        }
//    }
//
//    private void startCrop(Uri imageuri) {
//        CropImage.activity(imageuri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setAspectRatio(3, 4)
//                .start(this);
//        resultUri = imageuri;

}