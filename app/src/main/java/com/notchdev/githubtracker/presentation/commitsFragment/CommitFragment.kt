package com.notchdev.githubtracker.presentation.commitsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.notchdev.githubtracker.R
import com.notchdev.githubtracker.databinding.FragmentCommitBinding
import com.notchdev.githubtracker.presentation.adapter.CommitAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommitFragment : Fragment() {

    @Inject
    lateinit var commitAdapter: CommitAdapter

    private var _binding: FragmentCommitBinding? = null
    private val binding: FragmentCommitBinding
        get() = _binding!!

    private var branchName: String? = null
    private var repoName: String? = null
    private var ownerName: String? = null

    val commitViewModel by viewModels<CommitViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommitBinding.inflate(layoutInflater, container, false)

        loadArguments()
        setupRecyclerView()
        initFetchData()
        observeUIState()
        return binding.root
    }

    private fun initFetchData() {
        commitViewModel.getCommitDetailList(
            ownerName = ownerName!!,
            repoName = repoName!!,
            branchName = branchName!!
        )
    }

    private fun observeUIState() {
        commitViewModel.commitData.observe({lifecycle}) {
            it?.let {
                commitAdapter.submitList(it)
            }
        }
    }
    private fun loadArguments() {
        arguments?.let {
            branchName = it.getString(resources.getString(R.string.arg_branch_name))
            ownerName = it.getString(resources.getString(R.string.arg_owner_name))
            repoName = it.getString(resources.getString(R.string.arg_repo_name))
        }
    }

    private fun setupRecyclerView() {
        binding.commitRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commitAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}