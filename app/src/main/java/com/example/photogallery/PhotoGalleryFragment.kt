package com.example.photogallery

import androidx.fragment.app.Fragment
import retrofit2.Call
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photogallery.api.FlickrApi
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment: Fragment(){

    private lateinit var photoRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flickrLiveData: LiveData<String> = FlickrFetch().fetchContents()
        flickrLiveData.observe(
            this,
            Observer{responseString ->
                Log.d(TAG, "Response recived: $responseString")
            }
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_photo_galery, container, false)

        // Taking the context of photo_recycler_view
        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)

        // Configuration for Grid layout
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            photoRecyclerView.layoutManager = GridLayoutManager(context, 3)
        }

        // return view
        return view
    }


    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}
