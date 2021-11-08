package com.notchdev.githubtracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositoryDetail(repositoryEntity: RepositoryDetail)

    @Query("Select * from repositorydetail")
    fun getAllRepositoryEntity(): Flow<List<RepositoryDetail>>

    @Query("Delete from repositorydetail where id = :id")
    suspend fun deleteRepositoryEntity(id:Int)
}