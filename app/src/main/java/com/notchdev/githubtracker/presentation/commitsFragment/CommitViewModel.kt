package com.notchdev.githubtracker.presentation.commitsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notchdev.githubtracker.common.Resource
import com.notchdev.githubtracker.domain.model.CommitDetail
import com.notchdev.githubtracker.domain.use_case.get_commits.GetCommitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CommitViewModel @Inject constructor(
    private val getCommitsUseCase: GetCommitsUseCase
) : ViewModel() {

    private var _commitData = MutableLiveData<List<CommitDetail>?>()
    val commitData: LiveData<List<CommitDetail>?> = _commitData

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun getCommitDetailList(ownerName: String, repoName: String, branchName: String) {
        getCommitsUseCase(ownerName, repoName, branchName).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = result.errorMessage
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _commitData.value = result.data
                }
            }
        }.launchIn(viewModelScope)
    }
}