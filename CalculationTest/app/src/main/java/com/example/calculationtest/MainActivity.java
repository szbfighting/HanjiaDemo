package com.example.calculationtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private NavController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this,controller);
        myViewModel = ViewModelProviders.of(this,new SavedStateViewModelFactory(getApplication(),this)).get(MyViewModel.class);

    }

    @Override
    public boolean onSupportNavigateUp() {
        if (controller.getCurrentDestination().getId() == R.id.questionFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_message);
            builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    controller.navigateUp();
                    myViewModel.reset();
                }
            });

            builder.setNegativeButton("Cancel", null);
            builder.create().show();
        }else if (controller.getCurrentDestination().getId() == R.id.winFragment){
            controller.navigate(R.id.action_winFragment_to_titleFragment);
            myViewModel.reset();
        }else if(controller.getCurrentDestination().getId() == R.id.loseFragment){
            controller.navigate(R.id.action_loseFragment_to_titleFragment);
            myViewModel.reset();
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (controller.getCurrentDestination().getId() == R.id.questionFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_message);
            builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    controller.navigateUp();
                    myViewModel.reset();
                }
            });

            builder.setNegativeButton("Cancel", null);
            builder.create().show();
        }else if (controller.getCurrentDestination().getId() == R.id.winFragment){
            controller.navigate(R.id.action_winFragment_to_titleFragment);
            myViewModel.reset();
        }else if(controller.getCurrentDestination().getId() == R.id.loseFragment){
            controller.navigate(R.id.action_loseFragment_to_titleFragment);
            myViewModel.reset();
        }else{
            super.onBackPressed();
        }
    }
}
