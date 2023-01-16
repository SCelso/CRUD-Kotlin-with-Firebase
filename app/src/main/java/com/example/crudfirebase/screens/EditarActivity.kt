package com.example.crudfirebase.screens

import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.crudfirebase.MainActivity
import com.example.crudfirebase.composables.BackgroundImage
import com.example.crudfirebase.composables.ButtonDefault
import com.example.crudfirebase.composables.TextInput
import com.example.crudfirebase.composables.Title
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Editar(navController: NavController) {

    Scaffold(topBar = { Title(title = "Editar", navController = navController) }) {
        EditarBody()
    }
}

@Composable
fun EditarBody() {
    val db = FirebaseFirestore.getInstance()
    var name by remember { mutableStateOf("") }
    var categoriesExpanded by remember { mutableStateOf(false) }
    var checked by remember {
        mutableStateOf(false)
    }
    var estado by remember {
        mutableStateOf("")
    }


    var categoria by remember { mutableStateOf("") }
    val categorias = listOf("Analgésico",
        "Anestésico",
        "Antibiótico ",
        "Anticolinérgico ",
        "Anticonceptivo ",
        "Anticonvulsivo",
        "Antidepresivo",
        "Antidiabético",
        "Antiemético ",
        "Antihelmíntico ",
        "Antihipertensivo",
        "Antihistamínico ",
        "Antineoplásico ",
        "Antiinflamatorio ",
        "Antiparkinsoniano ",
        "Antimicótico ",
        "Antipirético",
        "Antipsicótico ",
        "Antitusivo ",
        "Antídoto ",
        "Broncodilatador ",
        "Cardiotónico ",
        "Citostático",
        "Hipnótico ",
        "Hormonoterápico",
        "Quimioterápico",
        "Relajante muscular ")
    var TextSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (categoriesExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    BackgroundImage()
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        // verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {

        TextInput(text = name, onValueChange = { name = it }, label = "Nombre")


        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates -> TextSize = coordinates.size.toSize() },
            label = { Text("Categoria") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { categoriesExpanded = !categoriesExpanded })
            }
        )

        DropdownMenu(
            expanded = categoriesExpanded,
            onDismissRequest = { categoriesExpanded = false },
        ) {
            categorias.forEach { label ->
                DropdownMenuItem(onClick = {
                    categoria = label
                    categoriesExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)) {
            Text(text = "Tóxico")
            Checkbox(checked = checked, onCheckedChange = { checked = !checked })
        }
        TextInput(text = estado, onValueChange = { estado = it }, label = "Estado")
        Spacer(modifier = Modifier.size(16.dp))
        ButtonDefault(text = "Editar") {


            db.collection("farmacos").document(name).update(mapOf(
                "nombre" to name,
                "categoria" to categoria,
                "toxico" to checked,
                "estado" to estado
            ))
            name=""
            categoria=""
            checked=false
            estado=""
        }
    }
}

@Preview
@Composable
fun EditarPreview() {
    val navController = rememberNavController()
    Anadir(navController)
}