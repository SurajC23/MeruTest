package com.example.merutest.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.merutest.MainActivity
import com.example.merutest.R
import com.example.merutest.adapter.RecyclerViewAdapter
import com.example.merutest.interfaces.Like
import com.example.merutest.model.ReceipeData
import com.example.merutest.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_chicken.*
import kotlinx.android.synthetic.main.fragment_potato.*

class PotatoFragment : Fragment()
{
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        createData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_potato, container, false)
        return view
    }

    private fun initRecyclerView()
    {
        rvPotato.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = RecyclerViewAdapter(activity as MainActivity)
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(context,
                StaggeredGridLayoutManager.VERTICAL
            )
            addItemDecoration(decoration)
        }
    }

    private fun createData() {
        val viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        viewModel.makeApiCall("potato")
        activity?.let {
            viewModel.getRecipeListDataObserver().observe(it, Observer {
                if (it != null) {
                    recyclerViewAdapter.setListData(it.recipes)
                    recyclerViewAdapter.notifyDataSetChanged()
                    Log.e("TAG", "createData: ${it.recipes.size}" )
                }
            })
        }
    }
}