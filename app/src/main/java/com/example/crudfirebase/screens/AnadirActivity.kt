package com.example.crudfirebase.screens

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.crudfirebase.composables.BackgroundImage
import com.example.crudfirebase.composables.ButtonDefault
import com.example.crudfirebase.composables.TextInput
import com.example.crudfirebase.composables.Title
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

@Composable
fun Anadir(navController: NavController) {
    Scaffold(topBar = { Title(title = "Añadir", navController = navController) }) {
        AnadirBody()
    }
}

@Composable
fun AnadirBody() {
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
    var textSize by remember { mutableStateOf(Size.Zero) }
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

        Spacer(modifier = Modifier.size(8.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates -> textSize = coordinates.size.toSize() },

            label = { Text("Categoria") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { categoriesExpanded = !categoriesExpanded })
            }
        )

        DropdownMenu(
            expanded = categoriesExpanded,
            onDismissRequest = { categoriesExpanded = false },
            //modifier=Modifier.width(LocalDensity.current)(width.toDp())
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
        ButtonDefault(text = "Añadir") {
            val data = hashMapOf(
                "nombre" to name,
                "categoria" to categoria,
                "toxico" to checked,
                "estado" to estado
            )
            Log.i("HashMap", data.toString())
            db.collection("farmacos").document(name).set(data, SetOptions.merge())

            name=""
            categoria=""
            checked=false
            estado=""
        }
    }
}

@Preview
@Composable
fun AnadirPreview() {
    val navController = rememberNavController()
    Anadir(navController)
}