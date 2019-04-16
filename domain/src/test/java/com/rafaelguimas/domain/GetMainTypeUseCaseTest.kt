package com.rafaelguimas.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelguimas.domain.data.CarTypeRepository
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.BuiltDateModel
import com.rafaelguimas.domain.model.MainTypeModel
import com.rafaelguimas.domain.use_case.GetMainTypeUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetMainTypeUseCaseTest {

    private val model = MainTypeModel()
    private val carTypeRepository: CarTypeRepository = mock()
    private val getMainTypeUseCase = GetMainTypeUseCase(carTypeRepository)

    @Test
    fun getBuiltDateUseCase_isCorrect() = runBlocking {
        val expected = Result.Success(model)

        whenever(carTypeRepository.getMainTypes("1", 15, 0)).thenReturn(expected)

        val result = getMainTypeUseCase("1", 15, 0)

        assertEquals(Result.Success(model), result)
    }

    @Test
    fun getBuiltDateUseCase_isFail() = runBlocking {
        val expected = Result.Error(Failure.ServerError)

        whenever(carTypeRepository.getMainTypes("0", -1, -1)).thenReturn(expected)

        val result = getMainTypeUseCase("0", -1, -1)

        assertEquals(Result.Error(Failure.ServerError), result)
    }
}