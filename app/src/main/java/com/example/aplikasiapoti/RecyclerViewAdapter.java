package com.example.aplikasiapoti;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public  class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList KategoriList;
    private ArrayList NamaPasienList;
    private ArrayList fotoList;
    private ArrayList NIKList;
    private Context context;
    RecyclerView mRecyclerView;


    //Membuat Konstruktor pada Class RecyclerViewAdapter


    public RecyclerViewAdapter(ArrayList niklist,ArrayList kategoriList, ArrayList namaPasienList,ArrayList fotoList) {
        this.NIKList = niklist;
        this.KategoriList = kategoriList;
        this.NamaPasienList = namaPasienList;
        this.fotoList = fotoList;
    }


    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView  KategoriPos, NamaPasien,Nikuser;
        private Button Detail;
        private ImageView Foto;
        private ImageButton Delete;


        ViewHolder(View itemView) {
            super(itemView);

            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData
            context = itemView.getContext();

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            mRecyclerView = itemView.findViewById(R.id.rv_perkembangan);
            NamaPasien = itemView.findViewById(R.id.tv_item_nama);
            KategoriPos = itemView.findViewById(R.id.tv_item_kategori);
            Detail = itemView.findViewById(R.id.btn_detail);
            Foto = itemView.findViewById(R.id.img_item_photo);
            Nikuser = itemView.findViewById(R.id.nik_user);
            Delete = itemView.findViewById(R.id.delete);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_perkembangan, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        //Memanggil Nilai/Value Pada View-View Yang Telah Dibuat pada Posisi Tertentu
        final String NamaPasien = (String) NamaPasienList.get(position);//Mengambil data (Judul) sesuai dengan posisi yang telah ditentukan
        final String KategoriPos = (String) KategoriList.get(position);
        final String Foto = (String) fotoList.get(position);
        final String NIK = (String) NIKList.get(position);
        holder.Nikuser.setText(NIK);
        holder.NamaPasien.setText(NamaPasien);
        holder.KategoriPos.setText(KategoriPos);
        holder.Foto.setImageURI(Uri.parse(Foto));

//      Panggil Onclik Hapus untuk Hapus db dan juga recyclveiw didalam showdialog
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                showDialog();
            }

//          dialog hapus
            private void showDialog(){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title dialog
                alertDialogBuilder.setTitle("Apakah Anda Ingin Menghapus Data ini?");

                // set pesan dari dialog
                alertDialogBuilder
                        .setIcon(R.drawable.ic_delete)
                        .setCancelable(false)
                        .setPositiveButton("Hapus",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // jika tombol diklik, maka akan menutup activity ini

                                //Menghapus Data Dari Database
                                DbHealth getDatabase = new DbHealth(context);
                                SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                                //Menentukan di mana bagian kueri yang akan dipilih
                                String selection = DbHealth.MyColumns.NamaPasien + " LIKE ?";
                                //Menentukan Judul Dari Data Yang Ingin Dihapus
                                String[] selectionArgs = {holder.Nikuser.getText().toString()};
                                DeleteData.delete(DbHealth.MyColumns.NamaTabel, selection, selectionArgs);

                                //Menghapus Data pada List dari Posisi Tertentu
                                int position = NIKList.indexOf(NIK);
                                NIKList.remove(position);
                                notifyItemRemoved(position);
                                Toast.makeText(context,"Data Dihapus",Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // jika tombol ini diklik, akan menutup dialog
                                // dan tidak terjadi apa2
                                dialog.cancel();
                            }
                        });

                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();
            }


        });

//        onklik see detail
        holder.Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent dataForm = new Intent(view.getContext(), DetailPerkembangan.class);
                dataForm.putExtra("SendKode", holder.Nikuser.getText().toString());
                context.startActivity(dataForm);
                ((Activity)context).finish();
                }

            });

    }

    @Override
    public int getItemCount() {
        return NIKList.size();
    }

}
