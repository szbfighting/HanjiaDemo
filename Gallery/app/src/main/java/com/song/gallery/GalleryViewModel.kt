package com.song.gallery

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import java.io.StringReader

class GalleryViewModel(application: Application):AndroidViewModel(application) {

    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive:LiveData<List<PhotoItem>>
    get() = _photoListLive

    fun fetchData(){
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                _photoListLive.value=Gson().fromJson(it,Pixabay::class.java).hits.toList()
            },
            Response.ErrorListener {
                Log.d("hello",it.toString())
            }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }

    private fun getUrl():String{
        return "https://pixabay.com/api/?key=16494219-b61004d06b8957bb3f9b6e78f&q=${keyWords.random()}&per_page=50"
    }

    private val keyWords = arrayOf("cat","dog","car","beauty","phone","computer","flower","animal")
}