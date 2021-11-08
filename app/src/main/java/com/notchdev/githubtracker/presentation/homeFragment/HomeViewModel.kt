package com.notchdev.githubtracker.presentation.homeFragment

import androidx.lifecycle.*
import com.notchdev.githubtracker.data.local.RepositoryDetail
import com.notchdev.githubtracker.domain.use_case.get_repo_list.GetRepoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRepoListUseCase: GetRepoListUseCase
) : ViewModel() {

    private var _repoData = MutableLiveData<List<RepositoryDetail>>()
    val repoData: LiveData<List<RepositoryDetail>> = _repoData

    fun getRepoListFromDB()  {
        getRepoListUseCase.invoke().onEach {
            _repoData.value = it
        }.launchIn(viewModelScope)
    }
}