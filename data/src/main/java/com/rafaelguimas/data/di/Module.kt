package com.rafaelguimas.data.di

import com.rafaelguimas.data.CarTypeRemoteDataSource
import com.rafaelguimas.data.CarTypeRepositoryImpl
import com.rafaelguimas.domain.data.CarTypeRepository
import org.koin.dsl.module

val carTypeModule = module {
//    single { CarTypeRepositoryImpl(get()) as CarTypeRepository }
//    single { CarTypeRemoteDataSource() }
}