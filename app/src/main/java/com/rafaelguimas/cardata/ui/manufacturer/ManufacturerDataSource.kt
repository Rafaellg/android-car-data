package com.rafaelguimas.cardata.ui.manufacturer

import androidx.paging.PageKeyedDataSource
import com.rafaelguimas.cardata.Constants.PAGE_SIZE
import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.use_case.GetManufacturerUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ManufacturerDataSource(
    private val getManufacturerUseCase: GetManufacturerUseCase
) : PageKeyedDataSource<Int, Pair<String, String>>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Pair<String, String>>) {
        launch {
            when (val result = getManufacturerUseCase(PAGE_SIZE, 0)) {
                is Result.Success -> callback.onResult(result.data.wkda.toList(), null, 1)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Pair<String, String>>) {
        launch {
            when (val result = getManufacturerUseCase(PAGE_SIZE, params.key)) {
                is Result.Success -> {
                    val model = result.data
                    if (model.wkda.size > 0) {
                        val key = if (model.totalPageCount > params.key) {
                            params.key + 1
                        } else {
                            null
                        }

                        callback.onResult(model.wkda.toList(), key)
                    }
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Pair<String, String>>) {
        // This is not necessary in our case as our data doesn't change.
    }
}