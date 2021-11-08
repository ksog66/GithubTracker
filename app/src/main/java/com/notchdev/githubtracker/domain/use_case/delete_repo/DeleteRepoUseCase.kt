package com.notchdev.githubtracker.domain.use_case.delete_repo

import com.notchdev.githubtracker.data.local.RepositoryDao
import javax.inject.Inject

class DeleteRepoUseCase @Inject constructor(
    private val repoDao:RepositoryDao
) {

    suspend fun deleteRepo(id:Int) {
        repoDao.deleteRepositoryEntity(id)
    }
}