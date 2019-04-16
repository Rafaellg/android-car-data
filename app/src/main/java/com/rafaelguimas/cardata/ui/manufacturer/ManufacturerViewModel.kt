package com.rafaelguimas.cardata.ui.manufacturer

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rafaelguimas.cardata.Constants
import com.rafaelguimas.domain.exception.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class ManufacturerViewModel(
    manufacturerDataSourceFactory: ManufacturerDataSourceFactory
) : ViewModel(), LifecycleObserver, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    val failureLiveData = MutableLiveData<Failure>()
    var manufacturerPagedListLiveData: LiveData<PagedList<Pair<String, String>>> = MutableLiveData<PagedList<Pair<String, String>>>()

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.PAGE_SIZE)
            .build()

        manufacturerPagedListLiveData = LivePagedListBuilder(manufacturerDataSourceFactory, pagedListConfig).build()
    }

}
