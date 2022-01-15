package com.zaelani.kumparantestapp.ui.detailpost

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaelani.kumparantestapp.R
import com.zaelani.kumparantestapp.data.source.remote.response.User
import com.zaelani.kumparantestapp.databinding.ActivityDetailPostBinding
import com.zaelani.kumparantestapp.ui.userdetail.DetailUserActivity
import com.zaelani.kumparantestapp.viewmodel.ViewModelFactory

class DetailPostActivity : AppCompatActivity() {

    private var _activityDetailPostBinding : ActivityDetailPostBinding? = null
    val binding get() = _activityDetailPostBinding!!
    private var listUser = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailPostBinding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar?.title= "Detail Post"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        showProgressBar(true)
        val postId = intent.getIntExtra(EXTRA_POST_ID,0)
        val commentsAdapter = CommentsPostAdapter()
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailPostViewModel::class.java]
        viewModel.getAllUsers().observe(this, {
            listUser.clear()
            listUser.addAll(it)
        })
        viewModel.getPost(postId).observe(this, {post ->
            with(binding.post){
                tvTitlePost.text = post.title
                tvBodyPost.text = post.body
                listUser.forEach {user->
                    if (user.id == post.userId) {
                        tvUser.text = resources.getString(R.string.user, user.name, user.company.name)
                    }
                }
                tvUser.setOnClickListener {
                    val intent = Intent(this@DetailPostActivity, DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.EXTRA_USER_ID, post.userId)
                    startActivity(intent)
                }
            }
        })
        viewModel.getPostComments(postId).observe(this, {comments ->
            showProgressBar(false)
            commentsAdapter.setComments(comments)
            commentsAdapter.notifyDataSetChanged()
        })

        with(binding.rvComments){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = commentsAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object{
        const val EXTRA_POST_ID = "extra_post_id"
    }
}