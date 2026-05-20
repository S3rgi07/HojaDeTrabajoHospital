import networkx as nx

def main():
    G = nx.DiGraph()

    # Cargar grafo [cite: 129]
    try:
        with open('guategrafo.txt', 'r') as file:
            for line in file:
                partes = line.strip().split()
                if len(partes) == 3:
                    G.add_edge(partes[0], partes[1], weight=int(partes[2]))
        print("Grafo cargado exitosamente.")
    except FileNotFoundError:
        print("No se encontró el archivo guategrafo.txt.")
        return

    while True:
        print("\n--- Centro de Respuesta Covid-19 (Python/NetworkX) ---")
        print("1. Buscar ruta más corta")
        print("2. Encontrar centro logístico del grafo")
        print("3. Salir")
        opcion = input("Elija una opción: ")

        if opcion == '1':
            origen = input("Ciudad origen: ")
            destino = input("Ciudad destino: ")
            try:
                # Usa Dijkstra internamente para buscar ruta, equivalente a Floyd para 2 nodos
                distancia = nx.shortest_path_length(G, source=origen, target=destino, weight='weight')
                ruta = nx.shortest_path(G, source=origen, target=destino, weight='weight')
                print(f"Distancia: {distancia} KM. Ruta: {' -> '.join(ruta)}")
            except (nx.NetworkXNoPath, nx.NodeNotFound):
                print("No hay ruta disponible o una ciudad no existe.")

        elif opcion == '2':
            try:
                # El centro se calcula con el método de networkx usando las distancias precalculadas por Floyd-Warshall [cite: 158]
                distancias = dict(nx.floyd_warshall(G, weight='weight'))
                
                # Excentricidad en Networkx (adaptación manual debido a que networkx.center funciona distinto en grafos dirigidos)
                excentricidades = {}
                for j in G.nodes():
                    max_dist = 0
                    for i in G.nodes():
                        if distancias[i][j] != float('inf') and distancias[i][j] > max_dist:
                            max_dist = distancias[i][j]
                    excentricidades[j] = max_dist
                
                centro = min(excentricidades, key=excentricidades.get)
                print(f"El centro óptimo para oficinas logísticas es: {centro}")
                
            except Exception as e:
                print(f"Error al calcular centro: {e}")

        elif opcion == '3':
            break

if __name__ == "__main__":
    main()