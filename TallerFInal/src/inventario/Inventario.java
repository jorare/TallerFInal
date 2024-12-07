package inventario;

import producto.Producto;

import java.io.*;
import java.util.*;

public class Inventario {

    List<Producto> listaProductos = new ArrayList<>();
    private static final String archivoProductosInventario = "Inventario_productos.txt";

    // Constructor: Carga los productos desde el archivo

    public Inventario() {
        cargarProductos();
    }

    //Metodo cargar productos

    public void cargarProductos() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoProductosInventario))) {
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
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        }
    }

    // Metodo Agregar Producto

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
        guardarCambios();
    }

    // Metodo Actualizar producto


    public void actualizarProducto(String id, Producto nuevoProducto) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId().equals(id)) {
                listaProductos.set(i, nuevoProducto);
                guardarCambios();
               // System.out.println("hasta aca imprime el producto en inventario " + listaProductos.get(i));
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    // metodo Eliminar producto

    public void eliminarProducto(String id) {
        listaProductos.removeIf(p -> p.getId().equals(id));
        guardarCambios();
    }

    public List<String> buscarPorCategoria(String palabra) {
        List<String> lineasEncontradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoProductosInventario))) {
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

    // Calcular precio Producto

    public Producto productoMasCaro() {
        return listaProductos.stream().max(Comparator.comparingInt(Producto::getPrecio)).orElse(null);
    }

    // Calcular cantidad de Productos

    public Map<String, Integer> cantidadPorCategoria() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Producto p : listaProductos) {
            conteo.put(p.getCategoria(), conteo.getOrDefault(p.getCategoria(), 0) + p.getCantidadDisponible());
        }
        return conteo;
    }

// Metodo que guarda los cambios

    private void guardarCambios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoProductosInventario))) {
            for (Producto p : listaProductos) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar cambios: " + e.getMessage());
        }
    }
}
