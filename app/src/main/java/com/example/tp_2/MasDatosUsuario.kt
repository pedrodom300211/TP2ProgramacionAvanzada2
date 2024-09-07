package com.example.tp_2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MasDatosUsuario : AppCompatActivity() {
    private lateinit var nombre: String
    private lateinit var apellido: String
    private lateinit var telefono: String
    private lateinit var email: String
    private lateinit var direccion: String
    private lateinit var fechaNacimiento: String
    private lateinit var listaIntereses: MutableList<String>
    private var IDBtonoSeleccionado: Int = -1
    private var nivelEstudios =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mas_datos_usuario)
        nombre = intent.getStringExtra("nombre") ?: ""
        apellido = intent.getStringExtra("apellido") ?: ""
        telefono = intent.getStringExtra("telefono") ?: ""
        email = intent.getStringExtra("email") ?: ""
        direccion = intent.getStringExtra("direccion") ?: ""
        fechaNacimiento = intent.getStringExtra("fechaNacimiento") ?: ""

        listaIntereses = mutableListOf()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val radioGroup = findViewById<RadioGroup>(R.id.RadioGroupNivelEstudios)
        IDBtonoSeleccionado = radioGroup.checkedRadioButtonId

        val chkBoxDeporte = findViewById<CheckBox>(R.id.Deporte)
        val chkBoxMusica = findViewById<CheckBox>(R.id.Musica)
        val chkBoxArte = findViewById<CheckBox>(R.id.Arte)
        val chkBoxTecnologia = findViewById<CheckBox>(R.id.Tecnologia)

        val listaIntereses=mutableListOf<String>()

        if (chkBoxDeporte.isChecked) {
            listaIntereses.add(chkBoxDeporte.text.toString())
        }
        if (chkBoxMusica.isChecked) {
            listaIntereses.add(chkBoxMusica.text.toString())
        }
        if (chkBoxArte.isChecked) {
            listaIntereses.add(chkBoxArte.text.toString())
        }
        if (chkBoxTecnologia.isChecked) {
            listaIntereses.add(chkBoxTecnologia.text.toString())
        }

        if (IDBtonoSeleccionado != -1) {

            val IDBtonoSeleccionado = findViewById<RadioButton>(IDBtonoSeleccionado)
            val selectedText = IDBtonoSeleccionado.text.toString()
            nivelEstudios=selectedText;

        } else {
            Toast.makeText(this, "No se seleccionó ninguna opción", Toast.LENGTH_SHORT).show()
        }

        val switch = findViewById<Switch>(R.id.switchDeseaRecibirInfo)
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // El Switch está en "On" (Sí)

            } else {
                // El Switch está en "Off" (No)

            }
        }

        val btnSiguiente : Button =findViewById(R.id.BtnGuardarContacto)
        btnSiguiente.setOnClickListener{
            GuardarContacto()
        }


    }
    private fun GuardarContacto(){
        val sharedPreferences = getSharedPreferences("Contactos", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("NOMBRE", nombre)
        editor.putString("APELLIDO", apellido)
        editor.putString("TELEFONO", telefono)
        editor.putString("EMAIL", email)
        editor.putString("DIRECCION", direccion)
        editor.putString("FECHA_NACIMIENTO", fechaNacimiento)


        val switch = findViewById<Switch>(R.id.switchDeseaRecibirInfo)
        editor.putBoolean("DESEA_RECIBIR_INFO", switch.isChecked)




        editor.putString("INTERESES", listaIntereses.toString())



        editor.putString("RADIO_BUTTON_SELECCIONADO", nivelEstudios)
        editor.apply()

    }
}