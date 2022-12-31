package com.example.aplikasiapoti;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AkunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AkunFragment extends Fragment {
    Button btn_keluar;
    String mykey;
    DatabaseReference databaseReference;
    TextView namalengkap,username,email;
    String usernamee;
    ProgressDialog progressDialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AkunFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AkunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AkunFragment newInstance(String param1, String param2) {
        AkunFragment fragment = new AkunFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);
//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setTitle("Tunggu Sebentar...");
//        progressDialog.setCanceledOnTouchOutside(false);
//
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Storedata", Context.MODE_PRIVATE);

        if(sharedPreferences.contains("Users")){
            mykey =sharedPreferences.getString("Users","Data tidak ditemukan");
        }
        final TextView namaLengkapText = (TextView)view.findViewById(R.id.namalengkapakun);
        final TextView usernameText = (TextView)view.findViewById(R.id.usernameakun);
        final TextView emailtxt = (TextView)view.findViewById(R.id.emailakun);
        final TextView passwordtxt = (TextView)view.findViewById(R.id.passwordakun);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child("username").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                DataSnapshot dataSnapshot = task.getResult();


                String namalengkap = String.valueOf(dataSnapshot.child("namalengkap").getValue());
                String username = String.valueOf(dataSnapshot.child("username").getValue());
                String email = String.valueOf(dataSnapshot.child("email").getValue());
                String password = String.valueOf(dataSnapshot.child("password").getValue());
                DataAkun dataAkun =dataSnapshot.getValue(DataAkun.class);

//                String namalengkapa = dataAkun.getNamaLengkap();
//                String usernamea = dataAkun.getUsername();
//                String emaila = dataAkun.getEmail();
//                String passworda = dataAkun.getPassword();
                namaLengkapText.setText(namalengkap);
                usernameText.setText(username);
                emailtxt.setText(email);
                passwordtxt.setText(password);
            }
        });
        btn_keluar = view.findViewById(R.id.btn_logout);
        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(getActivity(),Login.class);
                startActivity(logout);

            }
        });
        return view;
    }

    private class ActivityReadDataBinding {
    }
}