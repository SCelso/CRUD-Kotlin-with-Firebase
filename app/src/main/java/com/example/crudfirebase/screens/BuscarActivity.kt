package com.example.crudfirebase.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.crudfirebase.composables.*
import com.example.crudfirebase.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore
import com.example.crudfirebase.modelo.ExpandableCardItem as ExpandableCardItem

@Composable
fun Buscar(navController: NavController) {
    Scaffold(topBar = { Title(title = "Buscar", navController = navController) }) {
        BuscarBody(navController)
    }


}

@Composable
fun BuscarBody(navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    var farmaco by remember {
        mutableStateOf(ExpandableCardItem(id = "",
            nombre = "",
            categoria = "",
            detalles = ExpandableCardItem.ItemDetail(toxico = false, estado = "")))
    }
    var text by remember { mutableStateOf("") }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()) {
        TextInput(text = text, onValueChange = { text = it }, label = "Buscar")

        ButtonDefault(text = "Buscar", onClick = {
            db.collection("farmacos").document(text).get().addOnSuccessListener { document ->
                val expandableCardItem = ExpandableCardItem(
                    document.id,
                    document.data?.get("nombre") as String,
                    document.data?.get("categoria") as String,
                    ExpandableCardItem.ItemDetail(document.data?.get("toxico") as Boolean,
                        document.data?.get("estado") as String))

                farmaco = expandableCardItem
            }.addOnFailureListener { e -> Log.w("TAG", "Error", e) }

        })

        ExpandableCardRow(expandableCardItem = farmaco,
            { navController.navigate(AppScreens.Editar.ruta) }) {
            db.collection("farmacos").document(farmaco.id)
                .delete()
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w("TAG", "Error deleting document", e) }
        }
    }
}