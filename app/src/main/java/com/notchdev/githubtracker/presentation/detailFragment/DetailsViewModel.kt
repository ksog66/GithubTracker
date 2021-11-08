package com.notchdev.githubtracker.presentation.detailFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notchdev.githubtracker.common.Resource
import com.notchdev.githubtracker.domain.model.BranchDetail
import com.notchdev.githubtracker.domain.model.IssuesDetail
import com.notchdev.githubtracker.domain.use_case.delete_repo.DeleteRepoUseCase
import com.notchdev.githubtracker.domain.use_case.get_branches.GetBranchesUseCase
import com.notchdev.githubtracker.domain.use_case.get_repo_issues.GetRepoIssuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBranchesUseCase: GetBranchesUseCase,
    private val getRepoIssuesUseCase: GetRepoIssuesUseCase,
    private val deleteRepoUseCase: DeleteRepoUseCase,
):ViewModel() {

    private var _branchData = MutableLiveData<List<BranchDetail>?>()
    val branchData: LiveData<List<BranchDetail>?> = _branchData

    private var _issuesData = MutableLiveData<List<IssuesDetail>?>()
    val issuesData: LiveData<List<IssuesDetail>?> = _issuesData

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _errorMessage = MutableLiveData<String?>()
    val errorMessage:LiveData<String?> = _errorMessage

    fun getBranchList(ownerName:String,repoName:String) {
        getBranchesUseCase(ownerName,repoName).onEach {result ->
            when(result) {
                is Resource.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = result.errorMessage
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _branchData.value = result.data
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getIssuesList(ownerName:String,repoName:String) {
        getRepoIssuesUseCase(ownerName,repoName).onEach { result->
            when(result) {
                is Resource.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = result.errorMessage
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _issuesData.value = result.data
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteRepo(id:Int) = viewModelScope.launch{
        deleteRepoUseCase.deleteRepo(id)
    }
}