package com.rafaelguimas.domain.use_case

import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.data.CarTypeRepository
import com.rafaelguimas.domain.model.ManufacturerModel

class GetManufacturerUseCase(
    private val carTypeRepository: CarTypeRepository
) {
    suspend operator fun invoke(pageSize: Int, page: Int) : Result<ManufacturerModel> {
        return carTypeRepository.getManufacturer(pageSize, page)
    }
}