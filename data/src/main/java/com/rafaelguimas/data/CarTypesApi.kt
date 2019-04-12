package com.rafaelguimas.data

import com.rafaelguimas.domain.model.BuiltDateModel
import com.rafaelguimas.domain.model.MainTypeModel
import com.rafaelguimas.domain.model.ManufacturerModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CarTypesApi {
    @GET("/v1/car-types/manufacturer")
    fun getManufacturer(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Deferred<ManufacturerModel>

    @GET("/v1/car-types/main-types")
    fun getMainTypes(
        @Query("manufacturer") manufacturer: Int,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ):  Deferred<MainTypeModel>

    @GET("/v1/car-types/built-dates")
    fun getBuiltDates(
        @Query("manufacturer") manufacturer: Int,
        @Query("main-type") mainType: Int
    ):  Deferred<BuiltDateModel>
}