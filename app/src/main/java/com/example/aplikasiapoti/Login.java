package com.example.aplikasiapoti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText usernameLogin,passwordLogin;
    Button loginButton;
    TextView redirectToRegister;
    DatabaseReference reference;
    String USERNAME_KEY= "usernamekey";
    String username_key= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_login);
        usernameLogin = findViewById(R.id.username_login);
        passwordLogin = findViewById(R.id.password_login);
        loginButton = findViewById(R.id.btn_login);
        redirectToRegister = findViewById(R.id.toRegister);

        redirectToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login =new Intent(Login.this,Register.class);
                startActivity(login);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //State Tombol Berubah
                loginButton.setText("Loading ...");
                loginButton.setEnabled(false);

                final String xusername = usernameLogin.getText().toString();
                final String xpassowrd = passwordLogin.getText().toString();
                if(xusername.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                    loginButton.setText("LOGIN");
                    loginButton.setEnabled(true);
                }else{
                    if(xpassowrd.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Password Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                        loginButton.setText("LOGIN");
                        loginButton.setEnabled(true);
                    }else{
                        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(xusername);
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    String passwordAkunDariDatabase = snapshot.child("password").getValue().toString();
                                    if(xpassowrd.equals(passwordAkunDariDatabase)){

                                        //Menyimpan Ke SESSION
                                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(username_key, usernameLogin.getText().toString());
                                        editor.apply();

                                        Intent intent = new Intent(Login.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Password Salah",Toast.LENGTH_SHORT).show();
                                        loginButton.setText("LOGIN");
                                        loginButton.setEnabled(true);
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(),"Username Tidak Ditemukan",Toast.LENGTH_SHORT).show();
                                    loginButton.setText("LOGIN");
                                    loginButton.setEnabled(true);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }
        });
    }

}
