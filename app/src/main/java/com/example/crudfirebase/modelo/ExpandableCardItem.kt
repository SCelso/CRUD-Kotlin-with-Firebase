package com.example.crudfirebase.modelo

data class ExpandableCardItem(
    val id: String,
    val nombre: String,
    val categoria: String,
    val detalles: ItemDetail
) {
    data class ItemDetail(val toxico: Boolean, val estado:String)
}