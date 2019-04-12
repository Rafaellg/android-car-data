package com.rafaelguimas.cardata.di

import com.rafaelguimas.domain.use_case.GetManufacturerUseCase
import org.koin.dsl.module

val viewModelModule = module {

}

val useCaseModule = module {
    single { GetManufacturerUseCase(get()) }
}