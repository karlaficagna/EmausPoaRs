package com.ficagna.tcc2.ui.model

class Eventos (

    var evento: String,
    var local: String,
    var dataEvento: String,
    var horario: String,
    val imgEvento: String
){
    constructor() : this("", "", "", "", "")

}