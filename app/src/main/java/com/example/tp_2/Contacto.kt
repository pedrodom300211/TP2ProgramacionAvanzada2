package com.example.tp_2

import java.util.Date

class Contacto {
    val nombre: String
    val apellido: String
    val email: String
    val telefono: String
    val FechaNaciemiento: String
    val Direccion: String
    val NivelEstudios: String
    val Intereses: String
    val DeseaInformacion :String


    constructor(
        nombre: String,
        apellido: String,
        email: String,
        telefono: String,
        FechaNaciemiento: String,
        Direccion: String,
        NivelEstudios: String,
        Intereses: String,
        DeseaInformacion: String
    ) {
        this.nombre = nombre
        this.apellido = apellido
        this.email = email
        this.telefono = telefono
        this.FechaNaciemiento = FechaNaciemiento
        this.Direccion = Direccion
        this.NivelEstudios = NivelEstudios
        this.Intereses = Intereses
        this.DeseaInformacion = DeseaInformacion
    }
}