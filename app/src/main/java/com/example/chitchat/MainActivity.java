package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
private Toolbar tlbr ;
private TabLayout tl ;
private ViewPager vp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
tlbr = findViewById(R.id.tlbrid);
tl = findViewById(R.id.tl);
vp = findViewById(R.id.vpid);
vp.setAdapter(new vadapter(getSupportFragmentManager()));
tl.setupWithViewPager(vp);

setSupportActionBar(tlbr);
getSupportActionBar().setTitle("WhatsApp");
    }


    @Override
    protected void onStart() {
        super.onStart();
        if( FirebaseAuth.getInstance().getCurrentUser() == null )  {
          startActivity(new Intent(MainActivity.this , login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP));
           finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.signout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this , login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP));
finish();
return true ;
        }
return false ;
    }








    public class vadapter extends FragmentPagerAdapter {
        public vadapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0 :
                    return new chatsfragment();
                case 1 :
                    return new usersFragment();
            }
            return new profileFragment();
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0 :
                    return  "CHATS";
                case 1 :
                    return "USERS" ;
            }
            return "PROFILE"  ;
        }
    }




}