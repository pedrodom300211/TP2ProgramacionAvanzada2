package com.example.tp_2
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ContactoAdapter (context: Context, private val contactos: List<Contacto>) :
    ArrayAdapter<Contacto>(context, 0, contactos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val contacto = getItem(position)


        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_contacto, parent, false)


        val textViewNombre = view.findViewById<TextView>(R.id.textViewNombre)
        val textViewEmail = view.findViewById<TextView>(R.id.textViewEmail)


        textViewNombre.text = "${contacto?.nombre} ${contacto?.apellido}"
        textViewEmail.text = contacto?.email


        return view
    }
}