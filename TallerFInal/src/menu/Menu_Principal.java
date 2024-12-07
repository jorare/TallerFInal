package menu;

import java.util.Scanner;

public class Menu_Principal {

    Scanner scanner = new Scanner(System.in);
    int opcion = 0;

    public static void menuPrincipal() {


        System.out.println("                                          ");
        System.out.println("------------------------------------------");
        System.out.println("           MENU INVENTARIOS               ");
        System.out.println("------------------------------------------");
        System.out.println("- 1. Agregar producto                    -");
        System.out.println("- 2. Actualizar producto                 -");
        System.out.println("- 3. Eliminar producto                   -");
        System.out.println("- 4. Buscar por categoría                -");
        System.out.println("- 5. Generar reporte                     -");
        System.out.println("- 6. Cantidad de productos por categoría -");
        System.out.println("- 7. Producto más caro                   -");
        System.out.println("- 8. Salir                                ");
        System.out.println("------------------------------------------");
        System.out.print("Seleccione una opción del menu: ");
    }

    public static void menuCategorias() {

        System.out.println("                                          ");
        System.out.println("------------------------------------------");
        System.out.println("           MENU CATEGORIAS                ");
        System.out.println("------------------------------------------");
        System.out.println("- 1. Buscar por Categoria                -");
        System.out.println("- 2. Buscar por Nombre                   -");
        System.out.println("- 3. Buscar por id del producto          -");
        System.out.println("- 4. Salir                                ");
        System.out.println("------------------------------------------");
        System.out.print("Seleccione una opción del menu: ");
    }

}