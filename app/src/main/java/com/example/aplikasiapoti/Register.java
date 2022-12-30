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

public class Register extends AppCompatActivity {
    EditText signupUsername,signupNamalengkap,signupEmail,signupPassword,signupConfPassword;
    TextView loginRedicetText;
    Button signupButton;
    String USERNAME_KEY = "usernamekey";
    String username_key= "";
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_register_lama);

        signupUsername= findViewById(R.id.username);
        signupNamalengkap = findViewById(R.id.namalengkap);
        signupEmail= findViewById(R.id.email);
        signupPassword = findViewById(R.id.password);
        signupConfPassword = findViewById(R.id.konfirmasi_password);
        loginRedicetText =findViewById(R.id.toLogin);
        signupButton = findViewById(R.id.registrasi_akun);
        loginRedicetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register =new Intent(Register.this, Login.class);
                startActivity(register);
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameTxt = signupUsername.getText().toString();
                final String namaLengkapTxt = signupNamalengkap.getText().toString();
                final String emailTxt = signupEmail.getText().toString();
                final String passwordTxt = signupPassword.getText().toString();
                final String confPasswordTxt = signupConfPassword.getText().toString();

                if (usernameTxt.isEmpty() || namaLengkapTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty()) {
                    Toast.makeText(Register.this, "Tolong isikan kolom Registrasi", Toast.LENGTH_SHORT).show();
                } else if (!passwordTxt.equals(confPasswordTxt)) {
                    Toast.makeText(Register.this, "Password tidak sama", Toast.LENGTH_SHORT).show();
                } else {
                    // State Tombol Berubah
                    signupButton.setText("Loading ...");
                    signupButton.setEnabled(false);

                    // Menyimpan session ke dalam database local
                    SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(username_key, signupUsername.getText().toString());
                    editor.apply();
                    // Menyimpan ke dalam RTB
                    reference = FirebaseDatabase.getInstance().getReference().child("Users").child(signupUsername.getText().toString());
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            snapshot.getRef().child("username").setValue(signupUsername.getText().toString());
                            snapshot.getRef().child("namalengkap").setValue(signupNamalengkap.getText().toString());
                            snapshot.getRef().child("email").setValue(signupEmail.getText().toString());
                            snapshot.getRef().child("password").setValue(signupPassword.getText().toString());
                            snapshot.getRef().child("konfirmasi_password").setValue(signupConfPassword.getText().toString());


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Register.this, "Test gagalSg", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //Menuju Login
                    Intent login = new Intent(Register.this, Login.class);
                    startActivity(login);
                    finish();
                }
            }
        });


    }


}
