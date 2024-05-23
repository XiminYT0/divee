package com.ximin.divee.presentation.mainActivity.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.onboarding.IsOnboardingPassedGetUseCase
import com.ximin.divee.domain.useCase.repository.dataStoreRepository.store.onboarding.IsOnboardingPassedSetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val isOnboardingPassedSetUseCase: IsOnboardingPassedSetUseCase,
    private val isOnboardingPassedGetUseCase: IsOnboardingPassedGetUseCase,
) :ViewModel() {
    var isOnboardingPassed: Boolean= false
        private set
    init {
//    getIsOnboardingPassedStatus()
}
//    private fun getIsOnboardingPassedStatus(){
//        viewModelScope.launch {
//            isOnboardingPassed = isOnboardingPassedGetUseCase.invoke()
//        }
//    }
}