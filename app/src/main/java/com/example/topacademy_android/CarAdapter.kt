package com.example.topacademy_android

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import android.view.LayoutInflater

class CarAdapter(private val cars: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCar: ImageView = itemView.findViewById(R.id.iv_car)
        val tvBrandModel: TextView = itemView.findViewById(R.id.tv_brand_model)
        val tvYear: TextView = itemView.findViewById(R.id.tv_year)
        val tvCost: TextView = itemView.findViewById(R.id.tv_cost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        holder.ivCar.setImageResource(car.imageResId)
        holder.tvBrandModel.text = "${car.brand} ${car.model}"
        holder.tvYear.text = car.year.toString()
        holder.tvCost.text = "${car.cost} ${holder.itemView.context.getString(R.string.currency)}"
    }

    override fun getItemCount(): Int = cars.size
}