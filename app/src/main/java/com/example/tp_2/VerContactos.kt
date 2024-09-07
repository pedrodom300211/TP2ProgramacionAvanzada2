package com.example.tp_2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.ListView
import android.widget.Toast
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
        val spinner: Spinner =findViewById(R.id.spinner_main_activity_1)
        val items = resources.getStringArray(R.array.listado_items)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter=adapter
        val btnThreeDots: ImageButton = findViewById(R.id.btn_three_dots)
        btnThreeDots.setOnClickListener {
            spinner.performClick()
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{


            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, ID: Long) {
                val selectedItem: String =items[posicion]
                Toast.makeText(this@VerContactos, "$selectedItem", Toast.LENGTH_SHORT).show()
                when (selectedItem) {
                    "Agregar Contactos" -> {
                        val intent = Intent(this@VerContactos, AgregarContacto::class.java)
                        startActivity(intent)
                    }
                    "Listado de contactos" -> {
                        val intent = Intent(this@VerContactos, VerContactos::class.java)
                        startActivity(intent)
                    }
                }



            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
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

        Log.d("RecuperarDatos", "Intereses recuperados: $intereses")
        Log.d("RecuperarDatos", "RadioButton seleccionado: $radioButtonSeleccionadoId")

        val datosTexto = """
            Nombre: $nombre
            Apellido: $apellido
            Teléfono: $telefono
            Email: $email
            Dirección: $direccion
            Fecha de Nacimiento: $fechaNacimiento
            Desea Recibir Información: ${if (deseaRecibirInfo) "Sí" else "No"}
            Intereses: $intereses
            Nivel estudios: $radioButtonSeleccionadoId
        """.trimIndent()

        val arrayAdapter:ArrayAdapter<*>

        val lv1 = findViewById<ListView>(R.id.lv1)
        val datosEmail = mutableListOf("$nombre $apellido - $email")

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datosEmail)
        lv1.adapter = arrayAdapter

        // Mostrar los datos en el TextView
       // val textViewDatos = findViewById<TextView>(R.id.textViewIntereses)
       // textViewDatos.text = datosTexto
    }


}