package pe.edu.cibertec.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.myapplication.databinding.ActivityPregunta7Binding

class Pregunta7Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta7Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregunta7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
        binding.btnLimpiar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCalcular -> calcularPenalidad()
            R.id.btnLimpiar -> {
                binding.etDiasRetraso.text.clear()
                binding.tvResultado.text = ""
            }
        }
    }

    private fun calcularPenalidad() {

        val dias = binding.etDiasRetraso.text.toString().toIntOrNull()

        if (dias == null) {
            binding.tvResultado.text = "Ingrese los días de retraso"
        } else if (dias <= 2) {

            binding.tvResultado.text =
                "Devolución aceptada sin penalidad.\n" +
                        "Penalidad total: S/ 0.00"

        } else {
            val exceso = dias - 2
            val penalidad = 35.0 + (20.0 * exceso)

            binding.tvResultado.text =
                "Días de atraso: $dias\n" +
                        "Exceso computable: $exceso\n" +
                        "Penalidad total: S/ %.2f".format(penalidad)
        }
    }
}