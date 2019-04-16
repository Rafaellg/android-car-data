package com.rafaelguimas.cardata.ui.main_type

import androidx.paging.DataSource

class MainTypeDataSourceFactory(
    private val manufacturer: String
) : DataSource.Factory<Int, Pair<String, String>>() {

    override fun create(): DataSource<Int, Pair<String, String>> {
        return MainTypeDataSource(manufacturer)
    }
}