package com.example.restaurantreviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.restaurantreviews.adapter.RestaurantFragmentAdapter;
import com.example.restaurantreviews.databinding.ActivityMainBinding;
import com.example.restaurantreviews.fragment.DongShengFragment;
import com.example.restaurantreviews.fragment.MeiGuangFragment;
import com.example.restaurantreviews.fragment.XuRiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        List<Fragment> list = new ArrayList<>();
        list.add(new XuRiFragment());
        list.add(new DongShengFragment());
        list.add(new MeiGuangFragment());
        RestaurantFragmentAdapter adapter = new RestaurantFragmentAdapter(getSupportFragmentManager(),list);
        activityMainBinding.setAdapter(adapter);
        activityMainBinding.setMainActivity(this);
        setSupportActionBar(activityMainBinding.mainToolbar);

        activityMainBinding.idMainBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                switch (menuItem.getItemId()){
                    case R.id.id_xuri:
                        activityMainBinding.idMainVp.setCurrentItem(0);
                        break;
                    case R.id.id_dongsheng:
                        activityMainBinding.idMainVp.setCurrentItem(1);
                        break;
                    case R.id.id_meiguang:
                        activityMainBinding.idMainVp.setCurrentItem(2);
                        break;
                }

                return true;
            }
        });

        activityMainBinding.idMainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        activityMainBinding.idMainBnv.setSelectedItemId(R.id.id_xuri);
                        break;
                    case 1:
                        activityMainBinding.idMainBnv.setSelectedItemId(R.id.id_dongsheng);
                        break;
                    case 2:
                        activityMainBinding.idMainBnv.setSelectedItemId(R.id.id_meiguang);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }




    @BindingAdapter("adapter")
    public static void setAdapter(ViewPager viewPager,RestaurantFragmentAdapter adapter){

        viewPager.setAdapter(adapter);

    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView,String url){
        Glide.with(imageView).load(Uri.parse(url)).into(imageView);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
