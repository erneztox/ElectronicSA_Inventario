# Sistema de Gestión de Inventario - Electronic SA

Proyecto desarrollado para la actividad práctica de **Programación Orientada a Objetos I**.

Consiste en una aplicación de consola en **Java 21** diseñada para gestionar el inventario de la tienda de tecnología **Electronic SA**, administrando cuatro categorías principales de productos: **Laptops**, **Smartphones**, **Cámaras Digitales** y **Televisores**.

---

## 📋 Características y Funcionalidades

1. **Mostrar todos los productos**: Despliega el catálogo completo con código, nombre, categoría, precio en pesos chilenos (`es-CL`), stock disponible y ubicación física en tienda.
2. **Buscar productos por código**: Localiza referencias de forma rápida mediante búsqueda exacta (ej. `LAP-001`, `SM-001`).
3. **Consultar disponibilidad y ubicación**: Distingue entre productos disponibles y agotados, generando alertas en caso de stock crítico ($\le 3$ unidades).
4. **Filtrar productos por categoría**: Permite seleccionar interactivamente una categoría y visualizar únicamente sus artículos asociados.
5. **Simular compras y calcular descuentos**: Valida stock suficiente, calcula el subtotal y aplica descuentos escalonados según el importe total de compra, actualizando el stock disponible.

---

## 🎯 Demostración de Estructuras Condicionales

El sistema implementa de forma explícita las cuatro estructuras condicionales requeridas:

| Estructura | Archivo y Método | Caso de Uso en el Sistema |
|---|---|---|
| **`if` simple** | `Inventario.java` (`simularCompra`) | **Comprobación de condiciones de stock**: Valida si la cantidad solicitada excede la existencia disponible y emite alertas de stock bajo ($\le 3$ unidades). |
| **`if-else`** | `Inventario.java` (`consultarDisponibilidadYUbicacion`) | **Distinguir disponibilidad**: Separa claramente el flujo entre productos disponibles (`stock > 0`) y productos agotados (`stock == 0`). |
| **`if-else-if`** | `Inventario.java` (`determinarPorcentajeDescuento`) | **Cálculo de descuentos escalonados**: Evalúa en cascada el importe total de compra según los umbrales de negocio establecidos. |
| **`switch-case`** | `Main.java` (`ejecutarFiltradoPorCategoria` y menú principal) | **Selección de categorías**: Direcciona la categoría seleccionada por el usuario (1 a 4) y la navegación del menú por consola. |

---

## 💰 Reglas de Descuentos y Validación de Límites

El cálculo de descuentos por importe de compra se rige por las siguientes reglas en pesos chilenos (CLP):

| Rango de Compra | Descuento | Ejemplo de Validación de Límite |
|---|---|---|
| Menos de **$500.000** | **0%** (Sin descuento) | $450.000 $\to$ 0% \| $499.999 $\to$ 0% |
| Desde **$500.000** | **10%** | **$500.000 (límite exacto) $\to$ 10%** \| $999.999 $\to$ 10% |
| Desde **$1.000.000** | **15%** | **$1.000.000 (límite exacto) $\to$ 15%** \| $1.999.999 $\to$ 15% |
| Desde **$2.000.000** | **20%** | **$2.000.000 (límite exacto) $\to$ 20%** \| $3.500.000 $\to$ 20% |

> **Formato Monetario**: Se utiliza `Locale.forLanguageTag("es-CL")` para la correcta representación de moneda chilena (`$500.000`, sin decimales).

---

## 🏗️ Arquitectura y Clases

- **`com.electronicsa.Producto`**: Representa la entidad del producto. Aplica principios de encapsulamiento con atributos privados, métodos de acceso (getters/setters), reducción de stock y formateo monetario regional.
- **`com.electronicsa.Inventario`**: Administra la lista de productos y la lógica de negocio (búsquedas, filtros, cálculo de descuentos y actualización de existencias).
- **`com.electronicsa.Main`**: Clase ejecutable con la interfaz de consola interactiva basada en menús.
- **`com.electronicsa.PruebaLimites`**: Clase auxiliar para verificación automatizada de los límites exactos de descuento.

---

## 🚀 Compilación y Ejecución

### Requisitos
- **Java SE 21** o superior.
- Eclipse IDE (opcional) o consola de comandos.

### Desde Consola / Terminal
```bash
# 1. Clonar el repositorio
git clone https://github.com/erneztox/ElectronicSA_Inventario.git
cd ElectronicSA_Inventario

# 2. Compilar los archivos fuente
javac -d bin src/com/electronicsa/*.java

# 3. Ejecutar la aplicación principal
java -cp bin com.electronicsa.Main

# 4. (Opcional) Ejecutar la verificación de límites de descuento
java -cp bin com.electronicsa.PruebaLimites
```

---

## 🧪 Resultados de Pruebas

### 1. Catálogo General
```text
==========================================================================================================
                               CATÁLOGO GENERAL DE PRODUCTOS - ELECTRONIC SA                              
==========================================================================================================
[LAP-001] Lenovo ThinkPad E14          | Categoría: Laptops           | Precio: $2.000.000   | Stock:  5 | Ubicación: Pasillo A - Vitrina 1
[LAP-002] ASUS Vivobook 15             | Categoría: Laptops           | Precio: $1.450.000   | Stock:  2 | Ubicación: Pasillo A - Vitrina 2
[SM-001] Samsung Galaxy S24           | Categoría: Smartphones       | Precio: $3.100.000   | Stock:  8 | Ubicación: Pasillo B - Módulo 1
[SM-002] Xiaomi Redmi Note 13         | Categoría: Smartphones       | Precio: $450.000     | Stock: 12 | Ubicación: Pasillo B - Módulo 2
[CAM-001] Sony Alpha 7 IV              | Categoría: Cámaras Digitales | Precio: $2.400.000   | Stock:  4 | Ubicación: Pasillo C - Vitrina Central
[CAM-002] Canon EOS R50                | Categoría: Cámaras Digitales | Precio: $980.000     | Stock:  0 | Ubicación: Pasillo C - Estante 3
[TV-001] LG OLED 55 Pulgadas 4K       | Categoría: Televisores       | Precio: $1.000.000   | Stock:  6 | Ubicación: Pasillo D - Exhibición Principal
[TV-002] Samsung Crystal UHD 50       | Categoría: Televisores       | Precio: $500.000     | Stock:  3 | Ubicación: Pasillo D - Exhibición 2
==========================================================================================================
```

### 2. Comprobación de Límites Exactos de Descuento
```text
================================================================================
          TABLA DE VALIDACIÓN DE LÍMITES EXACTOS DE DESCUENTO                    
================================================================================
Importe Compra     | Descuento    | Regla Aplicada       | Resultado 
--------------------------------------------------------------------------------
$0                 |          0% | Menos de $500.000    | ✅ PASÓ
$450.000           |          0% | Menos de $500.000    | ✅ PASÓ
$499.999           |          0% | Menos de $500.000    | ✅ PASÓ
$500.000           |         10% | Desde $500.000       | ✅ PASÓ
$750.000           |         10% | Desde $500.000       | ✅ PASÓ
$999.999           |         10% | Desde $500.000       | ✅ PASÓ
$1.000.000         |         15% | Desde $1.000.000     | ✅ PASÓ
$1.500.000         |         15% | Desde $1.000.000     | ✅ PASÓ
$1.999.999         |         15% | Desde $1.000.000     | ✅ PASÓ
$2.000.000         |         20% | Desde $2.000.000     | ✅ PASÓ
$3.500.000         |         20% | Desde $2.000.000     | ✅ PASÓ
================================================================================
```
