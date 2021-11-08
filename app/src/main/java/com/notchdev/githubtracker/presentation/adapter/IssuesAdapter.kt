package com.notchdev.githubtracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.notchdev.githubtracker.common.loadImage
import com.notchdev.githubtracker.databinding.IssueListItemBinding
import com.notchdev.githubtracker.domain.model.IssuesDetail

class IssuesAdapter: ListAdapter<IssuesDetail,IssuesAdapter.IssuesViewHolder>(IssuesDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder {
        return IssuesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class IssuesViewHolder(private val binding: IssueListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item:IssuesDetail) {
            binding.apply {
                issueTitleTv.text = item.issueTitle
                issueCreatorTv.text = item.issueCreatorName
                issueCreatorAvatar.loadImage(item.avatarUrl)
            }
        }
        companion object {
            fun from(parent:ViewGroup): IssuesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IssueListItemBinding.inflate(layoutInflater,parent,false)
                return IssuesViewHolder(binding)
            }
        }
    }
}

class IssuesDiffCallBack: DiffUtil.ItemCallback<IssuesDetail>() {
    override fun areItemsTheSame(oldItem: IssuesDetail, newItem: IssuesDetail): Boolean {
        return oldItem.issueTitle == newItem.issueTitle
    }

    override fun areContentsTheSame(oldItem: IssuesDetail, newItem: IssuesDetail): Boolean {
        return oldItem == newItem
    }
}