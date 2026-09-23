package pe.edu.cibertec.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.myapplication.databinding.ActivityPregunta6Binding

class Pregunta6Activity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPregunta6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregunta6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCalcular -> calcularValor()
        }
    }

    private fun calcularValor() {
        val horas = binding.etHoras.text.toString().toIntOrNull()

        if (horas == null) {
            binding.tvResultado.text = "Ingrese las horas consumidas"
        } else if (horas <= 40) {
            binding.tvResultado.text =
                "Consumo cubierto por la póliza mensual contratada\n" +
                        "Facturacion total: S/ 0.00"

        } else {
            val exceso = horas -40
            val facturacion = 300 + (85 * exceso)

            binding.tvResultado.text =
                "Horas totales consumidas: " + horas +
                        "\nHoras excedentes: " + exceso +
                        "\nFacturación complementaria: " +
                        "Facturacion total: S/ %.2f".format(facturacion)


        }

    }
}