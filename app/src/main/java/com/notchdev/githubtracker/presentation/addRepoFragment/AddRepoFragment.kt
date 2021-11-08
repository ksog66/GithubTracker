package com.notchdev.githubtracker.presentation.addRepoFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.notchdev.githubtracker.R
import com.notchdev.githubtracker.databinding.FragmentAddRepoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRepoFragment : Fragment() {

    private var _binding:FragmentAddRepoBinding? = null
    private val binding:FragmentAddRepoBinding
        get() = _binding!!

    val addRepoViewModel by viewModels<AddRepoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddRepoBinding.inflate(layoutInflater, container, false)

        observeUIStates()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            addRepoBtn.setOnClickListener {
                val ownerName = ownerNameEt.text.toString()
                val repoName = repoNameEt.text.toString()
                if (ownerName.isNotBlank() && repoName.isNotBlank()) {
                    addRepoViewModel.getRepoDetail(ownerName,repoName)
                } else {
                    Toast.makeText(requireContext(), "Enter Owner and Repo Name Both", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeUIStates() {
        addRepoViewModel.isLoading.observe({lifecycle}) {
            if (it) {
                binding.apply {
                    loadingBar.visibility = View.VISIBLE
                    addRepoBtn.isClickable = false
                }
            } else {
                binding.apply {
                    loadingBar.visibility = View.INVISIBLE
                    addRepoBtn.isClickable = true
                }
            }
        }
        addRepoViewModel.repoDetail.observe({lifecycle}) {
            it?.let {
                addRepoViewModel.addRepositoryToDB(it)
                findNavController().popBackStack()
            }
        }
        addRepoViewModel.errorMessage.observe({lifecycle}) { errorMessage->
            errorMessage?.let {
                Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}