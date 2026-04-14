#  Formulario de Pedido - Android (Jetpack Compose)

📌 Descripción del Proyecto

Esta aplicación móvil fue desarrollada en Android utilizando Kotlin y Jetpack Compose, y permite al usuario completar un formulario de pedido de comida con validaciones en tiempo real, simulación de envío y retroalimentación visual.

El objetivo principal es aplicar conceptos básicos de desarrollo móvil, manejo de estados y uso de coroutines en una interfaz moderna.

📌 Funcionalidades
Formulario de pedido con los siguientes campos:
Nombre del cliente
Teléfono
Dirección
Producto
Cantidad
Notas adicionales (opcional)

📌 Validaciones en tiempo real:
Nombre con mínimo 3 caracteres
Teléfono solo números y mínimo 8 dígitos
Dirección obligatoria
Producto obligatorio
Cantidad mayor que 0

📌Botón de envío:
Solo se habilita cuando todo el formulario es válido
📌Simulación de envío:
Uso de Coroutines para simular un proceso con delay
📌Indicador de carga:
Muestra un CircularProgressIndicator mientras se envía el pedido
📌Mensaje final:
Confirmación cuando el pedido es enviado correctamente
📌Limpieza de formulario:
Botón para reiniciar todos los campos

📌 Tecnologías Utilizadas
Kotlin
Jetpack Compose
Material 3
Coroutines

📌 Lógica del Sistema

La pantalla principal PedidoScreen maneja todos los estados del formulario usando remember y mutableStateOf.

Ejemplo de validaciones implementadas:

val nombreCorrecto = cliente.trim().length >= 3
val telefonoCorrecto = telefono.all { it.isDigit() } && telefono.length >= 8
val direccionCorrecta = direccion.trim().isNotEmpty()
val cantidadCorrecta = (cantidad.toIntOrNull() ?: 0) > 0

El botón de envío se habilita solo si todo es válido:

val sePuedeEnviar = nombreCorrecto &&
telefonoCorrecto &&
direccionCorrecta &&
productoCorrecto &&
cantidadCorrecta &&
!cargando

 Simulación de Envío

Se utiliza una coroutine para simular el proceso de envío:

scope.launch {
delay(2000)
cargando = false
estado = "Pedido enviado con éxito"
}

Esto permite mostrar un estado de carga y luego un mensaje de confirmación.

 

📌 Autor
José (Ingeniería en Sistemas)