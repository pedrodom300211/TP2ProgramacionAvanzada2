package com.example.tp_2

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VerContactos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_contactos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recuperar datos de SharedPreferences
        val sharedPreferences = getSharedPreferences("Contactos", Context.MODE_PRIVATE)

        // Recuperar los datos que se han guardado
        val nombre = sharedPreferences.getString("NOMBRE", "No disponible")
        val apellido = sharedPreferences.getString("APELLIDO", "No disponible")
        val telefono = sharedPreferences.getString("TELEFONO", "No disponible")
        val email = sharedPreferences.getString("EMAIL", "No disponible")
        val direccion = sharedPreferences.getString("DIRECCION", "No disponible")
        val fechaNacimiento = sharedPreferences.getString("FECHA_NACIMIENTO", "No disponible")
        val deseaRecibirInfo = sharedPreferences.getBoolean("DESEA_RECIBIR_INFO", false)
        val intereses = sharedPreferences.getString("INTERESES", "No disponible")
        val radioButtonSeleccionadoId = sharedPreferences.getString("RADIO_BUTTON_SELECCIONADO", "No disponible")

        // Convertir los datos a un formato de texto
        val datosTexto = """
            Nombre: $nombre
            Apellido: $apellido
            Teléfono: $telefono
            Email: $email
            Dirección: $direccion
            Fecha de Nacimiento: $fechaNacimiento
            Desea Recibir Información: ${if (deseaRecibirInfo) "Sí" else "No"}
            Intereses: $intereses
            Radio Button Seleccionado ID: $radioButtonSeleccionadoId
        """.trimIndent()

        // Mostrar los datos en el TextView
        val textViewDatos = findViewById<TextView>(R.id.textViewIntereses)
        textViewDatos.text = datosTexto
    }


}