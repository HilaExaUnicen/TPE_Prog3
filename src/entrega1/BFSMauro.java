package entrega1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSMauro<T> {
	private final Grafo<T> grafo;
    
	public BFSMauro(Grafo<T> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> bfsForest(){
		List<Integer> ordenDescubrimiento = new ArrayList<>();
		boolean[] visitado = new boolean[grafo.cantidadVertices()+1]; //el out of bounds creo que es el array que hay que ponerlo +1;
		
		for (int i = 0; i < visitado.length; i++) {
            if (!visitado[i]) {
                bfs(i, visitado, ordenDescubrimiento);
            }
        }
		
        return ordenDescubrimiento;
    }
	
	private void bfs(int origen, boolean[] visitado, List<Integer> ordenDescubrimiento) {
        Queue<Integer> cola = new LinkedList<>();
        visitado[origen] = true;
        cola.add(origen);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            ordenDescubrimiento.add(actual);

            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
            while (adyacentes.hasNext()) {
                int adyacente = adyacentes.next();
                if (!visitado[adyacente]) {
                    visitado[adyacente] = true;
                    cola.add(adyacente);
                }
            }
        }
    }
}
