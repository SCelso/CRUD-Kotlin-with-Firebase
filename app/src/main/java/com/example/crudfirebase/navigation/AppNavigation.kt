package com.example.crudfirebase.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crudfirebase.screens.*


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.Inicio.ruta)

    {
      
        composable(AppScreens.Anadir.ruta) { Anadir(navigationController) }
        composable(AppScreens.InformeGeneral.ruta) { InformeGeneral(navigationController) }
        composable(AppScreens.Editar.ruta) { Editar(navigationController) }
        composable(AppScreens.Buscar.ruta) { Buscar(navigationController) }
        composable(AppScreens.Inicio.ruta) { Inicio(navigationController) }

    }
}