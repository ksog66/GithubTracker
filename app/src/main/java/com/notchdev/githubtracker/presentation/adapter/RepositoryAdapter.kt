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
    private var onShareClickListener: ShareClickListener? = null

    fun setOnRepositoryClickListener(listener: RepositoryClickListener) {
        onRepositoryClickListener = listener
    }

    fun setOnShareClickListener(listener: ShareClickListener) {
        onShareClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,onRepositoryClickListener,onShareClickListener)
    }

    class RepositoryViewHolder(private val binding:RepoListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item:RepositoryDetail,onRepositoryClickListener: RepositoryClickListener?,onShareClickListener: ShareClickListener?) {
            binding.repoNameTv.text = item.repoName
            binding.repoDescTv.text = item.repoDesc
            binding.root.setOnClickListener {
                onRepositoryClickListener?.onClick(item)
            }
            binding.shareRepoIv.setOnClickListener {
                onShareClickListener?.onClick(item.repoName,item.repoDesc,item.repoLink)
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

class ShareClickListener(val listener: (repoName:String,repoDesc:String,repoLink:String)-> Unit) {
    fun onClick(repoName: String,repoDesc: String,repoLink: String) = listener(repoName,repoDesc,repoLink)
}