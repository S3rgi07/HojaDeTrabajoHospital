public class AlgoritmoFloyd {
    private int[][] distancias;
    private int[][] recorridos; // Matriz next para reconstruir caminos
    private Grafo grafo;
    private int n;

    public AlgoritmoFloyd(Grafo grafo) {
        this.grafo = grafo;
        this.n = grafo.getNumVertices();
        distancias = new int[n][n];
        recorridos = new int[n][n];
    }

    public void calcularRutas() {
        int[][] matriz = grafo.obtenerMatrizAdyacencia();

        // Inicialización
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distancias[i][j] = matriz[i][j];
                if (matriz[i][j] != Grafo.INF && i != j) {
                    recorridos[i][j] = j;
                } else {
                    recorridos[i][j] = -1;
                }
            }
        }

        // Procedimiento de Floyd [cite: 24]
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][k] != Grafo.INF && distancias[k][j] != Grafo.INF &&
                            distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        recorridos[i][j] = recorridos[i][k];
                    }
                }
            }
        }
    }

    public String obtenerRutaCorta(String origen, String destino) {
        Integer u = grafo.obtenerIndice(origen);
        Integer v = grafo.obtenerIndice(destino);

        if (u == null || v == null)
            return "Una de las ciudades no existe.";
        if (distancias[u][v] == Grafo.INF)
            return "No hay ruta disponible entre estas ciudades.";

        StringBuilder ruta = new StringBuilder();
        ruta.append("Distancia: ").append(distancias[u][v]).append(" KM. Ruta: ").append(origen);

        int actual = u;
        while (actual != v) {
            actual = recorridos[actual][v];
            ruta.append(" -> ").append(grafo.obtenerCiudad(actual));
        }
        return ruta.toString();
    }

    // Calcula el centro basado en excentricidad [cite: 25, 26]
    public String encontrarCentro() {
        int excentricidadMinima = Grafo.INF;
        int centroIndice = -1;

        for (int j = 0; j < n; j++) { // Iterar sobre columnas [cite: 25]
            int excentricidadActual = 0;
            for (int i = 0; i < n; i++) {
                if (distancias[i][j] != Grafo.INF && distancias[i][j] > excentricidadActual) {
                    excentricidadActual = distancias[i][j];
                }
            }
            if (excentricidadActual < excentricidadMinima) {
                excentricidadMinima = excentricidadActual;
                centroIndice = j;
            }
        }

        if (centroIndice == -1)
            return "El grafo está disconexo, no hay un centro claro.";
        return grafo.obtenerCiudad(centroIndice);
    }
}