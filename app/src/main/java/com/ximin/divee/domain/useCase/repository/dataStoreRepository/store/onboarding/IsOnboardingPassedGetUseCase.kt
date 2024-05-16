package com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.onboarding

import com.ximin.divee.domain.useCase.repository.dataStoreRepository.DataStoreRepositoryGetValue
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.DataStoreCase

class IsOnboardingPassedGetUseCase(
    private val dataStoreRepositoryGetValue: DataStoreRepositoryGetValue
) {

    suspend operator fun invoke(key: String):Boolean{

        return dataStoreRepositoryGetValue(DataStoreCase.isOnboardingPassed) ?: false
    }
}