package com.rafaelguimas.domain.use_case

import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.data.CarTypeRepository
import com.rafaelguimas.domain.model.BuiltDateModel

class GetBuiltDateUseCase(
    private val carTypeRepository: CarTypeRepository
) {
    suspend operator fun invoke(manufacturer: String, mainType: String): Result<BuiltDateModel> {
        return carTypeRepository.getBuiltDates(manufacturer, mainType)
    }
}