package com.electronicsa;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Clase que representa un producto del inventario de Electronic SA.
 * Modela los datos esenciales y comportamiento básico de un producto.
 */
public class Producto {
    // Atributos privados (Encapsulamiento)
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidadDisponible;
    private String ubicacion;

    /**
     * Constructor con todos los campos requeridos.
     */
    public Producto(String codigo, String nombre, String categoria, double precio, int cantidadDisponible, String ubicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.ubicacion = ubicacion;
    }

    // Métodos Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Reduce la cantidad en stock tras una compra.
     */
    public void reducirStock(int cantidad) {
        this.cantidadDisponible -= cantidad;
    }

    /**
     * Formatea un valor monetario en formato legible ($#.###).
     */
    public static String formatearPrecio(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL"));
        return formato.format(valor);
    }

    /**
     * Representación textual de la ficha técnica del producto.
     */
    @Override
    public String toString() {
        return String.format("[%s] %-28s | Categoría: %-17s | Precio: %-12s | Stock: %2d | Ubicación: %s",
                codigo, nombre, categoria, formatearPrecio(precio), cantidadDisponible, ubicacion);
    }
}
