package inventario;

import producto.Producto;

import java.io.*;

public class GenerarReporteInventario extends Inventario {


    // Reporte de Inventario

    public void generarReporte() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("reporte_inventario.txt"))) {
            // Encabezado del reporte
            bw.write("No.\tNombre\tCategoría\tPrecio\t\tCantidad\tTotal");
            bw.newLine();

            int valorTotal = 0;
            int numero = 1;
            listaProductos.clear();
            BufferedReader br = new BufferedReader(new FileReader("Inventario_productos.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\s*,\\s*");
                if (partes.length == 5) {
                    listaProductos.add(new Producto(
                            partes[0],
                            partes[1],
                            partes[2],
                            Integer.parseInt(partes[3]),
                            Integer.parseInt(partes[4])
                    ));
                }
            }

            for (int i = 0; i < listaProductos.size(); i++) {
                int totalProducto = listaProductos.get(i).getPrecio() * listaProductos.get(i).getCantidadDisponible();
                valorTotal += totalProducto;
                //formato para organizar el reporte

                bw.write(String.format("%-1s |\t%-1s \t%-1s \t%1s \t\t%1s \t%1s",
                        numero++,
                        listaProductos.get(i).getNombre(),
                        listaProductos.get(i).getCategoria(),
                        String.format("%8d", listaProductos.get(i).getPrecio()),
                        listaProductos.get(i).getCantidadDisponible(),
                        totalProducto));
                bw.newLine();
            }
            // Parte Inferior del Reporte (Calculo de valor Total)
            bw.newLine();
            bw.write("Valor total del inventario: " + "$ " + valorTotal);
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

}
