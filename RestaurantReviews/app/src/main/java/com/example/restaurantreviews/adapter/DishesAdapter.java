package com.example.restaurantreviews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantreviews.BR;
import com.example.restaurantreviews.R;
import com.example.restaurantreviews.model.Dishes;

import java.util.ArrayList;
import java.util.List;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.BindingViewHolder>{

    private DishesClickListener listener;
    private LayoutInflater inflater;
    private List<Dishes> list;

    public DishesAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
    }

    static class BindingViewHolder<T extends ViewDataBinding>
            extends RecyclerView.ViewHolder{

        private T binding;
        public BindingViewHolder(T binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public T getBinding() {
            return binding;
        }
    }
    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.dishes_list_item,parent,false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
        Dishes dishes = list.get(position);
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.dishes,dishes);
        binding.executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(dishes);
            }
        });

    }

    public void addAll(List<Dishes> dishes){
        list.addAll(dishes);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListener(DishesClickListener listener){
        this.listener = listener;
    }

    public interface DishesClickListener{
        void onClick(Dishes dishes);
    }


}
