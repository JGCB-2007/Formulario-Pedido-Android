# 📱 Formulario de Pedido - Android (Jetpack Compose)

## 🌐 Descripción del Proyecto

Esta aplicación móvil fue desarrollada en Android utilizando Kotlin y Jetpack Compose, y permite al usuario completar un formulario de pedido de comida con validaciones en tiempo real, simulación de envío y retroalimentación visual.

El objetivo principal es aplicar conceptos básicos de desarrollo móvil, manejo de estados y uso de coroutines en una interfaz moderna.

## 🚀 Funcionalidades

📋Formulario de pedido con los siguientes campos:
1. Nombre del cliente
2. Teléfono
3. Dirección
4. Producto
5. Cantidad
6. Notas adicionales (opcional)

## 🧩  Validaciones en tiempo real:
1. Nombre del cliente
2. Teléfono solo números y mínimo 8 dígitos
3. Dirección obligatoria
4. Producto obligatorio
5. Cantidad mayor que 0

## Funcionalidad
<table>
	<tr>
		<td><b>🔘 Botón de envío</b></td>
		<td>Solo se habilita cuando todo el formulario es válido</td>
	</tr>
	<tr>
		<td><b>📊 Simulación de envío</b></td>
		<td>Uso de Coroutines para simular un proceso con delay</td>
	</tr>
	<tr>
		<td><b>⏳ Indicador de carga</b></td>
		<td>Muestra un CircularProgressIndicator mientras se envía el pedido</td>
	</tr>
 	<tr>
		<td><b>📢Mensaje final</b></td>
		<td>Confirmación cuando el pedido es enviado correctamente</td>
	</tr>
 <tr>
		<td><b>🧹 Limpieza de formulario</b></td>
		<td>Botón para reiniciar todos los campos</td>
	</tr>
</table>

## 🛠️Tecnologías Utilizadas

1. Kotlin
2. Jetpack Compose
3. Material 3
4. Coroutines

## 🧠 Lógica del Sistema
La pantalla principal`pedidos screen`maneja todos los estados del formulario usando `remembre` y `mutablestateof`.

### 📍 Ejemplo de validaciones implementadas

```Kotlin
val nombreCorrecto = cliente.trim().length >= 3
val telefonoCorrecto = telefono.all { it.isDigit() } && telefono.length >= 8
val direccionCorrecta = direccion.trim().isNotEmpty()
val cantidadCorrecta = (cantidad.toIntOrNull() ?: 0) > 0
```

### 📥 El botón de envío se habilita solo si todo es válido:

```Kotlin
val sePuedeEnviar = nombreCorrecto &&
    telefonoCorrecto &&
    direccionCorrecta &&
    productoCorrecto &&
    cantidadCorrecta &&
    !cargando
```

### 🔄 Simulación de Envío

```Kotlin
scope.launch {
    delay(2000)
    cargando = false
    estado = "Pedido enviado con éxito"
}
```

## 🎨 Interfaz de Usuario
- Uso de `OutlinedTextField` para los campos
- Mensajes de error dinámicos
- Botones de acción (Enviar / Limpiar)
- Indicador de carga
- Diseño organizado con `Column` y `Spacer`

## 📋 Requisitos del Proyecto
- No uso de arquitecturas complejas
- No uso de librerías externas
- Implementación simple y funcional
- Uso de validaciones y UI reactiva

##  📌 Autor
- José Cano
## 💡 Nota
Este proyecto fue desarrollado con fines académicos para reforzar conceptos de desarrollo en Android moderno utilizando Jetpack Compose.
