package com.ficagna.emausPoaRs.ui.model

data class Product(
    var imageProd: String,
    var nameProd: String,
    var corProd: String,
    var tamanho: String,
    var preco: String,
    var quantidade: String,
    var total: String
) {
    constructor() : this("", "", "", "", "", "", "")
}