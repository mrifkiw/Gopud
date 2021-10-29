package com.example.gofood

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ShareCompat
import com.example.gofood.databinding.ActivityCartBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.*
import kotlin.math.ceil

const val sdt = 15
class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    companion object {
        const val PORSI = "porsi"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val porsi = intent?.getStringExtra(PORSI)


        var kacang = ceil(konversiPorsiKeBahan(porsi!!,0.555))
        var kacangPrice = subTotalHargaKacang(kacang.toString(), 4500.toString())
        var subTotalKacang = NumberFormat.getInstance().format(kacangPrice)

        var garam = ceil(konversiPorsiKeBahan(porsi,1.65/15))
        var garamPrice = subTotalHargaKacang(garam.toString(), (0.5 * 55000).toString())
        var subTotalGaram = NumberFormat.getInstance().format(garamPrice)

        var arak = ceil(konversiPorsiKeBahan(porsi, 1.0/120))
        var arakPrice = subTotalHargaKacang(arak.toString(), 40000.toString())
        var subTotalArak = NumberFormat.getInstance().format(arakPrice)

        var bawang = ceil(konversiPorsiKeBahan(porsi, 1.0/8000))
        var bawangPrice = subTotalHargaKacang(bawang.toString(), 55000.toString())
        var subTotalBawang = NumberFormat.getInstance().format(bawangPrice)

        var harga = Price(
            kacangPrice,
            garamPrice,
            arakPrice,
            bawangPrice
        )

        binding.subtotalKacangPanjangPrice.text = getString(R.string.subtotal_kacang, subTotalKacang)
        binding.kacangPanjangQuantity.text = kacang.toString()
        binding.buttonUbahMinusKacang.setOnClickListener{
            kacang = minusButton(kacang.toInt()).toDouble()
            checkIsDelete(it,kacang, binding.cardViewKacang)
            binding.kacangPanjangQuantity.text = kacang.toString()
            harga.updateSubTotalKacang(kacang * 4500)
            subTotalKacang = NumberFormat.getInstance().format(harga.kacang)
            binding.subtotalKacangPanjangPrice.text = getString(R.string.subtotal_kacang, subTotalKacang)
            updateTotalHarga(harga)
        }
        binding.buttonUbahPlusKacang.setOnClickListener{
            kacang = plusButton(kacang.toInt()).toDouble()
            binding.kacangPanjangQuantity.text = kacang.toString()
            harga.updateSubTotalKacang(kacang * 4500)
            subTotalKacang = NumberFormat.getInstance().format(harga.kacang)
            binding.subtotalKacangPanjangPrice.text = getString(R.string.subtotal_kacang, subTotalKacang)
            updateTotalHarga(harga)
        }
        //misal 15 sdt = 500gram

        binding.subtotalGaramPrice.text = getString(R.string.subtotal_garam, subTotalGaram)
        binding.garamQuantity.text = garam.toString()
        binding.buttonUbahMinusGaram.setOnClickListener{
            garam = minusButton(garam.toInt()).toDouble()
            checkIsDelete(it,garam, binding.cardViewGaram)
            binding.garamQuantity.text = garam.toString()
            harga.updateSubTotalGaram(garam * 0.5 * 55000)
            subTotalGaram = NumberFormat.getInstance().format(harga.garam)
            binding.subtotalGaramPrice.text = getString(R.string.subtotal_garam, subTotalGaram)
            updateTotalHarga(harga)
        }
        binding.buttonUbahPlusGaram.setOnClickListener{
            garam = plusButton(garam.toInt()).toDouble()
            binding.garamQuantity.text = garam.toString()
            harga.updateSubTotalGaram(garam * 0.5 * 55000)
            subTotalGaram = NumberFormat.getInstance().format(harga.garam)
            binding.subtotalGaramPrice.text = getString(R.string.subtotal_garam, subTotalGaram)
            updateTotalHarga(harga)
        }

        //misal 1 sdt = 5 ml arak
        // contoh 120 sdt => 12 * 5 = 600ml => satu botol

        binding.subtotalArakPrice.text = getString(R.string.subtotal_arak, subTotalArak)
        binding.arakQuantity.text = arak.toString()
        binding.buttonUbahMinusArak.setOnClickListener{
            arak = minusButton(arak.toInt()).toDouble()
            checkIsDelete(it,arak, binding.cardViewArak)
            binding.arakQuantity.text = arak.toString()
            harga.updateSubTotalArak((arak * 40000))
            subTotalArak = NumberFormat.getInstance().format(harga.arak)
            binding.subtotalArakPrice.text = getString(R.string.subtotal_arak, subTotalArak)
            updateTotalHarga(harga)
        }
        binding.buttonUbahPlusArak.setOnClickListener{
            arak = plusButton(arak.toInt()).toDouble()
            binding.arakQuantity.text = arak.toString()
            harga.updateSubTotalArak((arak * 40000))
            subTotalArak = NumberFormat.getInstance().format(harga.arak)
            binding.subtotalArakPrice.text = getString(R.string.subtotal_arak, subTotalArak)
            updateTotalHarga(harga)
        }

        //1 siung = 8 gram

        binding.subtotalBawangPutihPrice.text = getString(R.string.subtotal_bawang, subTotalBawang)
        binding.bawangPutihQuantity.text = bawang.toString()
        binding.buttonUbahMinusBawangPutih.setOnClickListener{
            bawang = minusButton(bawang.toInt()).toDouble()
            checkIsDelete(it,bawang, binding.cardViewBawangPutih)
            binding.bawangPutihQuantity.text = bawang.toString()
//            bawangPrice = garam * 0.5 * 55000
            harga.updateSubTotalBawang((bawang * 55000))
            subTotalBawang = NumberFormat.getInstance().format(harga.bawang)
            binding.subtotalBawangPutihPrice.text = getString(R.string.subtotal_bawang, subTotalBawang)
            updateTotalHarga(harga)
        }
        binding.buttonUbahPlusBawangPutih.setOnClickListener{
            bawang = plusButton(bawang.toInt()).toDouble()
            binding.bawangPutihQuantity.text = bawang.toString()
            harga.updateSubTotalBawang((bawang * 55000))
            subTotalBawang = NumberFormat.getInstance().format(harga.bawang)
            binding.subtotalBawangPutihPrice.text = getString(R.string.subtotal_bawang, subTotalBawang)
            updateTotalHarga(harga)
        }

        //perhitungan total awal
        updateTotalHarga(harga)

        binding.buttonCheckout.setOnClickListener{
            var totalHarga = binding.totalPrice.text
            val queryUrl: Uri = Uri.parse("${CartActivity.SEARCH_PREFIX}${totalHarga}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }

    }

    override fun onStop() {
        super.onStop()
        makeToast("Aktivity being Stop")
    }

    override fun onResume() {
        super.onResume()
        makeToast("Aktivity being Resume")
    }

    fun makeToast(msg: String) {
        var toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun checkIsDelete(view: View, quantity: Double, layout: LinearLayout){
        if (quantity == 0.0) {
            layout.visibility = View.GONE
            var snackbar = Snackbar.make(view, "Apakah anda yakin?", Snackbar.LENGTH_SHORT)
            snackbar.setAction("UNDO", View.OnClickListener {
                layout.visibility = View.VISIBLE
            })
            snackbar.show()
        }
    }

    fun updateTotalHarga(price: Price){
        var total = price.getTotalPrice()
        var totalPrice = NumberFormat.getInstance().format(total)
        binding.totalPrice.text = getString(R.string.total_price, totalPrice)
    }

}