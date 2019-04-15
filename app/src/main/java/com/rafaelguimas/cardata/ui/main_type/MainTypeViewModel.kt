package com.rafaelguimas.cardata.ui.main_type

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaelguimas.cardata.Constants.PAGE_SIZE
import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.MainTypeModel
import com.rafaelguimas.domain.use_case.GetMainTypeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainTypeViewModel(
    private val getMainTypeUseCase: GetMainTypeUseCase
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
    val mainTypeModelLiveData = MutableLiveData<MainTypeModel>()
    val manufacturerLiveData = MediatorLiveData<String>()
    lateinit var manufacturerId: String

    fun saveArgs(args: MainTypeFragmentArgs) {
        manufacturerId = args.manufacturerId
        manufacturerLiveData.value = args.manufacturerValue
    }

    fun getMainTypes() {
        launch {
            progressLiveData.value = true

            val result = getMainTypeUseCase(manufacturerId, PAGE_SIZE, 0)

            when (result) {
                is Result.Success -> mainTypeModelLiveData.value = result.data
                is Result.Error -> failureLiveData.value = result.failure
            }

            progressLiveData.value = false
        }
    }

}
