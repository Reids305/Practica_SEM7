package com.example.practica_sem7.ViewModel

import androidx.lifecycle.ViewModel
import com.example.practica_sem7.Data.Elemento
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ElementoViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val coleccion = db.collection("elementos")

    // Estado que Compose observará para actualizar la UI
    private val _elementos = MutableStateFlow<List<Elemento>>(emptyList())
    val elementos: StateFlow<List<Elemento>> = _elementos

    init {
        obtenerElementos()
    }

    // Leer datos en tiempo real
    private fun obtenerElementos() {
        coleccion.addSnapshotListener { snapshot, error ->
            if (error != null) return@addSnapshotListener

            if (snapshot != null) {
                val lista = snapshot.documents.map { doc ->
                    Elemento(
                        id = doc.id,
                        nombre = doc.getString("nombre") ?: "",
                        categoria = doc.getString("categoria") ?: ""
                    )
                }
                _elementos.value = lista
            }
        }
    }

    // Guardar un nuevo elemento
    fun agregarElemento(nombre: String, categoria: String, onSuccess: () -> Unit) {
        val nuevoElemento = hashMapOf(
            "nombre" to nombre,
            "categoria" to categoria
        )

        coleccion.add(nuevoElemento)
            .addOnSuccessListener { onSuccess() }
    }
}