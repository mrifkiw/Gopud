package com.example.gofood.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PriceTest {
    var testHarga = Price(
        kacang = 4_500.0,
        garam = 27_400.0,
        arak = 50_000.0,
        bawang = 5_000.0
    )

    @Test
    fun whenGetTotalHargaIsValid() {
        val expectedTotal: Double = 4_500.0 + 27_400.0 + 50_000.0 + 5_000.0
        val actualTotal: Double = testHarga.getTotalPrice()
        assertThat(actualTotal).isEqualTo(expectedTotal)
    }
}