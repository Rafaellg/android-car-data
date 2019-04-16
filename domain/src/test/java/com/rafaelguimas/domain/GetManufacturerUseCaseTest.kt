package com.rafaelguimas.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelguimas.domain.data.CarTypeRepository
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.ManufacturerModel
import com.rafaelguimas.domain.use_case.GetManufacturerUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetManufacturerUseCaseTest {

    private val model = ManufacturerModel()
    private val carTypeRepository: CarTypeRepository = mock()
    private val getManufacturerUseCase = GetManufacturerUseCase(carTypeRepository)

    @Test
    fun getBuiltDateUseCase_isCorrect() = runBlocking {
        val expected = Result.Success(model)

        whenever(carTypeRepository.getManufacturer(15, 0)).thenReturn(expected)

        val result = getManufacturerUseCase(15, 0)

        assertEquals(Result.Success(model), result)
    }

    @Test
    fun getBuiltDateUseCase_isFail() = runBlocking {
        val expected = Result.Error(Failure.ServerError)

        whenever(carTypeRepository.getManufacturer(-1, -1)).thenReturn(expected)

        val result = getManufacturerUseCase(-1, -1)

        assertEquals(Result.Error(Failure.ServerError), result)
    }
}