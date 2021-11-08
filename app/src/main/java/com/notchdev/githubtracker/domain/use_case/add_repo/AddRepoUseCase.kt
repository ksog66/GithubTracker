package com.notchdev.githubtracker.domain.use_case.add_repo

import com.notchdev.githubtracker.data.local.RepositoryDao
import com.notchdev.githubtracker.data.local.RepositoryDetail
import javax.inject.Inject

class AddRepoUseCase @Inject constructor(
    private val repositoryDao: RepositoryDao
) {
    suspend fun addRepoDetail(repositoryDetail: RepositoryDetail) =
        repositoryDao.insertRepositoryDetail(repositoryDetail)

}