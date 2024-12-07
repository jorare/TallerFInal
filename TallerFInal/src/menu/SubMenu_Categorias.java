package menu;

import inventario.Inventario;
import producto.Producto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SubMenu_Categorias {

    List<Producto> productos = new ArrayList<>();
    private static final String archivoInventario = "Inventario_productos.txt";

    public void mostrarSubMenuCategorias(int tipo) {
        // 1. Extraer del archivo todas la categorias
        Set<String> categorias = new HashSet<>();
        for (Producto producto : productos) {
            categorias.add(producto.getCategoria());
        }

        Set<String> ids = new HashSet<>();
        for (Producto producto : productos) {
            ids.add(producto.getId());
        }

        // 2. Presentar el menú con las categorias que existen
        Scanner scanner = new Scanner(System.in);

        switch (tipo) {
            case 1 -> {
                int opcion;
                System.out.println("*********************************************");
                System.out.println("----CATEGORIAS----");
                int i = 1;
                for (String categoria : categorias) {
                    System.out.println(i + "." + categoria);
                    i++;
                }
                System.out.print("Ingrese el nombre de la categoria: ");
                String opcion_1 = scanner.nextLine();
                List<String> productosEncontrados = buscarPorCategoria(opcion_1);

                if(productosEncontrados.size()>0){
                    System.out.println("Productos en la categoría: " + opcion_1 );
                    for (String producto : productosEncontrados) {
                        System.out.println(producto);
                    }
                }else
                    System.out.println("No existen productos en la categoria "+opcion_1+".");
                System.out.println("*********************************************");

          }
            case 2 -> {
                Scanner scn = new Scanner(System.in);
                System.out.print("Ingrese el nombre del producto ");
                String palabra = scn.nextLine();
                List<String> productosEncontrados = buscarPorCategoria(palabra);

                if(productosEncontrados.size()>0){
                    System.out.println("Productos con el nombre: " + palabra);
                    for (String producto : productosEncontrados) {
                        System.out.println(producto);
                    }
                }else
                    System.out.println("No existen productos con el nombre "+palabra+".");
                System.out.println("*********************************************");

            }
            case 3 -> {
                int idProducto;
                System.out.print("Ingrese el id del producto ");
                idProducto = scanner.nextInt();
                int i = 1;
                boolean encontrado = false;
                for (String id : ids) {
                    String idEntras= String.valueOf(idProducto);
                    if(idEntras.equals(id))
                    {
                        List<String> productosEncontrados = buscarPorCategoria(String.valueOf(idProducto));
                        if(productosEncontrados.size()>0){
                            System.out.println("Productos con el id: " + idProducto );
                            for (String producto : productosEncontrados) {
                                System.out.println(producto);
                                encontrado= true;
                            }
                        }else
                            System.out.println("No existen productos con el id "+idProducto+".");
                    }
                    i++;
                }
                if (encontrado==false)
                    System.out.println("No existen productos con el id "+idProducto+".");
                System.out.println("*********************************************");
            }
            default -> System.out.println("Opción inválida.");
        }
    }

    public List<String> buscarPorCategoria(String palabra) {
        List<String> lineasEncontradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoInventario))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(palabra)) {
                    lineasEncontradas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar Palabra clave: " + e.getMessage());
        }
        return lineasEncontradas;
    }
}
