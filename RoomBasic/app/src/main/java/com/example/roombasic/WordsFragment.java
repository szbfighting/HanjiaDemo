package com.example.roombasic;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordsFragment extends Fragment {

    private WordViewModel viewModel;
    private RecyclerView recyclerView;
    private MyAdapter adapter1,adapter2;
    private FloatingActionButton floatingActionButton;
    private LiveData<List<Word>> filterWords;
    private static final String TAG = "WordsFragment";
    public static final String VIEW_TYPE_SHP = "view_type_shp";
    public static final String IS_USE_CARD = "is_use_card";


    public WordsFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_words, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity()).get(WordViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.recyclerView);
        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter1 = new MyAdapter(viewModel,false);
        adapter2 = new MyAdapter(viewModel,true);
        SharedPreferences shp = requireActivity().getSharedPreferences(VIEW_TYPE_SHP,Context.MODE_PRIVATE);

        recyclerView.setAdapter(shp.getBoolean(IS_USE_CARD,false)?adapter2:adapter1);
        filterWords = viewModel.getAllWord();
        filterWords.observe(requireActivity(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int temp = adapter1.getItemCount();
                adapter1.setAllWords(words);
                adapter2.setAllWords(words);
                if (temp != words.size()) {
                    adapter1.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_wordsFragment_to_addFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(),0);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String pattern = newText.trim();
                filterWords.removeObservers(requireActivity());
                filterWords = viewModel.findWordWithPattern(pattern);
                filterWords.observe(requireActivity(), new Observer<List<Word>>() {
                    @Override
                    public void onChanged(List<Word> words) {
                        int temp = adapter1.getItemCount();
                        adapter1.setAllWords(words);
                        adapter2.setAllWords(words);
                        if (temp != words.size()) {
                            adapter1.notifyDataSetChanged();
                            adapter2.notifyDataSetChanged();
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.clearData:
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity())
                        .setTitle("清空数据？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.deleteAll();
                            }
                        })
                        .setNegativeButton("取消",null);
                builder.create().show();
                break;
            case R.id.switchViewType:
                SharedPreferences shp = requireActivity().getSharedPreferences(VIEW_TYPE_SHP,Context.MODE_PRIVATE);
                boolean viewType = shp.getBoolean(IS_USE_CARD,false);
                SharedPreferences.Editor editor = shp.edit();
                if (viewType){
                    recyclerView.setAdapter(adapter1);
                    editor.putBoolean(IS_USE_CARD,false);
                    editor.apply();
                }else{
                    recyclerView.setAdapter(adapter2);
                    editor.putBoolean(IS_USE_CARD,true);
                    editor.apply();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
