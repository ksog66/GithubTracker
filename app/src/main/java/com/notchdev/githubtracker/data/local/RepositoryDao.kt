package com.notchdev.githubtracker.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RepositoryDao {

    @Insert
    suspend fun insertRepositoryDetail(repositoryEntity: RepositoryEntity)

    @Query("Select * from repositoryentity")
    fun getAllRepositoryEntity(): LiveData<List<RepositoryEntity>>

    @Query("Delete from repositoryentity where id = :id")
    suspend fun deleteRepositoryEntity(id:Int)
}