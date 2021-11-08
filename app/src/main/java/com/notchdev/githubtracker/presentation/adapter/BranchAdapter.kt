package com.notchdev.githubtracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.notchdev.githubtracker.databinding.BranchListItemBinding
import com.notchdev.githubtracker.domain.model.BranchDetail

class BranchAdapter: ListAdapter<BranchDetail, BranchAdapter.BranchViewHolder>(BranchDiffCallBack()) {


    private var onBranchClickListener: BranchClickListener? = null

    fun setOnBranchClickListener(listener: BranchClickListener) {
        onBranchClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        return BranchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,onBranchClickListener)
    }

    class BranchViewHolder(private val binding:BranchListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item:BranchDetail,onBranchClickListener: BranchClickListener?) {
            binding.apply {
                branchNameTv.text = item.branchName
                root.setOnClickListener {
                    onBranchClickListener?.onClick(item.branchName)
                }
            }

        }
        companion object {
            fun from(parent:ViewGroup): BranchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BranchListItemBinding.inflate(layoutInflater,parent,false)
                return BranchViewHolder(binding)
            }
        }
    }
}

class BranchDiffCallBack: DiffUtil.ItemCallback<BranchDetail>() {
    override fun areItemsTheSame(oldItem: BranchDetail, newItem: BranchDetail): Boolean {
        return oldItem.branchName == newItem.branchName
    }

    override fun areContentsTheSame(oldItem: BranchDetail, newItem: BranchDetail): Boolean {
        return oldItem == newItem
    }
}

class BranchClickListener(val listener: (branchName:String) -> Unit ) {
    fun onClick(branchName:String) = listener(branchName)
}