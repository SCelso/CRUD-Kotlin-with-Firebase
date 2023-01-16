package com.example.crudfirebase.composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonDefault(text: String, onClick: () -> Unit) {

    Button(
        onClick = onClick, modifier = Modifier
            .width(350.dp)
            .height(50.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            fontSize = 25.sp
        )
    }
}

@Composable
fun ButtonIcon(icon: ImageVector, onClick: () -> Unit){
    Button(
        onClick = onClick, modifier = Modifier.size(48.dp),
        colors = ButtonDefaults.textButtonColors()
    ) {
       Icon(imageVector = icon,contentDescription = "")
    }
}