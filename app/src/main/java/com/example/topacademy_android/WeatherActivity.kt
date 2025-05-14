package com.example.topacademy_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topacademy_android.databinding.ActivityWeatherBinding

data class Weather(
    val day: String,
    val temp: String,
    val description: String,
    val icon: Int
)

class WeatherAdapter(private val items: List<Weather>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.iv_weather_icon)
        val tvDay: TextView = view.findViewById(R.id.tv_day)
        val tvTemp: TextView = view.findViewById(R.id.tv_temp)
        val tvDescription: TextView = view.findViewById(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.ivIcon.setImageResource(item.icon)
        holder.tvDay.text = item.day
        holder.tvTemp.text = item.temp
        holder.tvDescription.text = item.description
    }

    override fun getItemCount(): Int = items.size
}


class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private val weatherList = listOf(
        Weather("Сегодня", "+25°C", "Солнечно", R.drawable.ic_sun),
        Weather("Завтра", "+22°C", "Облачно", R.drawable.ic_cloud),
        Weather("Послезавтра", "+18°C", "Дождь", R.drawable.ic_rain)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvWeather.layoutManager = LinearLayoutManager(this)
        binding.rvWeather.adapter = WeatherAdapter(weatherList)
    }
}