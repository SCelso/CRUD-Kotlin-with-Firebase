package com.example.crudfirebase.navigation

// CREAMOS UNA SEALED CLASS PARA CONTENER LOS OBJETOS DE CADA RUTA
sealed class AppScreens(val ruta: String){
    object MenuInicio: AppScreens("MenuInicio")
    object Anadir: AppScreens("Anadir")
    object InformeGeneral: AppScreens("InformeGeneral")
    object Editar: AppScreens("Editar")
    object Buscar: AppScreens("Buscar")
    object Inicio: AppScreens("Inicio")

    }
