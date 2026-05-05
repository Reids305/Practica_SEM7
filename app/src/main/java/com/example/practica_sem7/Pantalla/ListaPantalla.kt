package com.example.practica_sem7.Pantalla

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practica_sem7.ViewModel.ElementoViewModel

@Composable
fun ListaPantalla(viewModel: ElementoViewModel, onNavigateToForm: () -> Unit) {
    // Observamos los datos del ViewModel
    val elementos by viewModel.elementos.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToForm) {
                Text("+") // Botón para ir a registrar
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)
        ) {
            items(elementos) { elemento ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Nombre: ${elemento.nombre}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Categoría: ${elemento.categoria}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}