package com.example.roombasic;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Word> allWords = new ArrayList<>();
    private WordViewModel wordViewModel;
    private boolean useCardView;

    public MyAdapter(WordViewModel wordViewModel,boolean useCardView) {
        this.wordViewModel = wordViewModel;
        this.useCardView = useCardView;
    }

    public void setAllWords(List<Word> allWords){
        this.allWords = allWords;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (useCardView){
            view = inflater.inflate(R.layout.cell_card_2,parent,false);
        }else{
            view  = inflater.inflate(R.layout.cell_normal_2,parent,false);
        }

        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q="+viewHolder.englishText.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });

        viewHolder.chineseInvisable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Word word = (Word) viewHolder.itemView.getTag(R.id.word_for_view_holder);
                if (isChecked){
                    viewHolder.chineseText.setVisibility(GONE);
                    word.setChineseInvisable(true);
                    wordViewModel.update(word);
                }else{
                    viewHolder.chineseText.setVisibility(View.VISIBLE);
                    word.setChineseInvisable(false);
                    wordViewModel.update(word);
                }
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Word word = allWords.get(position);
        holder.itemView.setTag(R.id.word_for_view_holder,word);
        holder.number.setText(String.valueOf(position+1));
        holder.englishText.setText(word.getWord());
        holder.chineseText.setText(word.getChinese());
        if (word.isChineseInvisable()){
            holder.chineseText.setVisibility(GONE);
            holder.chineseInvisable.setChecked(true);
        }else{
            holder.chineseText.setVisibility(View.VISIBLE);
            holder.chineseInvisable.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView englishText,chineseText,number;
        Switch chineseInvisable;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            englishText = itemView.findViewById(R.id.english);
            chineseText = itemView.findViewById(R.id.chinese);
            number = itemView.findViewById(R.id.number);
            chineseInvisable = itemView.findViewById(R.id.chinese_invisable);
        }
    }

}
