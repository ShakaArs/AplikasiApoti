package com.example.aplikasiapoti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_beranda);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_kesehatan));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_jadwal));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_progress));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_akun));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new HealthFragment();
                        break;
                    case 3:
                        fragment = new DateFragment();
                        break;
                    case 4:
                        fragment = new ProgressFragment();
                        break;
                    case 5:
                        fragment = new AkunFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        bottomNavigation.show(1,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //navigation user
//                Toast.makeText(getApplicationContext(), " Menu sudah diklik "+ item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        // jika user mengulang untuk klik item menu
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "Kamu sudah berada di menu ini " , Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation.setCount(3,"10");

    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment,null)
                .commit();
    }

}