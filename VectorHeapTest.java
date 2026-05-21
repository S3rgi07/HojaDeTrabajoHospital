import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VectorHeapTest {
    private VectorHeap<Paciente> heap;

    @BeforeEach
    public void setUp() {
        heap = new VectorHeap<>();
    }

    @Test
    public void testAddAndGetFirst() {
        heap.add(new Paciente("Juan", "Fractura", "C"));
        heap.add(new Paciente("Maria", "Apendicitis", "A"));

        // Maria debería ser la primera porque 'A' tiene mayor prioridad que 'C'
        assertEquals("A", heap.getFirst().getCodigoEmergencia());
        assertEquals("Maria", heap.getFirst().getNombre());
    }

    @Test
    public void testRemove() {
        heap.add(new Paciente("Juan Perez", "fractura de pierna", "C"));
        heap.add(new Paciente("Maria Ramirez", "apendicitis", "A"));
        heap.add(new Paciente("Lorenzo Toledo", "chikunguya", "E"));
        heap.add(new Paciente("Carmen Sarmientos", "dolores de parto", "B"));

        // El orden de extracción debe ser A, B, C, E
        assertEquals("Maria Ramirez", heap.remove().getNombre());
        assertEquals("Carmen Sarmientos", heap.remove().getNombre());
        assertEquals("Juan Perez", heap.remove().getNombre());
        assertEquals("Lorenzo Toledo", heap.remove().getNombre());
        assertTrue(heap.isEmpty());
    }
}