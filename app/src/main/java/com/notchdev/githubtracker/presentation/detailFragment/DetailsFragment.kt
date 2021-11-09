package com.notchdev.githubtracker.presentation.detailFragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.notchdev.githubtracker.R
import com.notchdev.githubtracker.data.local.RepositoryDetail
import com.notchdev.githubtracker.databinding.FragmentDetailsBinding
import com.notchdev.githubtracker.presentation.adapter.BranchAdapter
import com.notchdev.githubtracker.presentation.adapter.BranchClickListener
import com.notchdev.githubtracker.presentation.adapter.IssuesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    private var repoDetail: RepositoryDetail? = null

    val detailViewModel by viewModels<DetailsViewModel>()

    @Inject
    lateinit var branchAdapter: BranchAdapter

    @Inject
    lateinit var issuesAdapter: IssuesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        arguments?.let {
            repoDetail = it.getParcelable(resources.getString(R.string.arg_repo_detail))
        }
        getFeedData()
        setupBranchRv()
        observeUIStates()

        branchAdapter.setOnBranchClickListener(BranchClickListener { branchName->
            findNavController().navigate(
                R.id.action_detailsFragment_to_commitFragment,
                bundleOf(
                    resources.getString(R.string.arg_branch_name) to branchName,
                    resources.getString(R.string.arg_repo_name) to repoDetail!!.repoName,
                    resources.getString(R.string.arg_owner_name) to repoDetail!!.ownerName
                )
            )
        })
        return binding.root
    }

    private fun getFeedData() {
        detailViewModel.getBranchList(
            ownerName = repoDetail!!.ownerName,
            repoName = repoDetail!!.repoName
        )
        detailViewModel.getIssuesList(
            ownerName = repoDetail!!.ownerName,
            repoName = repoDetail!!.repoName
        )
    }

    @SuppressLint("StringFormatMatches")
    private fun observeUIStates() {
        detailViewModel.isLoading.observe({lifecycle}) {
            if(it) {
                binding.loadinProgressBar.visibility = View.VISIBLE
            } else {
                binding.loadinProgressBar.visibility = View.INVISIBLE
            }
        }
        detailViewModel.branchData.observe({lifecycle}) {
            it?.let {
                branchAdapter.submitList(it)
            }
        }
        detailViewModel.issuesData.observe({lifecycle}) {
            it?.let {
                issuesAdapter.submitList(it)
                val issuesLabelText = resources.getString(R.string.issues_tab,it.size)
                binding.repoTabLayout.getTabAt(1)?.text = issuesLabelText
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            repoNameTv.text = repoDetail?.repoName
            repoDescTv.text = repoDetail?.repoDesc

            repoTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            detailViewModel.getBranchList(
                                repoDetail!!.ownerName,
                                repoDetail!!.repoName
                            )
                            setupBranchRv()
                        }
                        1 -> {
                            setupIssuesRv()
                            detailViewModel.getIssuesList(
                                repoDetail!!.ownerName,
                                repoDetail!!.repoName
                            )
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    private fun setupBranchRv() {
        binding.detailsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = branchAdapter
        }
        binding.repoTabLayout.getTabAt(0)?.select()
    }

    private fun setupIssuesRv() {
        binding.detailsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = issuesAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.browser_view -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(repoDetail!!.repoLink)
                startActivity(intent)
            }
            R.id.delete_repo -> {
                detailViewModel.deleteRepo(repoDetail!!.id)
                findNavController().popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}