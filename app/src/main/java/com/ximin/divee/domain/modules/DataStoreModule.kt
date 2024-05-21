package com.ximin.divee.domain.modules

import android.content.Context
import com.ximin.divee.data.implementations.DataStoreRepositoryImpl
import com.ximin.divee.domain.repository.DataStoreRepository
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.DataStoreRepositoryGetValue
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.DataStoreRepositorySetValue
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.onboarding.IsOnboardingPassedGetUseCase
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.onboarding.IsOnboardingPassedSetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository{
     return DataStoreRepositoryImpl(context)
    }
    @Provides
    @Singleton
    fun provideDataStoreRepositoryGetValueUseCase(
        dataStoreRepository: DataStoreRepository
    ): DataStoreRepositoryGetValue {
        return DataStoreRepositoryGetValue(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepositorySetValueUseCase(
        dataStoreRepository: DataStoreRepository
    ): DataStoreRepositorySetValue {
        return DataStoreRepositorySetValue(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideIsOnboardingPassedGetUseCase(
        dataStoreRepositoryGetValueUseCase: DataStoreRepositoryGetValue
    ): IsOnboardingPassedGetUseCase {
        return IsOnboardingPassedGetUseCase(dataStoreRepositoryGetValueUseCase)
    }

    @Provides
    @Singleton
    fun provideIsOnboardingPassedSetUseCase(
        dataStoreRepositorySetValueUseCase: DataStoreRepositorySetValue
    ): IsOnboardingPassedSetUseCase {
        return IsOnboardingPassedSetUseCase(dataStoreRepositorySetValueUseCase)
    }

}