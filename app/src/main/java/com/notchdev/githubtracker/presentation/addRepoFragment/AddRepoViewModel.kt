package com.notchdev.githubtracker.presentation.addRepoFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notchdev.githubtracker.common.Resource
import com.notchdev.githubtracker.data.local.RepositoryDetail
import com.notchdev.githubtracker.domain.use_case.add_repo.AddRepoUseCase
import com.notchdev.githubtracker.domain.use_case.get_repo.GetRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRepoViewModel @Inject constructor(
    private val getRepoUseCase: GetRepoUseCase,
    private val addRepoUseCase: AddRepoUseCase
):ViewModel() {

    private var _repoDetail = MutableLiveData<RepositoryDetail?>()
    val repoDetail: LiveData<RepositoryDetail?> = _repoDetail

    private var _errorMessage = MutableLiveData<String?>()
    val errorMessage:LiveData<String?> = _errorMessage

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    fun getRepoDetail(ownerName:String,repoName:String) {
        getRepoUseCase(ownerName,repoName).onEach { result->
            when(result) {
                is Resource.Error -> {
                    _errorMessage.value = "Not Found"
                    _isLoading.value = false
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Success -> {
                    _repoDetail.value = result.data
                    _isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addRepositoryToDB(repoDetail:RepositoryDetail) = viewModelScope.launch {
        addRepoUseCase.addRepoDetail(repoDetail)
    }
}