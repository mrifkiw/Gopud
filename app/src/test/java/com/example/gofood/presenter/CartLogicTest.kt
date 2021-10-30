package com.example.gofood.presenter

import com.example.gofood.CartLogic
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CartLogicTest {

    @Test
    fun whenKonversiPorsiKeBahanIsValid() {
        val porsi: String = "12"
        val besaranBahan: Double = 1000.0
        val result: Double = CartLogic.konversiPorsiKeBahan(porsi, besaranBahan)
        assertThat(result).isEqualTo(porsi.toDouble() * besaranBahan)
    }

    @Test
    fun whenSubTotalHargaKacangIsValid() {
        val harga: String = "4500"
        val qty: String = "4"
        val result: Double = CartLogic.subTotalHargaKacang(harga,qty)
        assertThat(result).isEqualTo(18000.0)
    }

    @Test
    fun whenMinusButtonIsValid() {
        val qty = 5
        for (i in 1..(qty + 1)) {
            CartLogic.minusButton(qty)
        }
        assertThat(qty).isGreaterThan(0)
    }

}