package com.example.slidedeletemenu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slidedeletemenu.R;
import com.example.slidedeletemenu.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private int[] icons={
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_5,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            R.drawable.icon_9,
            R.drawable.icon_10,
            R.drawable.icon_11
    };

    private int[] names={
            R.string.name1,R.string.name2,R.string.name3,R.string.name4,R.string.name5,
            R.string.name6,R.string.name7,R.string.name8,R.string.name9,R.string.name10,
            R.string.name11
    };

    private int[] infos = {
            R.string.info1,R.string.info2,R.string.info3,R.string.info4,R.string.info5,
            R.string.info6,R.string.info7,R.string.info8,R.string.info9,R.string.info10,
            R.string.info11
    };

    private Context context;

    private List<Integer> listIcon = new ArrayList<>();
    private List<Integer> listName = new ArrayList<>();
    private List<Integer> listInfo = new ArrayList<>();


    public Adapter(Context context){
        this.context = context;
        //设置菜单行数与信息
        for (int i = 0;i<11;i++){
            listIcon.add(icons[i]);
            listName.add(names[i]);
            listInfo.add(infos[i]);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView delete;
        public TextView name,info;
        public ImageView img;
        public ViewGroup layout_content; //图标与信息布局

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.tv_delete);
            name = itemView.findViewById(R.id.name);
            info = itemView.findViewById(R.id.info);
            img = itemView.findViewById(R.id.img);
            layout_content = itemView.findViewById(R.id.layout_content);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);

        return new MyViewHolder(view);
    }



    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.img.setImageResource(listIcon.get(position));
        holder.name.setText(listName.get(position));
        holder.info.setText(listInfo.get(position));
        //设置内容布局的宽度为屏幕的宽度
        holder.layout_content.getLayoutParams().width= Utils.getScreenWidth(context);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                listIcon.remove(n);
                listInfo.remove(n);
                listName.remove(n);
                notifyItemRemoved(n);
                Log.d("POSITION", "onClick: "+n+" "+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listIcon.size();
    }
}
