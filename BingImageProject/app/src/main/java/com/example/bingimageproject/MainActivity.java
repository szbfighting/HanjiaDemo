package com.example.bingimageproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bingimageproject.databinding.ActivityMainBinding;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private ImageViewModel mViewModel;
    private ProgressDialog mProgressDialog;
    private LifecycleRegistry mainRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ImageViewModel.class);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("加载中");
        mainRegistry = (LifecycleRegistry) getLifecycle();
        TestObserver testObserver = new TestObserver(mainRegistry);
        mainRegistry.addObserver(testObserver);
        mViewModel.getmImage().observe(this, new Observer<Data<ImageBean.ImagesBean>>() {
            @Override
            public void onChanged(Data<ImageBean.ImagesBean> imagesBeanData) {
                if (imagesBeanData.getmErrorMsg() != null) {
                    Toast.makeText(MainActivity.this, imagesBeanData.getmErrorMsg(), Toast.LENGTH_SHORT).show();
                    mProgressDialog.dismiss();
                    return;
                }
                mBinding.setImageBean(imagesBeanData.getmData());
                setTitle(imagesBeanData.getmData().getCopyright());
                mProgressDialog.dismiss();
            }
        });





        mBinding.setPresenter(new Presenter());
        mProgressDialog.show();
        mViewModel.LoadImage();

    }

    public class Presenter{
        public void onClick(View view){
            mProgressDialog.show();
            switch (view.getId()){
                case R.id.btn_load:
                    mViewModel.LoadImage();
                    break;
                case R.id.btn_previous:
                    mViewModel.previousImage();
                    break;
                case R.id.btn_next:
                    mViewModel.nextImage();
                    break;
            }
        }
    }
}
