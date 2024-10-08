package com.example.tp_2

import android.os.Parcel
import android.os.Parcelable

class Contacto(
    val nombre: String,
    val apellido: String,
    val email: String,
    val telefono: String,
    val FechaNaciemiento: String,
    val Direccion: String,
    val NivelEstudios: String,
    val Intereses: String,
    val DeseaInformacion: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(email)
        parcel.writeString(telefono)
        parcel.writeString(FechaNaciemiento)
        parcel.writeString(Direccion)
        parcel.writeString(NivelEstudios)
        parcel.writeString(Intereses)
        parcel.writeString(DeseaInformacion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contacto> {
        override fun createFromParcel(parcel: Parcel): Contacto {
            return Contacto(parcel)
        }

        override fun newArray(size: Int): Array<Contacto?> {
            return arrayOfNulls(size)
        }
    }
}
