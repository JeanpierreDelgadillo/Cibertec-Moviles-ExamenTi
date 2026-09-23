package pe.edu.cibertec.myapplication

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.myapplication.databinding.ActivityPregunta4Binding

class Pregunta4Activity : AppCompatActivity(), View.OnClickListener  {

    private lateinit var binding: ActivityPregunta4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPregunta4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnCalculo.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnCalculo.id -> calcularComision()
        }
    }
    private fun calcularComision() {

        val textoSobregiro =
            binding.etsobregiro.text.toString().trim()

        if (textoSobregiro.isEmpty()) {
            binding.etsobregiro.error = "Ingrese el monto del sobregiro"
            return
        }

        val sobregiro = textoSobregiro.toDoubleOrNull()

        if (sobregiro == null || sobregiro < 0) {
            binding.etsobregiro.error = "Ingrese un monto válido"
            return
        }

        binding.etsobregiro.error = null

        val limite = 5000.00

        if (sobregiro <= limite) {

            binding.textView3.text =
                "Sobregiro protegido por línea preferente."

        } else {

            val exceso = sobregiro - limite

            val comision = 150.00 + (exceso * 0.03)

            binding.textView3.text = """
            Sobregiro solicitado: S/ ${String.format("%.2f", sobregiro)}
            Exceso del límite: S/ ${String.format("%.2f", exceso)}
            Comisión total aplicada: S/ ${String.format("%.2f", comision)}
        """.trimIndent()
        }
    }



}