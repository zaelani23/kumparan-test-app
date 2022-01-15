package com.zaelani.kumparantestapp.ui.detailpost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaelani.kumparantestapp.data.source.remote.response.Comment
import com.zaelani.kumparantestapp.databinding.ItemsCommentBinding

class CommentsPostAdapter: RecyclerView.Adapter<CommentsPostAdapter.CommentsViewHolder>() {
    private var listComments = ArrayList<Comment>()

    fun setComments(comments: List<Comment>) {
        this.listComments.clear()
        this.listComments.addAll(comments)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentsPostAdapter.CommentsViewHolder {
        val itemsCommentBinding = ItemsCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentsViewHolder(itemsCommentBinding)
    }

    override fun onBindViewHolder(holder: CommentsPostAdapter.CommentsViewHolder, position: Int) {
        val comment = listComments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = listComments.size

    inner class CommentsViewHolder(private val binding : ItemsCommentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(comment: Comment) {
            with(binding){
                tvUserName.text = comment.name
                tvComments.text = comment.body
            }
        }

    }
}