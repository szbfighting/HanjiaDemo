package com.example.roombasic;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    private Button btnSubmit;
    private EditText etEnglishWord,etChinese;
    private WordViewModel viewModel;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnSubmit = requireActivity().findViewById(R.id.buttonSubmit);
        etEnglishWord = requireActivity().findViewById(R.id.editTextEnglish);
        etChinese = requireActivity().findViewById(R.id.editTextChinese);
        viewModel = ViewModelProviders.of(requireActivity()).get(WordViewModel.class);
        btnSubmit.setEnabled(false);
        etEnglishWord.requestFocus();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etEnglishWord,0);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String english = etEnglishWord.getText().toString().trim();
                String chinese = etChinese.getText().toString().trim();
                btnSubmit.setEnabled(!english.isEmpty()&&!chinese.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        etEnglishWord.addTextChangedListener(watcher);
        etChinese.addTextChangedListener(watcher);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String english = etEnglishWord.getText().toString().trim();
                String chinese = etChinese.getText().toString().trim();
                Word word = new Word(english,chinese);
                viewModel.insert(word);
                NavController controller = Navigation.findNavController(v);
                controller.navigateUp();
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });
    }


}
