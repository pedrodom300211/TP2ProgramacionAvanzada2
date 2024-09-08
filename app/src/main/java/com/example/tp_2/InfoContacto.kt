package com.example.tp_2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InfoContacto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info_contacto)
        // Obtener el contacto desde el Intent
        val contacto = intent.getParcelableExtra<Contacto>("CONTACTO")


        val textViewNombre = findViewById<TextView>(R.id.textViewNombre)
        val textViewApellido = findViewById<TextView>(R.id.textViewApellido)
        val textViewEmail = findViewById<TextView>(R.id.textViewEmail)
        val textViewTelefono = findViewById<TextView>(R.id.textViewTelefono)
        val textViewFechaNacimiento = findViewById<TextView>(R.id.textViewFechaNacimiento)
        val textViewDireccion = findViewById<TextView>(R.id.textViewDireccion)
        val textViewNivelEstudios = findViewById<TextView>(R.id.textViewNivelEstudios)
        val textViewIntereses = findViewById<TextView>(R.id.textViewIntereses)
        val textViewDeseaInformacion = findViewById<TextView>(R.id.textViewDeseaInformacion)


        contacto?.let {
            textViewNombre.text = it.nombre
            textViewApellido.text = it.apellido
            textViewEmail.text = it.email
            textViewTelefono.text = it.telefono
            textViewFechaNacimiento.text = it.FechaNaciemiento
            textViewDireccion.text = it.Direccion
            textViewNivelEstudios.text = it.NivelEstudios
            textViewIntereses.text = it.Intereses
            textViewDeseaInformacion.text = it.DeseaInformacion
        }
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)
        btnRegresar.setOnClickListener {

            finish()
        }
    }

}