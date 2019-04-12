package com.rafaelguimas.data

import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.data.CarTypeRepository
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.BuiltDateModel
import com.rafaelguimas.domain.model.MainTypeModel
import com.rafaelguimas.domain.model.ManufacturerModel

class CarTypeRepositoryImpl(
    private val carTypeRemoteDataSource: CarTypeRemoteDataSource
) : CarTypeRepository {

    override suspend fun getManufacturer(pageSize: Int, page: Int): Result<ManufacturerModel> {
        val response = carTypeRemoteDataSource.getManufacturer(pageSize, page).await()

        return if (response.wkda.isNotEmpty()) {
            Result.Success(response)
        } else {
            Result.Error(Failure.ServerError)
        }
    }

    override suspend fun getMainTypes(manufacturer: Int, pageSize: Int, page: Int): Result<MainTypeModel> {
        val response = carTypeRemoteDataSource.getMainTypes(manufacturer, pageSize, page).await()

        return if (response.wkda.isNotEmpty()) {
            Result.Success(response)
        } else {
            Result.Error(Failure.ServerError)
        }
    }

    override suspend fun getBuiltDates(manufacturer: Int, mainType: Int): Result<BuiltDateModel> {
        val response = carTypeRemoteDataSource.getBuiltDates(manufacturer, mainType).await()

        return if (response.wkda.isNotEmpty()) {
            Result.Success(response)
        } else {
            Result.Error(Failure.ServerError)
        }
    }

}