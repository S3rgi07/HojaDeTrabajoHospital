import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue; // Importando del JCF

public class SistemaEmergencias {
    public static void main(String[] args) {
        // PriorityQueue de java.util
        PriorityQueue<Paciente> colaEmergencias = new PriorityQueue<>();
        String rutaArchivo = "pacientes.txt";

        System.out.println("--- LEYENDO ARCHIVO DE PACIENTES ---");
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    Paciente p = new Paciente(datos[0], datos[1], datos[2]);
                    colaEmergencias.add(p); // Insertando en el JCF PriorityQueue
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        System.out.println("\n--- ATENDIENDO PACIENTES (Java Collection Framework) ---");
        while (!colaEmergencias.isEmpty()) {
            Paciente atendido = colaEmergencias.poll(); // poll() es el equivalente a remove() seguro en JCF
            System.out.println(atendido.toString());
        }
    }
}