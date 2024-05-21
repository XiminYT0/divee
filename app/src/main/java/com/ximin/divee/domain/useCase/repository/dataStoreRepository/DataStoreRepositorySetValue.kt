package com.ximin.divee.domain.useCase.repository.dataStoreRepository

import com.ximin.divee.domain.repository.DataStoreRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


class DataStoreRepositorySetValue  constructor(private val repository: DataStoreRepository) {
    suspend operator fun invoke(key:String, value: Boolean){
        repository.setValue(key,value)
    }
}