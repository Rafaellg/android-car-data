package com.rafaelguimas.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelguimas.domain.data.CarTypeRepository
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.BuiltDateModel
import com.rafaelguimas.domain.use_case.GetBuiltDateUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetBuiltDateUseCaseTest {

    private val model = BuiltDateModel()
    private val carTypeRepository: CarTypeRepository = mock()
    private val getBuiltDateUseCase = GetBuiltDateUseCase(carTypeRepository)

    @Test
    fun getBuiltDateUseCase_isCorrect() = runBlocking {
        val expected = Result.Success(model)

        whenever(carTypeRepository.getBuiltDates("1", "1")).thenReturn(expected)

        val result = getBuiltDateUseCase("1", "1")

        assertEquals(Result.Success(model), result)
    }

    @Test
    fun getBuiltDateUseCase_isFail() = runBlocking {
        val expected = Result.Error(Failure.ServerError)

        whenever(carTypeRepository.getBuiltDates("0", "")).thenReturn(expected)

        val result = getBuiltDateUseCase("0", "")

        assertEquals(Result.Error(Failure.ServerError), result)
    }
}