package com.zaelani.kumparantestapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaelani.kumparantestapp.databinding.ActivityMainBinding
import com.zaelani.kumparantestapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding : ActivityMainBinding? = null
    val binding get() = _activityMainBinding!!


    //lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar!!.title= "Home"

        showProgressBar(true)
        val homeAdapter = HomeAdapter()
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        viewModel.getAllUsers().observe(this, {users ->
            homeAdapter.setUsers(users)
        })
        viewModel.getAllPosts().observe(this, {posts ->
            showProgressBar(false)
            homeAdapter.setPosts(posts)
            homeAdapter.notifyDataSetChanged()
        })

        with(binding.rvPosts) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    // own function
    private fun showProgressBar(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}