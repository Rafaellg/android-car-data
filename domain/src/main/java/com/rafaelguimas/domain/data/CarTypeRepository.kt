package com.rafaelguimas.domain.data

import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.model.BuiltDateModel
import com.rafaelguimas.domain.model.MainTypeModel
import com.rafaelguimas.domain.model.ManufacturerModel

interface CarTypeRepository {
    suspend fun getManufacturer(pageSize: Int, page: Int): Result<ManufacturerModel>
    suspend fun getMainTypes(manufacturer: String, pageSize: Int, page: Int): Result<MainTypeModel>
    suspend fun getBuiltDates(manufacturer: String, mainType: String): Result<BuiltDateModel>
}