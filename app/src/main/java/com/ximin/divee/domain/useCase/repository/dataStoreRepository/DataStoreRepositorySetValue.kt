package com.ximin.divee.domain.useCase.repository.dataStoreRepository

import com.ximin.divee.domain.repository.DataStoreRepository

class DataStoreRepositorySetValue(private val repository: DataStoreRepository) {
    suspend operator fun invoke(key:String, value: Boolean){
        repository.setValue(key,value)
    }
}