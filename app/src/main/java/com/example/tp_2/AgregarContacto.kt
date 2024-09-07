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
import android.widget.EditText


class AgregarContacto : AppCompatActivity() {

    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var telefono: EditText
    private lateinit var email: EditText
    private lateinit var direccion: EditText
    private lateinit var fechaNacimiento: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_contacto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nombre = findViewById(R.id.editTextNombre)
        apellido = findViewById(R.id.editTextApellido)
        telefono = findViewById(R.id.editTextTelefono)
        email = findViewById(R.id.editTextEmail)
        direccion = findViewById(R.id.editTextDireccion)
        fechaNacimiento = findViewById(R.id.editTextDateFechaNacimiento)

        //Evento click boton siguiente
        val btnSiguiente :Button=findViewById(R.id.buttonContinuar)
        btnSiguiente.setOnClickListener{
            if(validar()) {
                val intent = Intent(this, MasDatosUsuario::class.java)
                //envio datos a otro activity
                intent.putExtra("nombre", nombre.text.toString())
                intent.putExtra("apellido", apellido.text.toString())
                intent.putExtra("telefono", telefono.text.toString())
                intent.putExtra("email", email.text.toString())
                intent.putExtra("direccion", direccion.text.toString())
                intent.putExtra("fechaNacimiento", fechaNacimiento.text.toString())

                startActivity(intent)

            }
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

    private fun validar(): Boolean {

        if(nombre.text.isEmpty()||apellido.text.isEmpty()||telefono.text.isEmpty()||email.text.isEmpty()||direccion.text.isEmpty()||fechaNacimiento.text.isEmpty()){
            Toast.makeText(this,"Completa los campos",Toast.LENGTH_SHORT).show()
            return false
        }
        if (!nombre.text.toString().matches(Regex("^[A-Za-z ]+$"))) {
            Toast.makeText(this, "El nombre debe contener solo letras", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!apellido.text.toString().matches(Regex("^[A-Za-z ]+$"))) {
            Toast.makeText(this, "El apellido debe contener solo letras", Toast.LENGTH_SHORT).show()
            return false
        }

        if(!telefono.text.toString().matches(Regex("^[0-9-]+$"))){
            Toast.makeText(this,"El teléfono puede contener solo números y guiones",Toast.LENGTH_SHORT).show()
            return false
        }
        if(!email.text.toString().matches(Regex("^[^@]+@[^@]+\\.[a-zA-Z]{2,}\$"))){
            Toast.makeText(this,"Ingrese un email válido",Toast.LENGTH_SHORT).show()
            return false
        }
    return true
    }
}