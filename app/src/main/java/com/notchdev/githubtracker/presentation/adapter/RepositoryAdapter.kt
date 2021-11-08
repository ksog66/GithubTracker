package com.notchdev.githubtracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.notchdev.githubtracker.data.local.RepositoryDetail
import com.notchdev.githubtracker.databinding.RepoListItemBinding


class RepositoryAdapter : ListAdapter<RepositoryDetail,RepositoryAdapter.RepositoryViewHolder>(RepoDiffCallBack()) {

    private var onRepositoryClickListener: RepositoryClickListener? = null

    fun setOnRepositoryClickListener(listener: RepositoryClickListener) {
        onRepositoryClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,onRepositoryClickListener)
    }

    class RepositoryViewHolder(private val binding:RepoListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item:RepositoryDetail,onRepositoryClickListener: RepositoryClickListener?) {
            binding.repoNameTv.text = item.repoName
            binding.repoDescTv.text = item.repoDesc
            binding.root.setOnClickListener {
                onRepositoryClickListener?.onClick(item)
            }
        }
        companion object {
            fun from(parent:ViewGroup):RepositoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RepoListItemBinding.inflate(layoutInflater,parent,false)
                return RepositoryViewHolder(binding)
            }
        }
    }
}

class RepoDiffCallBack: DiffUtil.ItemCallback<RepositoryDetail>() {
    override fun areItemsTheSame(oldItem: RepositoryDetail, newItem: RepositoryDetail): Boolean {
        return oldItem.repoLink == newItem.repoLink
    }

    override fun areContentsTheSame(oldItem: RepositoryDetail, newItem: RepositoryDetail): Boolean {
        return oldItem == newItem
    }
}

class RepositoryClickListener(val listener: (repoDetail: RepositoryDetail) -> Unit) {
    fun onClick(repoDetail:RepositoryDetail) = listener(repoDetail)
}