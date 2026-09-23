package pe.edu.cibertec.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.myapplication.databinding.ActivityPregunta1Binding
import java.text.NumberFormat
import java.util.Locale

class Pregunta1Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPregunta1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnCalcular.id -> calcularPenalidad()
        }
    }

    private fun calcularPenalidad() {
        val textoDias = binding.etDiasRetraso.text.toString()

        if (textoDias.isBlank()) {
            Toast.makeText(this, "Ingrese los días de retraso", Toast.LENGTH_SHORT).show()
            return
        }

        val diasRetraso = textoDias.toInt()
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "PE"))

        if (diasRetraso <= 5) {
            binding.tvResultado.text = "Entrega dentro de la tolerancia contractual."
        } else {
            val diasComputables = diasRetraso - 5
            val penalidad = 500.0 + (150.0 * diasComputables)

            binding.tvResultado.text = "Días de retraso: $diasRetraso\n" +
                    "Días computables para penalidad: $diasComputables\n" +
                    "Penalidad: ${formatoMoneda.format(penalidad)}"
        }
    }
}