package com.rafaelguimas.cardata.ui.built_date

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.BuiltDateModel
import com.rafaelguimas.domain.use_case.GetBuiltDateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BuiltDateViewModel(
    private val getBuiltDateUseCase: GetBuiltDateUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    val progressLiveData = MutableLiveData<Boolean>()
    val failureLiveData = MutableLiveData<Failure>()
    val builtDateModelLiveData = MutableLiveData<BuiltDateModel>()
    val manufacturerLiveData = MediatorLiveData<String>()
    val mainTypeLiveData = MediatorLiveData<String>()
    lateinit var manufacturerId: String

    fun saveArgs(args: BuiltDateFragmentArgs) {
        manufacturerId = args.manufacturerId
        manufacturerLiveData.value = args.manufacturerValue
        mainTypeLiveData.value = args.mainType
    }

    fun getBuildDates() {
        launch {
            progressLiveData.value = true

            mainTypeLiveData.value?.let { mainType ->
                when (val result = getBuiltDateUseCase(manufacturerId, mainType)) {
                    is Result.Success -> builtDateModelLiveData.value = result.data
                    is Result.Error -> failureLiveData.value = result.failure
                }
            }

            progressLiveData.value = false
        }
    }

}
