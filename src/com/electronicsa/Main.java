package com.electronicsa;

import java.util.Scanner;

/**
 * Clase principal que gestiona la interfaz por consola de la tienda Electronic SA.
 * Permite al usuario interactuar con el sistema de inventario mediante menús interactivos.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();
        boolean salir = false;

        System.out.println("*************************************************************");
        System.out.println("   BIENVENIDO AL SISTEMA DE GESTIÓN DE INVENTARIO            ");
        System.out.println("                   ELECTRONIC SA                             ");
        System.out.println("*************************************************************");

        while (!salir) {
            mostrarMenuPrincipal();
            System.out.print("Seleccione una opción (1-6): ");
            String entrada = scanner.nextLine().trim();

            int opcion;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número entre 1 y 6.\n");
                continue;
            }

            // =====================================================================
            // ESTRUCTURA 4: switch-case (Control del Menú Principal)
            // =====================================================================
            switch (opcion) {
                case 1:
                    inventario.mostrarTodosLosProductos();
                    break;

                case 2:
                    ejecutarBusquedaPorCodigo(scanner, inventario);
                    break;

                case 3:
                    ejecutarConsultaDisponibilidad(scanner, inventario);
                    break;

                case 4:
                    ejecutarFiltradoPorCategoria(scanner, inventario);
                    break;

                case 5:
                    ejecutarSimulacionCompra(scanner, inventario);
                    break;

                case 6:
                    salir = true;
                    System.out.println("\nCerrando sesión. ¡Gracias por usar el sistema de Electronic SA!");
                    break;

                default:
                    System.out.println("❌ Opción inválida. Ingrese un valor del 1 al 6.\n");
                    break;
            }

            if (!salir) {
                System.out.print("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Despliega las opciones del menú principal.
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n==================== MENÚ PRINCIPAL ====================");
        System.out.println("1. Mostrar todos los productos");
        System.out.println("2. Buscar producto por código");
        System.out.println("3. Consultar disponibilidad y ubicación");
        System.out.println("4. Filtrar productos por categoría");
        System.out.println("5. Simular compra y calcular descuentos");
        System.out.println("6. Salir del sistema");
        System.out.println("========================================================");
    }

    /**
     * Busca un producto por su código y muestra sus datos generales.
     */
    private static void ejecutarBusquedaPorCodigo(Scanner scanner, Inventario inventario) {
        System.out.print("\nIngrese el código del producto a buscar (ej. LAP-001, SM-001): ");
        String codigo = scanner.nextLine().trim();

        Producto producto = inventario.buscarPorCodigo(codigo);
        if (producto != null) {
            System.out.println("\n✅ Producto encontrado:");
            System.out.println(producto);
        } else {
            System.out.println("❌ No se encontró ningún producto con el código: " + codigo);
        }
    }

    /**
     * Consulta el estado de stock y ubicación en tienda de un producto.
     */
    private static void ejecutarConsultaDisponibilidad(Scanner scanner, Inventario inventario) {
        System.out.print("\nIngrese el código del producto para consultar disponibilidad y ubicación: ");
        String codigo = scanner.nextLine().trim();
        inventario.consultarDisponibilidadYUbicacion(codigo);
    }

    /**
     * Permite al usuario seleccionar una categoría usando switch-case y filtra los productos.
     * 
     * DEMOSTRACIÓN DE ESTRUCTURA CONDICIONAL:
     * - ESTRUCTURA 4 (switch-case): Selección directa de las cuatro categorías de la tienda.
     */
    private static void ejecutarFiltradoPorCategoria(Scanner scanner, Inventario inventario) {
        System.out.println("\n--------------- CATEGORÍAS DISPONIBLES ---------------");
        System.out.println("1. Laptops");
        System.out.println("2. Smartphones");
        System.out.println("3. Cámaras Digitales");
        System.out.println("4. Televisores");
        System.out.println("------------------------------------------------------");
        System.out.print("Seleccione el número de la categoría (1-4): ");

        String entrada = scanner.nextLine().trim();
        int opcionCat;
        try {
            opcionCat = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada no válida. Debe ser un número.");
            return;
        }

        String categoriaSeleccionada = "";

        // =====================================================================
        // ESTRUCTURA 4: switch-case
        // Selecciona la categoría de productos según la opción elegida
        // =====================================================================
        switch (opcionCat) {
            case 1:
                categoriaSeleccionada = "Laptops";
                break;
            case 2:
                categoriaSeleccionada = "Smartphones";
                break;
            case 3:
                categoriaSeleccionada = "Cámaras Digitales";
                break;
            case 4:
                categoriaSeleccionada = "Televisores";
                break;
            default:
                System.out.println("❌ Opción de categoría no reconocida. Seleccione entre 1 y 4.");
                return;
        }

        inventario.filtrarPorCategoria(categoriaSeleccionada);
    }

    /**
     * Simula la compra de un producto y calcula los descuentos correspondientes.
     */
    private static void ejecutarSimulacionCompra(Scanner scanner, Inventario inventario) {
        System.out.print("\nIngrese el código del producto a comprar: ");
        String codigo = scanner.nextLine().trim();

        Producto producto = inventario.buscarPorCodigo(codigo);
        if (producto == null) {
            System.out.println("❌ No se encontró ningún producto con el código: " + codigo);
            return;
        }

        System.out.println("Producto seleccionado: " + producto.getNombre());
        System.out.println("Precio unitario     : " + Producto.formatearPrecio(producto.getPrecio()));
        System.out.println("Stock disponible    : " + producto.getCantidadDisponible() + " unidades");

        System.out.print("Ingrese la cantidad de unidades que desea comprar: ");
        String entradaCantidad = scanner.nextLine().trim();
        int cantidad;
        try {
            cantidad = Integer.parseInt(entradaCantidad);
        } catch (NumberFormatException e) {
            System.out.println("❌ Cantidad inválida. Debe ser un número entero.");
            return;
        }

        inventario.simularCompra(codigo, cantidad);
    }
}
