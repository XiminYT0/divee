package com.ximin.divee.presentation.mainActivity.viewModel

import androidx.lifecycle.ViewModel
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.onboarding.IsOnboardingPassedGetUseCase
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.onboarding.IsOnboardingPassedSetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val isOnboardingPassedSetUseCase: IsOnboardingPassedSetUseCase,
    private val isOnboardingPassedGetUseCase: IsOnboardingPassedGetUseCase,
) :ViewModel() {

}