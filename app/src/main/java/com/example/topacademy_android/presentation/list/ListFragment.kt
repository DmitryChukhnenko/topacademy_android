package com.example.topacademy_android.presentation.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.topacademy_android.databinding.FragmentListBinding
import kotlinx.coroutines.launch
import com.example.topacademy_android.R

class ListFragment : Fragment(R.layout.fragment_list) {
    private val viewModel: ListViewModel by viewModel()
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        setupRecyclerView()
        setupListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = ItemAdapter(viewModel.items.value.toMutableList()) { position ->
            viewModel.deleteItem(position)
        }
        binding.rvItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvItems.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            val newItem = binding.etNewItem.text.toString().trim()
            if (newItem.isNotEmpty()) {
                viewModel.addItem(newItem)
                binding.etNewItem.text?.clear()
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect { items ->
                    adapter.updateData(items.toMutableList())
                }
            }
        }
    }
}