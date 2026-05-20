import java.util.*;

public class Grafo {
    private Map<String, Integer> ciudadAIndice;
    private Map<Integer, String> indiceACiudad;
    private int[][] matrizAdyacencia;
    private int numVertices;
    public static final int INF = 999999999; // Representa infinito

    public Grafo(int maxNodos) {
        ciudadAIndice = new HashMap<>();
        indiceACiudad = new HashMap<>();
        matrizAdyacencia = new int[maxNodos][maxNodos];
        numVertices = 0;

        for (int i = 0; i < maxNodos; i++) {
            Arrays.fill(matrizAdyacencia[i], INF);
            matrizAdyacencia[i][i] = 0;
        }
    }

    private void registrarCiudadSiNoExiste(String ciudad) {
        if (!ciudadAIndice.containsKey(ciudad)) {
            ciudadAIndice.put(ciudad, numVertices);
            indiceACiudad.put(numVertices, ciudad);
            numVertices++;
        }
    }

    public void agregarArco(String origen, String destino, int peso) {
        registrarCiudadSiNoExiste(origen);
        registrarCiudadSiNoExiste(destino);
        int u = ciudadAIndice.get(origen);
        int v = ciudadAIndice.get(destino);
        matrizAdyacencia[u][v] = peso;
    }

    public void eliminarArco(String origen, String destino) {
        if (ciudadAIndice.containsKey(origen) && ciudadAIndice.containsKey(destino)) {
            int u = ciudadAIndice.get(origen);
            int v = ciudadAIndice.get(destino);
            matrizAdyacencia[u][v] = INF;
        }
    }

    public int[][] obtenerMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public String obtenerCiudad(int indice) {
        return indiceACiudad.get(indice);
    }

    public Integer obtenerIndice(String ciudad) {
        return ciudadAIndice.get(ciudad);
    }
}