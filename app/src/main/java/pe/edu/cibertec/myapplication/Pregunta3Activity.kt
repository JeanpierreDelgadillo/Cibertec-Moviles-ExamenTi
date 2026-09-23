package pe.edu.cibertec.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.Toast
import pe.edu.cibertec.myapplication.databinding.ActivityPregunta3Binding
import java.text.NumberFormat
import java.util.Locale

class Pregunta3Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPregunta3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCalcular.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnCalcular.id -> calcularMulta()
        }
    }

    private fun calcularMulta() {
        val textoDias = binding.etDiasRetraso.text.toString()

        if (textoDias.isBlank()) {
            Toast.makeText(this, "Ingrese los días de retraso", Toast.LENGTH_SHORT).show()
            return
        }

        val diasRetraso = textoDias.toInt()
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "PE"))

        if (diasRetraso <= 3) {
            binding.tvResultado.text = "Préstamo regularizado dentro de la prórroga."
        } else {
            val diasSujetosCobro = diasRetraso - 3
            val multa = 12.0 + (3.50 * diasSujetosCobro)

            binding.tvResultado.text = "Días de demora: $diasRetraso\n" +
                    "Días sujetos a cobro: $diasSujetosCobro\n" +
                    "Multa administrativa: ${formatoMoneda.format(multa)}"
        }
    }
}
