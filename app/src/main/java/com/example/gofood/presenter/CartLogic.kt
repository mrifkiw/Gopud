package com.example.gofood

//Metode Penghitungan sub-total
fun subTotalHargaKacang(harga: String, qty: String): Double {
    return harga.toDouble() * qty.toDouble()
}

//Metode Konversi porsi -> Jumlah bahan
fun konversiPorsiKeBahan(porsi: String, besaranBahan: Double): Double {
    var result = porsi.toDouble() * besaranBahan
    return result
}

fun plusButton(quantity: Int): Int {
    return quantity + 1
}

fun minusButton(quantity: Int): Int {
    if (quantity > 0) {
        return quantity - 1
    }else{
        return quantity
    }
}
