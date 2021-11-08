package com.notchdev.githubtracker.domain.use_case.get_repo_list

import com.notchdev.githubtracker.common.Resource
import com.notchdev.githubtracker.data.local.RepositoryDao
import com.notchdev.githubtracker.data.local.RepositoryDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetRepoListUseCase @Inject constructor(
    private val repoDao:RepositoryDao
) {
    operator fun invoke(): Flow<List<RepositoryDetail>> = repoDao.getAllRepositoryEntity()
}