package com.ximin.divee.domain.useCase.repository.dataStoreRepository

import com.ximin.divee.domain.repository.DataStoreRepository

class DataStoreRepositoryGetValue(private val repository: DataStoreRepository) {
    suspend operator fun invoke(key: String):Boolean?{
        return repository.getValue(key)
    }

}