package com.rafaelguimas.cardata.ui.manufacturer

import androidx.lifecycle.*
import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.ManufacturerModel
import com.rafaelguimas.domain.use_case.GetManufacturerUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ManufacturerViewModel(
    private val getManufacturerUseCase: GetManufacturerUseCase
) : ViewModel(), LifecycleObserver, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    val progressLiveData = MutableLiveData<Boolean>()
    val failureLiveData = MutableLiveData<Failure>()
    val manufacturerModelLiveData = MutableLiveData<ManufacturerModel>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        launch {
            progressLiveData.value = true

            val result = getManufacturerUseCase(20, 0)

            when (result) {
                is Result.Success -> manufacturerModelLiveData.value = result.data
                is Result.Error -> failureLiveData.value = result.failure
            }

            progressLiveData.value = false
        }
    }

}
