package com.ficagna.tcc2.ui.model

data class Product (
    val imageProd: String,
    val nameProd: String,
    val corProd: String,
    val tamanho: String,
    val preco: String,
    val quantidade: String,
    val total: String
)
{
    constructor() : this("","","","","","","")
}