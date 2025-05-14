package com.example.topacademy_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private val items = mutableListOf<String>()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ItemAdapter(items) { position ->
            items.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val newItem = binding.etNewItem.text.toString().trim()
            if (newItem.isNotEmpty()) {
                items.add(newItem)
                adapter.notifyItemInserted(items.size - 1)
                binding.etNewItem.text?.clear()
            }
        }
    }
}