package com.notchdev.githubtracker.domain.use_case.get_repo_issues

import com.notchdev.githubtracker.common.Resource
import com.notchdev.githubtracker.domain.model.IssuesDetail
import com.notchdev.githubtracker.domain.repository.GitTrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRepoIssuesUseCase @Inject constructor(
    private val gitTrackerRepository: GitTrackerRepository
) {
    operator fun invoke(ownerName:String,repoName:String) : Flow<Resource<List<IssuesDetail>>> = flow {
        try {
            emit(Resource.Loading())
            val issueDetailList = gitTrackerRepository.getRepoIssues(ownerName,repoName).map { it.toIssuesDetail() }
            emit(Resource.Success(issueDetailList))
        } catch(e: HttpException) {
            emit(Resource.Error<List<IssuesDetail>>(e.localizedMessage ?: "Unexpected Error Occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<IssuesDetail>>("Couldn't Reach Server. Check Your internet Connection"))
        }
    }
}