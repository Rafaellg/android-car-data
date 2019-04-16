package com.rafaelguimas.cardata.ui.main_type

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rafaelguimas.cardata.Constants.PAGE_SIZE
import com.rafaelguimas.domain.exception.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainTypeViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    val failureLiveData = MutableLiveData<Failure>()
    var mainTypePagedListLiveData: LiveData<PagedList<Pair<String, String>>> = MutableLiveData<PagedList<Pair<String, String>>>()
    val manufacturerLiveData = MediatorLiveData<String>()
    lateinit var manufacturerId: String

    fun saveArgs(args: MainTypeFragmentArgs) {
        manufacturerId = args.manufacturerId
        manufacturerLiveData.value = args.manufacturerValue
    }

    fun getMainTypes() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()

        mainTypePagedListLiveData = LivePagedListBuilder(MainTypeDataSourceFactory(manufacturerId), pagedListConfig).build()
    }

}
