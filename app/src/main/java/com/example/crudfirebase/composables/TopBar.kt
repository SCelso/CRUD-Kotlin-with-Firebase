package com.example.crudfirebase.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Title(title: String, navController: NavController, backbutton: Boolean = true) {
    TopAppBar (
            backgroundColor = MaterialTheme.colors.primary
            ) {

        if (backbutton) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable { navController.popBackStack() })
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
            fontSize = 25.sp
        )

    }


}

