package com.example.crudfirebase.composables

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.crudfirebase.modelo.ExpandableCardItem
import com.example.crudfirebase.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore
import io.grpc.Context

@Composable
fun ExpandableCardRow(

    expandableCardItem: ExpandableCardItem,editOnClick:()->Unit, deleteOnClick:()->Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card {

        Column(modifier = Modifier.animateContentSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                    Column {
                        Text(text = expandableCardItem.nombre, style = MaterialTheme.typography.h6)
                        Text(
                            text = expandableCardItem.categoria,
                            style = MaterialTheme.typography.body1
                        )
                    }
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    ButtonIcon(icon = Icons.Default.Build, onClick = editOnClick)
                    Spacer(modifier = Modifier.width(16.dp))
                    ButtonIcon(icon = Icons.Default.Delete, onClick =deleteOnClick )
                    Spacer(modifier = Modifier.width(16.dp))
                    ExpandableCardIcon(
                        expanded = expanded,
                        onIconClick = { expanded = !expanded },
                    )

                }




            }

            if (expanded)
                Divider(thickness = Dp.Hairline, modifier = Modifier.padding(horizontal = 16.dp))
            Column(  modifier = Modifier
                .height(
                    if (expanded) {
                        56.dp
                    } else {
                        0.dp
                    }
                )
                .padding(start = 16.dp)) {
                Text(
                    text = "estado: ${expandableCardItem.detalles.estado}",

                )
                Text(
                    text = "tóxico: ${if(expandableCardItem.detalles.toxico){"Si"}else{"No"}}",

                    )
            }


        }
            }

    }


@Composable
fun ExpandableCardIcon(

    expanded: Boolean,
    onIconClick: () -> Unit,

) {
    IconButton(onClick = onIconClick) {
        Icon(
            Icons.Filled.KeyboardArrowDown,
            "Icono para expandir tarjeta",
            Modifier.rotate(
                if (expanded)
                    180f
                else
                    360f
            )
        )
    }
}
