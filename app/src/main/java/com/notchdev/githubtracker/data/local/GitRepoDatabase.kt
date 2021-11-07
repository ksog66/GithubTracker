package com.notchdev.githubtracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [RepositoryEntity::class],
    version = 1
)
abstract class GitRepoDatabase : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao
}