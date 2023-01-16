package com.example.crudfirebase.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.EmptyPath
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.crudfirebase.composables.ButtonDefault
import com.example.crudfirebase.composables.ExpandableCardRow
import com.example.crudfirebase.composables.Title
import com.example.crudfirebase.modelo.ExpandableCardItem
import com.example.crudfirebase.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.lang.reflect.Modifier


@Composable
fun InformeGeneral(navController: NavController) {
    Scaffold(topBar = { Title(title = "Informe General", navController = navController) }) {
        InformeGeneralBody(navController)
    }
}

@Composable
fun InformeGeneralBody(navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    var datos by remember { mutableStateOf("") }
    var farmacos by remember { mutableStateOf(emptyArray<ExpandableCardItem>()) }



    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()) {


        ButtonDefault(text = "Cargar", onClick = {
            farmacos = emptyArray<ExpandableCardItem>()
            datos = ""
            db.collection("farmacos")
                .get().addOnSuccessListener { resultado ->


                    for (encontrado in resultado) {

                        //val expandableCardItem= encontrado.toObject<ExpandableCardItem>()
                        val expandableCardItem = ExpandableCardItem(
                            encontrado.id,
                            encontrado["nombre"] as String,
                            encontrado["categoria"] as String,
                            ExpandableCardItem.ItemDetail(encontrado["toxico"] as Boolean,
                                encontrado["estado"] as String))

                        farmacos += expandableCardItem

                        Log.i("DATA:", expandableCardItem.toString())


                    }
                    if (datos.isEmpty()) {
                        datos = "No existen datos"
                    }
                }.addOnFailureListener {
                    datos = "La conexión a FireStore no se ha podido completar"
                }
        })
    }
    Column() {
        farmacos.forEach {
            Log.i("Objeto:", it.toString())
            ExpandableCardRow(expandableCardItem = it,
                { navController.navigate(AppScreens.Editar.ruta) }) {
                db.collection("farmacos").document(it.id)
                    .delete()
                    .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully deleted!") }
                    .addOnFailureListener { e -> Log.w("TAG", "Error deleting document", e) }
            }
        }
    }

}






