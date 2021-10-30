package com.example.gofood

class Price(
    kacang: Double?,
    garam: Double?,
    arak: Double?,
    bawang: Double?
) {
    public var kacang: Double?
    public var garam: Double?
    public var arak: Double?
    public var bawang: Double?

    init {
        this.kacang = kacang
        this.garam = garam
        this.arak = arak
        this.bawang = bawang
    }

    fun getTotalPrice(): Double {
        return kacang!! + garam!! + arak!! + bawang!!
    }

    fun updateSubTotalKacang(price: Double) {
        this.kacang = price
    }

    fun updateSubTotalGaram(price: Double) {
        this.garam = price
    }

    fun updateSubTotalArak(price: Double) {
        this.arak = price
    }

    fun updateSubTotalBawang(price: Double) {
        this.bawang = price
    }

}