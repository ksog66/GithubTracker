package com.notchdev.githubtracker.domain.use_case.get_repo

import com.notchdev.githubtracker.common.Resource
import com.notchdev.githubtracker.domain.model.RepositoryDetail
import com.notchdev.githubtracker.domain.repository.GitTrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRepoUseCase @Inject constructor(
    private val gitTrackerRepository: GitTrackerRepository
) {
    operator fun invoke(ownerName:String,repoName:String) : Flow<Resource<RepositoryDetail>> = flow {
        try {
            emit(Resource.Loading())
            val repositoryDetail = gitTrackerRepository.getRepoDetail(ownerName,repoName).toRepositoryDetail()
            emit(Resource.Success(repositoryDetail))
        } catch( e:HttpException) {
            emit(Resource.Error<RepositoryDetail>(e.localizedMessage ?: "Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<RepositoryDetail>("Couldn't Reach server. Check Your internet connection"))
        }
    }
}