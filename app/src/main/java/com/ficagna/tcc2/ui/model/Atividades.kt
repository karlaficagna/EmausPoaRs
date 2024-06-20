package com.ficagna.tcc2.ui.model

class Atividades (

    var evento: String,
    var local: String,
    var dataEvento: String,
    var horario: String,
    val imgAtividade: String
){
    constructor() : this("", "", "", "", "")

}