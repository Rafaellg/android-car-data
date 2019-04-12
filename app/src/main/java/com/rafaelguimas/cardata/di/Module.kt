package com.rafaelguimas.cardata.di

import com.rafaelguimas.cardata.ui.manufacturer.ManufacturerViewModel
import com.rafaelguimas.data.CarTypeRemoteDataSource
import com.rafaelguimas.data.CarTypeRepositoryImpl
import com.rafaelguimas.domain.data.CarTypeRepository
import com.rafaelguimas.domain.use_case.GetManufacturerUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ManufacturerViewModel(get()) }
}

val useCaseModule = module {
    single { GetManufacturerUseCase(get()) }
    single { CarTypeRepositoryImpl(get()) as CarTypeRepository }
    single { CarTypeRemoteDataSource() }
}