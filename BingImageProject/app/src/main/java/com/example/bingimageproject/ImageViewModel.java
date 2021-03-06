package com.example.bingimageproject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ImageViewModel extends ViewModel {

    private MutableLiveData<Data<ImageBean.ImagesBean>> mImage;
    private ImageRepertory mRepertory;
    private int idx;

    public ImageViewModel(){
        mImage = new MutableLiveData<>();
        mRepertory = new ImageRepertory();
        idx = 0;

    }

    public LiveData<Data<ImageBean.ImagesBean>> getmImage() {
        return mImage;

    }

    public void LoadImage(){
        mRepertory.getImage("js",idx,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageBean o) {
                        mImage.setValue(new Data<>(o.getImages().get(0), null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(null,e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void nextImage(){
        mRepertory.getImage("js",++idx,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageBean o) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(o.getImages().get(0),null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(null,e.getMessage()));
                        idx--;
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void previousImage(){
        if (idx<=0){
            mImage.setValue(new Data<ImageBean.ImagesBean>(null,"已经是第一个了"));
            return;
        }
        mRepertory.getImage("js",--idx,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageBean o) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(o.getImages().get(0),null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mImage.setValue(new Data<ImageBean.ImagesBean>(null,e.getMessage()));
                        idx++;
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
