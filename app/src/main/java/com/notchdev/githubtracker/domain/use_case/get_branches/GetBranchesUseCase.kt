package com.notchdev.githubtracker.domain.use_case.get_branches

import com.notchdev.githubtracker.common.Resource
import com.notchdev.githubtracker.domain.model.BranchDetail
import com.notchdev.githubtracker.domain.repository.GitTrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBranchesUseCase @Inject constructor(
    private val gitTrackerRepository: GitTrackerRepository
) {
    operator fun invoke(ownerName:String,repoName:String): Flow<Resource<List<BranchDetail>>> = flow {
        try {
            emit(Resource.Loading())
            val branchList = gitTrackerRepository.getRepoBranches(ownerName,repoName).map { it.toBranchDetail() }
            emit(Resource.Success(branchList))
        } catch(e: HttpException) {
            emit(Resource.Error<List<BranchDetail>>(e.localizedMessage ?: "Unexpected Error Occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<BranchDetail>>("Couldn't reach server. Check Your internet Connection"))
        }
    }
}