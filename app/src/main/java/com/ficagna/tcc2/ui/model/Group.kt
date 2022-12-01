package com.ficagna.tcc2.ui.model

data class Group (
    var nameCoordenador: String,
    var localReuniao: String,
    var diaReuniao: String,
    var hora: String,
    var image: String
) {
    constructor() : this("","","","","")
}
