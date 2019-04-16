package com.rafaelguimas.cardata.di

import com.rafaelguimas.cardata.ui.built_date.BuiltDateViewModel
import com.rafaelguimas.cardata.ui.main_type.MainTypeViewModel
import com.rafaelguimas.cardata.ui.manufacturer.ManufacturerDataSource
import com.rafaelguimas.cardata.ui.manufacturer.ManufacturerDataSourceFactory
import com.rafaelguimas.cardata.ui.manufacturer.ManufacturerViewModel
import com.rafaelguimas.cardata.ui.summary.SummaryViewModel
import com.rafaelguimas.domain.use_case.GetBuiltDateUseCase
import com.rafaelguimas.domain.use_case.GetMainTypeUseCase
import com.rafaelguimas.domain.use_case.GetManufacturerUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ManufacturerViewModel(get()) }
    viewModel { MainTypeViewModel() }
    viewModel { BuiltDateViewModel(get()) }
    viewModel { SummaryViewModel() }
}

val useCaseModule = module {
    single { GetManufacturerUseCase(get()) }
    single { GetMainTypeUseCase(get()) }
    single { GetBuiltDateUseCase(get()) }
}

val dataSourceModule = module {
    single { ManufacturerDataSourceFactory(get()) }
    single { ManufacturerDataSource(get()) }
}