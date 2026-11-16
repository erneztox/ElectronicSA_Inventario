package com.electronicsa;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona el inventario de la tienda Electronic SA.
 * Administra el catálogo de productos y las operaciones de consulta, filtrado y compra.
 */
public class Inventario {
    private List<Producto> listaProductos;

    public Inventario() {
        this.listaProductos = new ArrayList<>();
        cargarProductosIniciales();
    }

    /**
     * Carga un conjunto inicial de productos en las cuatro categorías requeridas:
     * Laptops, Smartphones, Cámaras Digitales y Televisores.
     */
    private void cargarProductosIniciales() {
        // Laptops
        listaProductos.add(new Producto("LAP-001", "Lenovo ThinkPad E14", "Laptops", 2000000.0, 5, "Pasillo A - Vitrina 1")); // Límite exacto: $2.000.000 (20%)
        listaProductos.add(new Producto("LAP-002", "ASUS Vivobook 15", "Laptops", 1450000.0, 2, "Pasillo A - Vitrina 2")); // Stock bajo (<= 3)

        // Smartphones
        listaProductos.add(new Producto("SM-001", "Samsung Galaxy S24", "Smartphones", 3100000.0, 8, "Pasillo B - Módulo 1"));
        listaProductos.add(new Producto("SM-002", "Xiaomi Redmi Note 13", "Smartphones", 450000.0, 12, "Pasillo B - Módulo 2")); // Menor a $500.000 (0%)

        // Cámaras Digitales
        listaProductos.add(new Producto("CAM-001", "Sony Alpha 7 IV", "Cámaras Digitales", 2400000.0, 4, "Pasillo C - Vitrina Central"));
        listaProductos.add(new Producto("CAM-002", "Canon EOS R50", "Cámaras Digitales", 980000.0, 0, "Pasillo C - Estante 3")); // Agotado (stock 0)

        // Televisores
        listaProductos.add(new Producto("TV-001", "LG OLED 55 Pulgadas 4K", "Televisores", 1000000.0, 6, "Pasillo D - Exhibición Principal")); // Límite exacto: $1.000.000 (15%)
        listaProductos.add(new Producto("TV-002", "Samsung Crystal UHD 50", "Televisores", 500000.0, 3, "Pasillo D - Exhibición 2")); // Límite exacto: $500.000 (10%) y Stock bajo
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    /**
     * Muestra todos los productos registrados en el inventario.
     */
    public void mostrarTodosLosProductos() {
        System.out.println("\n==========================================================================================================");
        System.out.println("                               CATÁLOGO GENERAL DE PRODUCTOS - ELECTRONIC SA                              ");
        System.out.println("==========================================================================================================");
        for (Producto p : listaProductos) {
            System.out.println(p);
        }
        System.out.println("==========================================================================================================");
        System.out.println("Total de referencias registradas: " + listaProductos.size());
    }

    /**
     * Busca un producto por su código identificador.
     * Retorna el objeto Producto si lo encuentra, o null si no existe.
     */
    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return p;
            }
        }
        return null;
    }

    /**
     * Consulta la disponibilidad y ubicación de un producto según su código.
     * 
     * DEMOSTRACIÓN DE ESTRUCTURAS CONDICIONALES:
     * - ESTRUCTURA 1 (if simple): Comprobación de condición especial de stock (alerta por stock bajo <= 3).
     * - ESTRUCTURA 2 (if-else): Distingue entre productos disponibles (stock > 0) y agotados (stock == 0).
     */
    public void consultarDisponibilidadYUbicacion(String codigo) {
        Producto producto = buscarPorCodigo(codigo);

        if (producto == null) {
            System.out.println("❌ No se encontró ningún producto con el código: " + codigo);
            return;
        }

        System.out.println("\n-------------------------------------------------------------");
        System.out.println("         CONSULTA DE DISPONIBILIDAD Y UBICACIÓN             ");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Código   : " + producto.getCodigo());
        System.out.println("Nombre   : " + producto.getNombre());
        System.out.println("Categoría: " + producto.getCategoria());
        System.out.println("Precio   : " + Producto.formatearPrecio(producto.getPrecio()));

        // =====================================================================
        // ESTRUCTURA 2: if-else
        // Distingue entre productos disponibles y productos agotados
        // =====================================================================
        if (producto.getCantidadDisponible() > 0) {
            System.out.println("Estado   : DISPONIBLE (Stock actual: " + producto.getCantidadDisponible() + " unidad/es)");
            System.out.println("Ubicación: " + producto.getUbicacion());
        } else {
            System.out.println("Estado   : AGOTADO (Sin stock en tienda)");
            System.out.println("Ubicación: " + producto.getUbicacion() + " (Espacio vacío - pendiente reposición)");
        }

        // =====================================================================
        // ESTRUCTURA 1: if simple
        // Comprueba condición de stock crítico: alerta si quedan 3 o menos unidades (pero mayor a 0)
        // =====================================================================
        if (producto.getCantidadDisponible() > 0 && producto.getCantidadDisponible() <= 3) {
            System.out.println("⚠️ ALERTA DE STOCK: ¡Quedan pocas unidades en inventario! Notificar a compras.");
        }

        System.out.println("-------------------------------------------------------------");
    }

    /**
     * Filtra y muestra los productos pertenecientes a una categoría específica.
     */
    public void filtrarPorCategoria(String categoria) {
        System.out.println("\n==========================================================================================================");
        System.out.println("                       PRODUCTOS EN LA CATEGORÍA: " + categoria.toUpperCase());
        System.out.println("==========================================================================================================");

        int contador = 0;
        for (Producto p : listaProductos) {
            if (p.getCategoria().equalsIgnoreCase(categoria.trim())) {
                System.out.println(p);
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No hay productos registrados en esta categoría.");
        } else {
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.println("Total encontrados en categoría " + categoria + ": " + contador);
        }
        System.out.println("==========================================================================================================");
    }

    /**
     * Determina el porcentaje de descuento aplicable según el importe total de la compra.
     * 
     * Reglas de negocio solicitadas:
     * - Desde $2.000.000 : 20%
     * - Desde $1.000.000 : 15%
     * - Desde $500.000   : 10%
     * - Menos de $500.000: sin descuento (0%)
     * 
     * DEMOSTRACIÓN DE ESTRUCTURA CONDICIONAL:
     * - ESTRUCTURA 3 (if-else-if): Evaluación en cascada de rangos de importe.
     */
    public double determinarPorcentajeDescuento(double subtotal) {
        double porcentaje;

        // =====================================================================
        // ESTRUCTURA 3: if-else-if
        // Determina descuentos según el importe de compra
        // =====================================================================
        if (subtotal >= 2000000.0) {
            porcentaje = 0.20; // 20% para compras desde $2.000.000
        } else if (subtotal >= 1000000.0) {
            porcentaje = 0.15; // 15% para compras desde $1.000.000
        } else if (subtotal >= 500000.0) {
            porcentaje = 0.10; // 10% para compras desde $500.000
        } else {
            porcentaje = 0.00; // Menos de $500.000: sin descuento
        }

        return porcentaje;
    }

    /**
     * Simula la compra de un producto, validando existencia de stock,
     * calculando subtotal, descuento aplicable y total a pagar.
     * 
     * DEMOSTRACIÓN DE ESTRUCTURAS CONDICIONALES:
     * - ESTRUCTURA 1 (if simple): Comprobación de condición de stock (cantidad solicitada excede stock).
     * - ESTRUCTURA 3 (if-else-if): Invocada internamente en determinarPorcentajeDescuento().
     */
    public boolean simularCompra(String codigo, int cantidadSolicitada) {
        Producto producto = buscarPorCodigo(codigo);

        if (producto == null) {
            System.out.println("❌ Error: No existe un producto con el código especificado.");
            return false;
        }

        if (cantidadSolicitada <= 0) {
            System.out.println("❌ Error: La cantidad a comprar debe ser mayor a cero.");
            return false;
        }

        // =====================================================================
        // ESTRUCTURA 1: if simple
        // Comprueba condición de stock: valida si la cantidad pedida supera el stock disponible
        // =====================================================================
        if (cantidadSolicitada > producto.getCantidadDisponible()) {
            System.out.println("❌ Operación rechazada: Stock insuficiente.");
            System.out.println("   Disponible: " + producto.getCantidadDisponible() + " unidad/es | Solicitado: " + cantidadSolicitada);
            return false;
        }

        // Cálculo de importes
        double subtotal = producto.getPrecio() * cantidadSolicitada;
        double porcentajeDescuento = determinarPorcentajeDescuento(subtotal);
        double valorDescuento = subtotal * porcentajeDescuento;
        double totalPagar = subtotal - valorDescuento;

        // Actualizar stock del producto
        producto.reducirStock(cantidadSolicitada);

        // Desglose de la compra
        System.out.println("\n=============================================================");
        System.out.println("                  RESUMEN DE COMPRA SIMULADA                 ");
        System.out.println("=============================================================");
        System.out.println("Producto         : " + producto.getNombre() + " (" + producto.getCodigo() + ")");
        System.out.println("Categoría        : " + producto.getCategoria());
        System.out.println("Precio unitario  : " + Producto.formatearPrecio(producto.getPrecio()));
        System.out.println("Cantidad pedida  : " + cantidadSolicitada + " unidad/es");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Subtotal         : " + Producto.formatearPrecio(subtotal));
        System.out.println("Descuento (" + (int)(porcentajeDescuento * 100) + "%) : -" + Producto.formatearPrecio(valorDescuento));
        System.out.println("TOTAL A PAGAR    : " + Producto.formatearPrecio(totalPagar));
        System.out.println("-------------------------------------------------------------");
        System.out.println("Stock restante   : " + producto.getCantidadDisponible() + " unidad/es");
        System.out.println("Ubicación retiro : " + producto.getUbicacion());

        // =====================================================================
        // ESTRUCTURA 1: if simple
        // Alerta posterior a la compra si el stock quedó en nivel crítico (<= 3 y > 0) o en cero
        // =====================================================================
        if (producto.getCantidadDisponible() == 0) {
            System.out.println("⚠️ AVISO: El producto se ha AGOTADO tras esta compra.");
        }

        System.out.println("=============================================================\n");
        return true;
    }
}
