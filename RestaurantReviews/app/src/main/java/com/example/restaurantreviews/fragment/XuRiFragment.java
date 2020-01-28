package com.example.restaurantreviews.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantreviews.R;
import com.example.restaurantreviews.adapter.DishesAdapter;
import com.example.restaurantreviews.databinding.FragmentXuriBinding;
import com.example.restaurantreviews.model.Dishes;

import java.util.ArrayList;
import java.util.List;

public class XuRiFragment extends Fragment {

    private FragmentXuriBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_xuri,container,false);
        List<Dishes> list = new ArrayList<>();
        Dishes dishes = new Dishes("http://b-ssl.duitang.com/uploads/item/201709/02/20170902135603_2mYKC.thumb.700_0.jpeg","Dog");
        list.add(dishes);

        DishesAdapter adapter = new DishesAdapter(getContext());
        adapter.addAll(list);
        adapter.setListener(new ItemClickListener());
        binding.setAdapter(adapter);
        binding.setManager(new LinearLayoutManager(getContext()));
        Log.d("TEST", "onCreateView: ");
        return binding.getRoot();
    }

    @BindingAdapter(value = {"adapter","layoutManager"})
    public static void setAdapter(RecyclerView recyclerView, DishesAdapter adapter, RecyclerView.LayoutManager manager){
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public class ItemClickListener implements DishesAdapter.DishesClickListener{
        private FragmentManager manager = getFragmentManager();

        @Override
        public void onClick(Dishes dishes) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.fragment_into,R.anim.fragment_out);
            transaction.add(R.id.id_main_fl,new DishesItemFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
