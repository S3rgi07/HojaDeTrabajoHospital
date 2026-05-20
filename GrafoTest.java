import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GrafoTest {
    private Grafo grafo;
    private AlgoritmoFloyd floyd;

    @Before
    public void setUp() {
        grafo = new Grafo(5);
        grafo.agregarArco("A", "B", 10);
        grafo.agregarArco("B", "C", 5);
        floyd = new AlgoritmoFloyd(grafo);
        floyd.calcularRutas();
    }

    @Test
    public void testAgregarArco() {
        assertEquals((Integer) 0, grafo.obtenerIndice("A"));
        int[][] matriz = grafo.obtenerMatrizAdyacencia();
        assertEquals(10, matriz[grafo.obtenerIndice("A")][grafo.obtenerIndice("B")]);
    }

    @Test
    public void testEliminarArco() {
        grafo.eliminarArco("A", "B");
        int[][] matriz = grafo.obtenerMatrizAdyacencia();
        assertEquals(Grafo.INF, matriz[grafo.obtenerIndice("A")][grafo.obtenerIndice("B")]);
    }

    @Test
    public void testAlgoritmoFloydDistancia() {
        // A -> B -> C debería ser 15
        String resultado = floyd.obtenerRutaCorta("A", "C");
        assertTrue(resultado.contains("Distancia: 15 KM"));
    }

    @Test
    public void testCentroGrafo() {
        // En A->B (10) y B->C (5), el centro lógico que alcanza a otros más rápido
        // dependiendo de la direccionalidad
        String centro = floyd.encontrarCentro();
        assertNotNull(centro);
    }
}