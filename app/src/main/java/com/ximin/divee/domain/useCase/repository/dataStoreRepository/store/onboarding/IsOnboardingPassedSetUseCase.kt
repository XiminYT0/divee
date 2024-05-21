package com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.onboarding

import com.ximin.divee.domain.useCase.repository.dataStoreRepository.DataStoreRepositorySetValue
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.DataStoreCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class IsOnboardingPassedSetUseCase @Inject constructor(private val dataStoreRepositorySetValue: DataStoreRepositorySetValue) {
    suspend operator fun invoke(){
        dataStoreRepositorySetValue(DataStoreCase.isOnboardingPassed, true)
    }
}