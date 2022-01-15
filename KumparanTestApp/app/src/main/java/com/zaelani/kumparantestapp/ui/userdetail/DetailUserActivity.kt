package com.zaelani.kumparantestapp.ui.userdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaelani.kumparantestapp.databinding.ActivityDetailUserBinding
import com.zaelani.kumparantestapp.viewmodel.ViewModelFactory

class DetailUserActivity : AppCompatActivity() {

    private var _activityDetailUserBinding : ActivityDetailUserBinding ?= null
    val binding get() = _activityDetailUserBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar?.title= "Detail User"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        showProgressBar(true)
        val userId = intent.getIntExtra(EXTRA_USER_ID, 0)
        val albumAdapter = AlbumAdapter()
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UserDetailViewModel::class.java]
        viewModel.getUser(userId).observe(this, { user ->
            with(binding.user){
                tvName.text = user.name
                tvAddress.text = user.address.city
                tvEmail.text = user.email
                tvCompany.text = user.company.name
            }
        })

        viewModel.getAlbums(userId).observe(this, {albums ->
            showProgressBar(false)
            albumAdapter.setAlbums(albums)
            albumAdapter.notifyDataSetChanged()
        })

        with(binding.rvAlbums) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = albumAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDetailUserBinding = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object{
        const val EXTRA_USER_ID = "extra_user_id"
    }
}