package com.horoscopeapp.ui.horoscope

import com.horoscopeapp.data.providers.HoroscopeProvider
import com.horoscopeapp.domain.model.HoroscopeInfo
import com.horoscopeapp.motherobject.HoroscopeMotherObject
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HoroscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel: HoroscopeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `given a viewmodel when is created then horoscopes is not an empty list`() {
        every { horoscopeProvider.getHoroscopes() } returns HoroscopeMotherObject.horoscopeList
        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscopes = viewModel.horoscope.value

        assertTrue(horoscopes.isNotEmpty())
    }

    @Test
    fun `given a viewmodel when is created then horoscopes is the expected list of horoscopes`() {
        every { horoscopeProvider.getHoroscopes() } returns HoroscopeMotherObject.horoscopeList
        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscopes = viewModel.horoscope.value

        horoscopes shouldBe HoroscopeMotherObject.horoscopeList
    }

    @Test
    fun `given a viewmodel when is created then horoscopes is not equals as an empty list`() {
        every { horoscopeProvider.getHoroscopes() } returns HoroscopeMotherObject.horoscopeList
        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscopes = viewModel.horoscope.value
        val anotherList = listOf<HoroscopeInfo>()

        horoscopes shouldNotBe anotherList
    }
}