package com.notchdev.githubtracker.presentation.homeFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.notchdev.githubtracker.R
import com.notchdev.githubtracker.databinding.FragmentHomeBinding
import com.notchdev.githubtracker.presentation.adapter.RepositoryAdapter
import com.notchdev.githubtracker.presentation.adapter.RepositoryClickListener
import com.notchdev.githubtracker.presentation.adapter.ShareClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    val homeViewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var repoAdapter:RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()
        homeViewModel.getRepoListFromDB()
        observerUIState()
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        repoAdapter.setOnRepositoryClickListener(RepositoryClickListener{ repoDetail ->
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                bundleOf(
                    resources.getString(R.string.arg_repo_detail) to repoDetail
                )
            )
        })
        repoAdapter.setOnShareClickListener(ShareClickListener {repoName,repoDesc,repoLink->
            val sendIntent : Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,repoName)
                putExtra(Intent.EXTRA_TEXT,repoDesc)
                putExtra(Intent.EXTRA_TEXT,repoLink)
                type="text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent,null)
            startActivity(shareIntent)
        })
    }

    private fun setupRecyclerView() {
        binding.repoFeedRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }
    }
    private fun observerUIState() {
        homeViewModel.repoData.observe({lifecycle}) {
            if (it.isNotEmpty()) {
                Log.d("HomeFragment", "observerUIState: List is not null $it ")
                binding.apply {
                    repoFeedRv.visibility = View.VISIBLE
                    gotoAddRepoBtn.visibility = View.INVISIBLE
                    labelTv.visibility = View.INVISIBLE
                }
                repoAdapter.submitList(it)
            } else {
                Log.d("HomeFragment", "observerUIState: List is null $it")
                binding.apply {
                    repoFeedRv.visibility = View.INVISIBLE
                    gotoAddRepoBtn.visibility = View.VISIBLE
                    labelTv.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gotoAddRepoBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_addRepoFragment
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_now) {
            findNavController().navigate(
                R.id.action_homeFragment_to_addRepoFragment
            )
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}