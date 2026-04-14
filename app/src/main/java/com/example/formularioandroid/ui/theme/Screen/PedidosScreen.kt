package com.example.formularioandroid.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PedidoScreen() {
    var cliente by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var producto by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    var cargando by remember { mutableStateOf(false) }
    var estado by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    // Validaciones correctas
    val nombreCorrecto = cliente.trim().length >= 3
    val telefonoCorrecto = telefono.all { it.isDigit() } && telefono.length >= 8
    val direccionCorrecta = direccion.trim().isNotEmpty()
    val productoCorrecto = producto.trim().isNotEmpty()
    val cantidadInt = cantidad.toIntOrNull() ?: 0
    val cantidadCorrecta = cantidadInt > 0

    // Mostrar error solo si el usuario escribió algo y está mal
    val mostrarErrorNombre = cliente.isNotEmpty() && !nombreCorrecto
    val mostrarErrorTelefono = telefono.isNotEmpty() && !telefonoCorrecto
    val mostrarErrorDireccion = direccion.isNotEmpty() && !direccionCorrecta
    val mostrarErrorProducto = producto.isNotEmpty() && !productoCorrecto
    val mostrarErrorCantidad = cantidad.isNotEmpty() && !cantidadCorrecta

    val sePuedeEnviar = nombreCorrecto &&
            telefonoCorrecto &&
            direccionCorrecta &&
            productoCorrecto &&
            cantidadCorrecta &&
            !cargando

    val coloresCampo = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.Gray,
        cursorColor = Color.White,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.LightGray
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Formulario de Pedido",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )

        OutlinedTextField(
            value = cliente,
            onValueChange = {
                cliente = it
                estado = ""
            },
            label = { Text("Nombre del cliente") },
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarErrorNombre,
            enabled = !cargando,
            colors = coloresCampo
        )

        if (mostrarErrorNombre) {
            Text(
                text = "Debe tener al menos 3 caracteres",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = telefono,
            onValueChange = {
                telefono = it.filter { caracter -> caracter.isDigit() }
                estado = ""
            },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = mostrarErrorTelefono,
            enabled = !cargando,
            colors = coloresCampo
        )

        if (mostrarErrorTelefono) {
            Text(
                text = "Solo números y mínimo 8 dígitos",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = direccion,
            onValueChange = {
                direccion = it
                estado = ""
            },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarErrorDireccion,
            enabled = !cargando,
            colors = coloresCampo
        )

        if (mostrarErrorDireccion) {
            Text(
                text = "La dirección es obligatoria",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = producto,
            onValueChange = {
                producto = it
                estado = ""
            },
            label = { Text("Producto") },
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarErrorProducto,
            enabled = !cargando,
            colors = coloresCampo
        )

        if (mostrarErrorProducto) {
            Text(
                text = "El producto es obligatorio",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = cantidad,
            onValueChange = {
                cantidad = it.filter { caracter -> caracter.isDigit() }
                estado = ""
            },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = mostrarErrorCantidad,
            enabled = !cargando,
            colors = coloresCampo
        )

        if (mostrarErrorCantidad) {
            Text(
                text = "La cantidad debe ser mayor que cero",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = notas,
            onValueChange = {
                notas = it
                estado = ""
            },
            label = { Text("Notas adicionales (opcional)") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !cargando,
            colors = coloresCampo
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                estado = "Enviando pedido..."
                cargando = true

                scope.launch {
                    delay(2000)

                    cargando = false

                    cliente = ""
                    telefono = ""
                    direccion = ""
                    producto = ""
                    cantidad = ""
                    notas = ""

                    estado = "Pedido enviado con éxito"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = sePuedeEnviar
        ) {
            Text("Enviar pedido")
        }

        TextButton(
            onClick = {
                cliente = ""
                telefono = ""
                direccion = ""
                producto = ""
                cantidad = ""
                notas = ""
                estado = ""
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !cargando
        ) {
            Text("Limpiar formulario")
        }

        if (cargando) {
            CircularProgressIndicator()
        }

        if (estado.isNotEmpty()) {
            Text(
                text = estado,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}