package com.horoscopeapp.ui.provider

import org.junit.Assert.*
import org.junit.Test

class RandomCardProviderTest {

    @Test
    fun `given a RandomCardProvider when getLuck then should return a not null random luck card`() {
        val randomCardProvider = RandomCardProvider()
        val luck = randomCardProvider.getLucky()

        assertNotNull(luck)
    }
}