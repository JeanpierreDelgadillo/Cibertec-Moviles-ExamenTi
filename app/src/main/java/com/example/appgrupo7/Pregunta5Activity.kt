package com.example.appgrupo7

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.appgrupo7.databinding.ActivityPregunta5Binding

class Pregunta5Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregunta5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        val texto = binding.edtDecibelios.text.toString()

        if (texto.isEmpty()) {
            binding.txtResultado.text = "Ingrese un valor"
            return
        }

        val decibelios = texto.toDouble()

        if (decibelios <= 55) {

            binding.txtResultado.text =
                "Nivel sonoro conforme a la ordenanza."

        } else {

            val exceso = decibelios - 55

            val multa = 1200 + (180 * exceso)

            binding.txtResultado.text =
                "Decibelios: $decibelios\n" +
                        "Exceso: $exceso\n" +
                        "Multa: S/ %.2f".format(multa)
        }
    }
}