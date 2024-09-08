package com.example.tp_2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.IOException
import com.google.gson.Gson


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
        val contactos = leerContactosDesdeArchivo(this)


        val listView = findViewById<ListView>(R.id.lv1)


        val adapter2 = ContactoAdapter(this, contactos)
        listView.adapter = adapter2
        listView.setOnItemClickListener { _, _, position, _ ->
            val contactoSeleccionado = contactos[position]


            val intent = Intent(this, InfoContacto::class.java)
            intent.putExtra("CONTACTO", contactoSeleccionado)


            startActivity(intent)
        }




    }
    private fun leerContactosDesdeArchivo(context: Context): List<Contacto> {
        val archivo = File(context.filesDir, "contactos.json")
        val gson = Gson()
        val contactos = mutableListOf<Contacto>()

        if (archivo.exists()) {
            try {
                archivo.forEachLine { linea ->
                    val contacto = gson.fromJson(linea, Contacto::class.java)
                    contactos.add(contacto)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                println("Error al leer los contactos.")
            }
        } else {
            println("El archivo de contactos no existe.")
        }

        return contactos
    }


}