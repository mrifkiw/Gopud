package com.example.gofood

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.ScrollView
import android.widget.Toast
import com.example.gofood.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kacangPanjang.text = getString(R.string.default_text_bahan1)
        binding.bawangPutih.text = getString(R.string.default_text_bahan2)
        binding.arakMasak.text = getString(R.string.default_text_bahan3)
        binding.garamPenyedap.text = getString(R.string.default_text_bahan4)

        binding.buttonUbah.setOnClickListener{ changeCountMaterial() }

    }


    fun changeCountMaterial() {
        val stringTextField = binding.editPorsiEditText.text.toString()
        val porsi = stringTextField.toDoubleOrNull() ?: return

        val resultKacangPanjang = 0.555 * porsi
        val resultBawangPutih = 1.65 * porsi

        countKacangMaterial(resultKacangPanjang)
        countBawangMaterial(resultBawangPutih)
        countArakMaterial(porsi)
        countGaramMaterial(porsi)
    }

    fun countKacangMaterial(porsi: Double) {
        val formattedTip = NumberFormat.getNumberInstance().format(porsi)
        binding.kacangPanjang.text = getString(R.string.kacang_panjang, formattedTip)
    }

    fun countBawangMaterial(porsi: Double) {
        val formattedTip = NumberFormat.getNumberInstance().format(porsi)
        binding.bawangPutih.text = getString(R.string.bawang_putih, formattedTip)
    }
    fun countArakMaterial(porsi: Double) {
        val formattedTip = NumberFormat.getNumberInstance().format(porsi)
        binding.arakMasak.text = getString(R.string.arak_masak, formattedTip)
    }
    fun countGaramMaterial(porsi: Double) {
        val formattedTip = NumberFormat.getNumberInstance().format(porsi)
        binding.garamPenyedap.text = getString(R.string.garam_penyedap, formattedTip)
    }
}