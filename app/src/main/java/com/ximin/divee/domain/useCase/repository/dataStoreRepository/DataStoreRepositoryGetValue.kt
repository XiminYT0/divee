package com.ximin.divee.domain.useCase.repository.dataStoreRepository

import com.ximin.divee.domain.repository.DataStoreRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


class DataStoreRepositoryGetValue  constructor(private val repository: DataStoreRepository) {
    suspend operator fun invoke(key: String):Boolean?{
        return repository.getValue(key)
    }

}