package com.example.topacademy_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherAdapter(
    private val items: List<DataSeries>,
    private val onItemClick: (DataSeries) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.iv_weather_icon)
        val tvDay: TextView = view.findViewById(R.id.tv_day)
        val tvTemp: TextView = view.findViewById(R.id.tv_temp)
        val tvDescription: TextView = view.findViewById(R.id.tv_description)
        val cardView: CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {return items.count()}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        val date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).parse(item.date.toString().take(8))
        holder.tvDay.text = date?.let { SimpleDateFormat("EEEE", Locale.getDefault()).format(it) }

        holder.tvTemp.text = "${item.temp2m.max}°/${item.temp2m.min}°"

        val iconRes = when (item.weather) {
            "clearday" -> R.drawable.ic_sun
            "cloudy" -> R.drawable.ic_cloud
            else -> R.drawable.ic_rain
        }
        Glide.with(holder.itemView)
            .load(iconRes)
            .into(holder.ivIcon)

        val bgColor = when {
            item.temp2m.max > 30 -> R.color.hot
            item.temp2m.max < 10 -> R.color.cold
            else -> R.color.normal
        }
        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, bgColor))

        holder.itemView.setOnClickListener { onItemClick(item) }
    }
}