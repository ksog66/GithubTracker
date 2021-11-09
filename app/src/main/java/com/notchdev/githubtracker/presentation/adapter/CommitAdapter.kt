package com.notchdev.githubtracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.notchdev.githubtracker.common.loadImage
import com.notchdev.githubtracker.common.timeStamp
import com.notchdev.githubtracker.databinding.CommitsListItemBinding
import com.notchdev.githubtracker.domain.model.CommitDetail

class CommitAdapter: ListAdapter<CommitDetail,CommitAdapter.CommitViewHolder>(CommitDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        return CommitViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class CommitViewHolder(private val binding:CommitsListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item:CommitDetail) {
            binding.apply {
                commitMessageTv.text = item.commitMessage
                branchIdTv.text = item.commitId
                dateTv.timeStamp = item.commitDate
                usernameTv.text = item.committerName
                userAvatarIv.loadImage(item.committerAvatar,circleCrop = true)
            }
        }
        companion object {
            fun from(parent:ViewGroup): CommitViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommitsListItemBinding.inflate(layoutInflater)
                return CommitViewHolder(binding)
            }
        }
    }
}

class CommitDiffCallBack: DiffUtil.ItemCallback<CommitDetail>() {
    override fun areItemsTheSame(oldItem: CommitDetail, newItem: CommitDetail): Boolean {
        return oldItem.commitDate == newItem.commitDate
    }

    override fun areContentsTheSame(oldItem: CommitDetail, newItem: CommitDetail): Boolean {
        return oldItem == newItem
    }
}