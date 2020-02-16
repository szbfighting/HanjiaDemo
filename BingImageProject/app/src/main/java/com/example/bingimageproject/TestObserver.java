package com.example.bingimageproject;

import android.util.Log;

import androidx.lifecycle.Lifecycle;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;

public class TestObserver implements LifecycleObserver {

    private static final String TAG = "TestObserver";
    private LifecycleRegistry registry;
    public TestObserver(LifecycleRegistry registry){
        this.registry = registry;
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        Log.d(TAG, TAG+"onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        Log.d(TAG, TAG+"onStart: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        Log.d(TAG, TAG+"onResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        Log.d(TAG, TAG+"onPause: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        Log.d(TAG, TAG+"onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        Log.d(TAG, TAG+"onDestroy: ");
    }
}
