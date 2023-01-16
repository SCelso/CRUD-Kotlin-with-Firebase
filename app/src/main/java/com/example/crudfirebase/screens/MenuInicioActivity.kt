package com.example.crudfirebase.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.crudfirebase.composables.BackgroundImage
import com.example.crudfirebase.composables.ButtonDefault
import com.example.crudfirebase.composables.Title
import com.example.crudfirebase.navigation.AppScreens

@Composable
fun MenuInicio(navController: NavController) {
    Scaffold(topBar = { Title(title = "Farmacia", navController = navController, false) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("Anadir") },
                modifier = Modifier.size(70.dp),
                content = {
                    Text(
                        text = "+",
                        fontSize = 32.sp,
                        color = MaterialTheme.colors.surface
                    )
                })
        }) {
        BodyMenuInicio(navController)
    }
}


@Composable
fun BodyMenuInicio(navController: NavController) {
    BackgroundImage()
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()) {

        Spacer(modifier = Modifier.size(64.dp))





        ButtonDefault(text = "Consultar") {
            navController.navigate(AppScreens.Buscar.ruta)
        }

        ButtonDefault(text = "Informe General",
            onClick = { navController.navigate("InformeGeneral") })
        Spacer(modifier = Modifier.size(64.dp))


    }
}

@Preview(showBackground = true)
@Composable
fun MenuInicioPreview() {
    val navController = rememberNavController()
    MenuInicio(navController)
}