package com.example.firstassignment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecipesFragment : Fragment(R.layout.recycle_list_screen) {

    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var adapter: FoodAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.recyclerView)
        val logoutButton: Button = view.findViewById(R.id.logoutButton)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FoodAdapter(emptyList(), requireContext())
        recyclerView.adapter = adapter

        logoutButton.setOnClickListener {
            (requireActivity() as? SampleViewsActivity)?.switchToLoginFragment()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest { uiState ->
                progressBar.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE
                recyclerView.visibility = if (uiState.isLoading) View.GONE else View.VISIBLE
                adapter.updateData(uiState.recipes)
            }
        }

        viewModel.loadRecipes()

        val searchView: androidx.appcompat.widget.SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filter(newText ?: "")
                return true
            }
        })
    }
}
