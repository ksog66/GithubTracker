package com.notchdev.githubtracker.domain.use_case.get_commits

import com.notchdev.githubtracker.common.Resource
import com.notchdev.githubtracker.domain.model.CommitDetail
import com.notchdev.githubtracker.domain.repository.GitTrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCommitsUseCase @Inject constructor(
    private val gitTrackerRepository: GitTrackerRepository
) {
    operator fun invoke(ownerName:String,repoName:String,branchName:String): Flow<Resource<List<CommitDetail>>> = flow {
        try {
            emit(Resource.Loading())
            val commitList = gitTrackerRepository.getRepoCommits(ownerName,repoName,branchName).toCommitDetailList()
            emit(Resource.Success(commitList))
        } catch (e: HttpException) {
            emit(Resource.Error<List<CommitDetail>>(e.localizedMessage ?: "Unexpected Error Occured"))
        } catch (e:IOException) {
            emit(Resource.Error<List<CommitDetail>>("Couldn't reach the server. Check your Internet Connection"))
        }
    }
}