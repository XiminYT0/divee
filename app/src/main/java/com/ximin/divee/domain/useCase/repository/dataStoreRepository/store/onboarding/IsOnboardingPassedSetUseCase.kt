package com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.onboarding

import com.ximin.divee.domain.useCase.repository.dataStoreRepository.DataStoreRepositorySetValue
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.DataStoreCase

class IsOnboardingPassedSetUseCase(private val dataStoreRepositorySetValue: DataStoreRepositorySetValue) {
    suspend operator fun invoke(){
        dataStoreRepositorySetValue(DataStoreCase.isOnboardingPassed, true)
    }
}