package com.example.tp_2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
    private lateinit var listaIntereses: String

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





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val radioGroup: RadioGroup  = findViewById(R.id.RadioGroupNivelEstudios)
        val idBtonoSeleccionado =radioGroup.checkedRadioButtonId


        if (idBtonoSeleccionado != -1) {
            val selectedRadioButton: RadioButton = findViewById(idBtonoSeleccionado)

            nivelEstudios=selectedRadioButton.text.toString()


        } else {
            Toast.makeText(this, "No se seleccionó ninguna opción", Toast.LENGTH_SHORT).show()
            nivelEstudios="NO ENTRO CORRECTAMENTE"
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            val selectedRadioButton = findViewById<RadioButton>(checkedId)
             nivelEstudios = selectedRadioButton.text.toString()

        }











        val btnSiguiente : Button =findViewById(R.id.BtnGuardarContacto)
        btnSiguiente.setOnClickListener{
            val intent = Intent(this@MasDatosUsuario, MainActivity::class.java)

            guardarContacto()
            startActivity(intent)
        }


    }
    private fun guardarContacto(){
        val sharedPreferences = getSharedPreferences("Contactos", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val chkBoxDeporte = findViewById<CheckBox>(R.id.Deporte)
        val chkBoxMusica = findViewById<CheckBox>(R.id.Musica)
        val chkBoxArte = findViewById<CheckBox>(R.id.Arte)
        val chkBoxTecnologia = findViewById<CheckBox>(R.id.Tecnologia)
        listaIntereses=""

        editor.putString("NOMBRE", nombre)
        editor.putString("APELLIDO", apellido)
        editor.putString("TELEFONO", telefono)
        editor.putString("EMAIL", email)
        editor.putString("DIRECCION", direccion)
        editor.putString("FECHA_NACIMIENTO", fechaNacimiento)

        if (chkBoxDeporte.isChecked) {
            listaIntereses+= chkBoxDeporte.text.toString()+" "
        }
        if (chkBoxMusica.isChecked) {
            listaIntereses+=chkBoxMusica.text.toString()+" "
        }
        if (chkBoxArte.isChecked) {
            listaIntereses+= chkBoxArte.text.toString()+" "
        }
        if (chkBoxTecnologia.isChecked) {
            listaIntereses+= chkBoxTecnologia.text.toString()+ " "
        }


        val switch = findViewById<Switch>(R.id.switchDeseaRecibirInfo)
        editor.putBoolean("DESEA_RECIBIR_INFO", switch.isChecked)




        editor.putString("INTERESES", listaIntereses)



        editor.putString("RADIO_BUTTON_SELECCIONADO", nivelEstudios)
        Log.d("GuardarContacto", "Intereses guardados: $listaIntereses")
        Log.d("GuardarContacto", "RadioButton seleccionado: $nivelEstudios")
        editor.apply()

    }
}