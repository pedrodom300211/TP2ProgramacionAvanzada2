package com.example.tp_2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val spinner:Spinner=findViewById(R.id.spinner_main_activity_1)
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
                Toast.makeText(this@MainActivity, "$selectedItem", Toast.LENGTH_SHORT).show()
                when (selectedItem) {
                    "Agregar Contactos" -> {
                        val intent = Intent(this@MainActivity, AgregarContacto::class.java)
                        startActivity(intent)
                    }
                    "Listado de contactos" -> {
                        val intent = Intent(this@MainActivity, VerContactos::class.java)
                        startActivity(intent)
                    }
            }



        }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
}



}