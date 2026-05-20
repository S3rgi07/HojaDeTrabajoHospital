import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(50); // Asumiendo un máximo de 50 ciudades
        Scanner scanner = new Scanner(System.in);

        // Lectura de archivo
        try {
            File archivo = new File("guategrafo.txt");
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(" ");
                if (partes.length == 3) {
                    grafo.agregarArco(partes[0], partes[1], Integer.parseInt(partes[2]));
                }
            }
            lector.close();
            System.out.println("Grafo cargado exitosamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo guategrafo.txt.");
            return;
        }

        AlgoritmoFloyd floyd = new AlgoritmoFloyd(grafo);
        floyd.calcularRutas();

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Centro de Respuesta Covid-19 ---");
            System.out.println("1. Buscar ruta más corta");
            System.out.println("2. Encontrar centro logístico del grafo");
            System.out.println("3. Modificar grafo (interrupción o nueva conexión)");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ciudad origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ciudad destino: ");
                    String destino = scanner.nextLine();
                    System.out.println(floyd.obtenerRutaCorta(origen, destino));
                    break;
                case 2:
                    System.out.println("El centro óptimo para oficinas logísticas es: " + floyd.encontrarCentro());
                    break;
                case 3:
                    System.out.println("a) Interrupción de tráfico (eliminar ruta)");
                    System.out.println("b) Nueva conexión (agregar/actualizar ruta)");
                    String sub = scanner.nextLine();
                    System.out.print("Ciudad 1: ");
                    String c1 = scanner.nextLine();
                    System.out.print("Ciudad 2: ");
                    String c2 = scanner.nextLine();

                    if (sub.equalsIgnoreCase("a")) {
                        grafo.eliminarArco(c1, c2);
                        System.out.println("Ruta eliminada.");
                    } else if (sub.equalsIgnoreCase("b")) {
                        System.out.print("Distancia en KM: ");
                        int km = scanner.nextInt();
                        grafo.agregarArco(c1, c2, km);
                        System.out.println("Ruta actualizada.");
                    }
                    floyd = new AlgoritmoFloyd(grafo); // Recalcular con el nuevo estado [cite: 142]
                    floyd.calcularRutas();
                    break;
                case 4:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }
}