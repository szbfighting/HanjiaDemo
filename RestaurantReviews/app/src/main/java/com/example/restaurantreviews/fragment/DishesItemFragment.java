package com.example.restaurantreviews.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.restaurantreviews.R;

public class DishesItemFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dishes_item,container,false);
        ImageView imageView = view.findViewById(R.id.id_dishes_show);
        Glide.with(getContext()).load("http://b-ssl.duitang.com/uploads/item/201709/02/20170902135603_2mYKC.thumb.700_0.jpeg").into(imageView);
        Toolbar toolbar = view.findViewById(R.id.id_dishes_fragment_toolbar);
        toolbar.setTitle("Dog");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(v -> {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.fragment_into,R.anim.fragment_out);
            transaction.remove(this);
            transaction.commit();
            getActivity().onBackPressed();

        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DISHES", "onDestroy: ");
    }


    
}
