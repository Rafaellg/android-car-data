package com.rafaelguimas.cardata.ui.manufacturer

import androidx.paging.DataSource

class ManufacturerDataSourceFactory(
    private val manufacturerDataSource: ManufacturerDataSource
) : DataSource.Factory<Int, Pair<String, String>>() {

    override fun create(): DataSource<Int, Pair<String, String>> {
        return manufacturerDataSource
    }
}