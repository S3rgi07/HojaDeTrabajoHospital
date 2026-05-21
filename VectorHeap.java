import java.util.Vector;

/**
 * Implementación de una cola de prioridad basada en un Min-Heap utilizando un
 * Vector.
 * El elemento con el valor más bajo (según su compareTo) se mantiene en la
 * raíz.
 *
 * @param <E> El tipo de elementos mantenidos en esta cola, debe implementar
 *            Comparable.
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    protected Vector<E> data; // Los datos, guardados en orden de heap

    /**
     * Construye un VectorHeap vacío.
     */
    public VectorHeap() {
        data = new Vector<E>();
    }

    /**
     * Devuelve el índice del padre del nodo en la posición i.
     * 
     * @param i índice del nodo actual.
     * @return índice del nodo padre.
     */
    protected static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Devuelve el índice del hijo izquierdo del nodo en la posición i.
     * 
     * @param i índice del nodo actual.
     * @return índice del hijo izquierdo.
     */
    protected static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Devuelve el índice del hijo derecho del nodo en la posición i.
     * 
     * @param i índice del nodo actual.
     * @return índice del hijo derecho.
     */
    protected static int right(int i) {
        return (2 * i + 2) - 1 + 1; // Equivalente a 2*i + 2
    }

    /**
     * Mueve el nodo en el índice leaf hacia arriba a su posición correcta en el
     * heap.
     * 
     * @param leaf el índice del nodo a mover hacia arriba.
     */
    protected void percolateUp(int leaf) {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 && (value.compareTo(data.get(parent)) < 0)) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf, value);
    }

    /**
     * Inserta un nuevo valor en la cola de prioridad.
     * 
     * @param value el valor a insertar.
     */
    @Override
    public void add(E value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    /**
     * Mueve el nodo en el índice root hacia abajo a su posición correcta en el
     * heap.
     * 
     * @param root el índice del nodo a mover hacia abajo.
     */
    protected void pushDownRoot(int root) {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int childpos = left(root);
            if (childpos < heapSize) {
                if ((right(root) < heapSize) && ((data.get(childpos + 1)).compareTo(data.get(childpos)) < 0)) {
                    childpos++;
                }
                if ((data.get(childpos)).compareTo(value) < 0) {
                    data.set(root, data.get(childpos));
                    root = childpos; // sigue bajando
                } else {
                    data.set(root, value);
                    return;
                }
            } else { // no tiene hijos
                data.set(root, value);
                return;
            }
        }
    }

    /**
     * Elimina y devuelve el valor mínimo (raíz) de la cola de prioridad.
     * 
     * @return el valor mínimo de la cola.
     */
    @Override
    public E remove() {
        E minVal = getFirst();
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1);
        if (data.size() > 1) {
            pushDownRoot(0);
        }
        return minVal;
    }

    /**
     * Devuelve el valor mínimo de la cola sin eliminarlo.
     * 
     * @return el valor mínimo.
     */
    @Override
    public E getFirst() {
        return data.get(0);
    }

    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si está vacía, false en caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Devuelve la cantidad de elementos en la cola.
     * 
     * @return el tamaño de la cola.
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Elimina todos los elementos de la cola.
     */
    @Override
    public void clear() {
        data.clear();
    }
}