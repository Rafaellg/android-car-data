package com.rafaelguimas.domain.use_case

import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.data.CarTypeRepository
import com.rafaelguimas.domain.model.MainTypeModel

class GetMainTypeUseCase(
    private val carTypeRepository: CarTypeRepository
) {
    suspend operator fun invoke(manufacturer: String, pageSize: Int, page: Int): Result<MainTypeModel> {
        return carTypeRepository.getMainTypes(manufacturer, pageSize, page)
    }
}