import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SistemaEmergenciasPropio {
    public static void main(String[] args) {
        VectorHeap<Paciente> colaEmergencias = new VectorHeap<>();
        String rutaArchivo = "pacientes.txt"; // Asegúrate de tener este archivo en la raíz del proyecto

        System.out.println("--- LEYENDO ARCHIVO DE PACIENTES ---");
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    Paciente p = new Paciente(datos[0], datos[1], datos[2]);
                    colaEmergencias.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        System.out.println("\n--- ATENDIENDO PACIENTES (VectorHeap Propio) ---");
        while (!colaEmergencias.isEmpty()) {
            Paciente atendido = colaEmergencias.remove();
            System.out.println(atendido.toString());
        }
    }
}