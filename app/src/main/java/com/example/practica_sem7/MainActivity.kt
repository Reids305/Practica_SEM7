package com.example.practica_sem7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica_sem7.Pantalla.FormularioPantalla
import com.example.practica_sem7.Pantalla.ListaPantalla
import com.example.practica_sem7.ViewModel.ElementoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val viewModel: ElementoViewModel = viewModel()

                // Gestor de navegación
                NavHost(navController = navController, startDestination = "lista") {

                    composable("lista") {
                        ListaPantalla(
                            viewModel = viewModel,
                            onNavigateToForm = { navController.navigate("formulario") }
                        )
                    }

                    composable("formulario") {
                        FormularioPantalla(
                            viewModel = viewModel,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }

                }
            }
        }
    }
}