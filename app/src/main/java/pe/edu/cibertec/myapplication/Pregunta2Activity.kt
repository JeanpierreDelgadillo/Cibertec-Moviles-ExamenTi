package pe.edu.cibertec.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.myapplication.databinding.ActivityPregunta2Binding

class Pregunta2Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPregunta2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btncalcular.setOnClickListener(this)
    }

    // Metodo para calcular el monto de reposición según las prendas falladas
    fun CalculoEconomico(pFalladas: Int): Double {
        return if (pFalladas > 10) {
            val exceso = pFalladas - 10
            100.0 + (exceso * 28.0)
        } else {
            0.0
        }
    }

    // Metodo para construir el mensaje según la regla de negocio del caso
    fun pMensajes(pFalladas: Int): String {
        return if (pFalladas <= 10) {
            "Nivel de merma dentro del margen admisible."
        } else {
            val exceso = pFalladas - 10
            val montoTotal = CalculoEconomico(pFalladas)
            val montoFormateado = String.format("S/ %.2f", montoTotal)

            """
            Fallas registradas: $pFalladas
            Exceso de prendas defectuosas: $exceso
            Descuento total por reposición: $montoFormateado
            """.trimIndent()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btncalcular -> calcularRespuesta()
        }
    }

    // Metodo que ejecuta la acción del botón
    fun calcularRespuesta() {
        val input = binding.txtprendasfalladas.text.toString()

        if (input.isNotEmpty()) {
            val pFalladas = input.toInt()
            val mensaje = pMensajes(pFalladas)

            // Asigna la respuesta al TextView de tu layout
            binding.tvdescripcion.text = mensaje
        } else {
            binding.tvdescripcion.text = "Por favor, ingrese la cantidad de prendas falladas."
        }
    }
}