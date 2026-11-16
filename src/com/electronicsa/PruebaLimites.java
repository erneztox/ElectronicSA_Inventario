package com.electronicsa;

/**
 * Clase de pruebas unitarias/límite para verificar rigurosamente los tramos
 * y límites exactos de descuento ($500.000, $1.000.000 y $2.000.000).
 */
public class PruebaLimites {

    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        double[] casosPrueba = {
            0.0,            // Sin compra
            450000.0,       // Caso típico bajo $500.000
            499999.0,       // Límite superior sin descuento ($499.999)
            500000.0,       // Límite exacto inicio 10% ($500.000)
            750000.0,       // Caso medio 10%
            999999.0,       // Límite superior tramo 10% ($999.999)
            1000000.0,      // Límite exacto inicio 15% ($1.000.000)
            1500000.0,      // Caso medio 15%
            1999999.0,      // Límite superior tramo 15% ($1.999.999)
            2000000.0,      // Límite exacto inicio 20% ($2.000.000)
            3500000.0       // Caso superior a $2.000.000
        };

        System.out.println("================================================================================");
        System.out.println("          TABLA DE VALIDACIÓN DE LÍMITES EXACTOS DE DESCUENTO                    ");
        System.out.println("================================================================================");
        System.out.printf("%-18s | %-12s | %-20s | %-10s%n", "Importe Compra", "Descuento", "Regla Aplicada", "Resultado");
        System.out.println("--------------------------------------------------------------------------------");

        for (double importe : casosPrueba) {
            double descuento = inventario.determinarPorcentajeDescuento(importe);
            int porcentajeInt = (int) (descuento * 100);

            String reglaEsperada;
            int porcentajeEsperado;
            if (importe >= 2000000.0) {
                reglaEsperada = "Desde $2.000.000";
                porcentajeEsperado = 20;
            } else if (importe >= 1000000.0) {
                reglaEsperada = "Desde $1.000.000";
                porcentajeEsperado = 15;
            } else if (importe >= 500000.0) {
                reglaEsperada = "Desde $500.000";
                porcentajeEsperado = 10;
            } else {
                reglaEsperada = "Menos de $500.000";
                porcentajeEsperado = 0;
            }

            boolean pasa = (porcentajeInt == porcentajeEsperado);

            System.out.printf("%-18s | %10d%% | %-20s | %s%n",
                    Producto.formatearPrecio(importe),
                    porcentajeInt,
                    reglaEsperada,
                    pasa ? "✅ PASÓ" : "❌ FALLÓ");
        }
        System.out.println("================================================================================\n");
    }
}
