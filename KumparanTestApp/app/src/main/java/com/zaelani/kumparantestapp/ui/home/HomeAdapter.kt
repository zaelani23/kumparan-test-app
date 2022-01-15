package com.zaelani.kumparantestapp.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaelani.kumparantestapp.R
import com.zaelani.kumparantestapp.data.source.remote.response.Post
import com.zaelani.kumparantestapp.data.source.remote.response.User
import com.zaelani.kumparantestapp.databinding.ItemsPostBinding
import com.zaelani.kumparantestapp.ui.detailpost.DetailPostActivity

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var listPost = ArrayList<Post>()
    private var listUser = ArrayList<User>()

    fun setPosts(posts: List<Post>) {
        this.listPost.clear()
        this.listPost.addAll(posts)
    }

    fun setUsers(users: List<User>) {
        this.listUser.clear()
        this.listUser.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemPostBinding = ItemsPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemPostBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val post = listPost[position]
        holder.bind(post, listUser)
    }

    override fun getItemCount(): Int = listPost.size

    class HomeViewHolder(private val binding: ItemsPostBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post, listUser: ArrayList<User>){
            with(binding){
                tvTitlePost.text = post.title
                tvBodyPost.text = post.body
                listUser.forEach {
                    if (it.id == post.userId) {
                        tvUser.text = itemView.resources.getString(R.string.user, it.name, it.company.name)
                    }
                }
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailPostActivity::class.java)
                    intent.putExtra(DetailPostActivity.EXTRA_POST_ID, post.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}