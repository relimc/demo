package com.sunnyweather.android.ui.place

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.weather.WeatherActivity

class PlaceFragment: Fragment() {
    val viewModel by lazy {
        ViewModelProvider(this)[PlaceViewModel::class.java]
    }
    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (viewModel.isPlaceSaved() && activity is MainActivity) {
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }

        val bgImageView = activity?.findViewById<ImageView>(R.id.bgImageView)
        val layoutManager = LinearLayoutManager(activity)
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = layoutManager
        adapter = PlaceAdapter(this, viewModel.placeList)
        recyclerView?.adapter = adapter
        val searchPlaceEdit = activity?.findViewById<EditText>(R.id.searchPlaceEdit)
        searchPlaceEdit?.addTextChangedListener {
            val content = it.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                recyclerView?.visibility = View.GONE
                bgImageView?.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.placeLiveData.observe(viewLifecycleOwner) {
            val places = it.getOrNull()
            if (places != null) {
                recyclerView?.visibility = View.VISIBLE
                bgImageView?.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_LONG).show()
                it.exceptionOrNull()?.printStackTrace()
            }
        }
    }
}