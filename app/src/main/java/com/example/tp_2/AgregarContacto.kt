package com.example.tp_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarContacto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_contacto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Evento click boton siguiente
        val btnSiguiente :Button=findViewById(R.id.buttonContinuar)
        btnSiguiente.setOnClickListener{
            val intent = Intent(this, MasDatosUsuario::class.java)
            startActivity(intent)
        }




        val spinner: Spinner =findViewById(R.id.spinnerTelefono)
        val items = resources.getStringArray(R.array.listado_items_Telefono_Email)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter=adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, ID: Long) {
                val selectedItem=items[posicion]
               // Toast.makeText(this@AgregarContacto, "$selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        val spinnerEmail: Spinner =findViewById(R.id.spinnerEmail)
        val itemsEmail = resources.getStringArray(R.array.listado_items_Telefono_Email)
        val adapterEmail = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
        adapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerEmail.adapter=adapterEmail

        spinnerEmail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, ID: Long) {
                val selectedItem=itemsEmail[posicion]
                // Toast.makeText(this@AgregarContacto, "$selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }
}