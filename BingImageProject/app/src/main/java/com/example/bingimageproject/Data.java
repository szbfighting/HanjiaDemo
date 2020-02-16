package com.example.bingimageproject;

public class Data<T> {
    private T mData;
    private String mErrorMsg;

    public Data(T mData, String mErrorMsg) {
        this.mData = mData;
        this.mErrorMsg = mErrorMsg;
    }

    public T getmData() {
        return mData;
    }

    public void setmData(T mData) {
        this.mData = mData;
    }

    public String getmErrorMsg() {
        return mErrorMsg;
    }

    public void setmErrorMsg(String mErrorMsg) {
        this.mErrorMsg = mErrorMsg;
    }
}
