package entrega1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class BFSHila<T> {
	private Grafo<T> grafo;
	private Map<Integer, Boolean> visitado;
	
	public BFSHila(Grafo<T> grafo) {
		this.grafo = grafo;
		this.visitado = new HashMap<>();
	}
	
	public List<Integer> bfsVisit() {
		LinkedList<Integer> recorrido = new LinkedList<>();
		
		Iterator<Integer> itVertices = this.grafo.obtenerVertices();
		while(itVertices.hasNext()) {
			int vertice = itVertices.next();
			this.visitado.put(vertice, false);
		}
		
		itVertices = this.grafo.obtenerVertices();//Se reinicia el iterador
		while(itVertices.hasNext()) {
			int vertice = itVertices.next();
			if(this.visitado.get(vertice).equals(false)) {
				bfs(vertice, recorrido);
			}
		}
		
		return recorrido;
	}
	
	private void bfs(Integer vertice, List<Integer> recorrido) {
		LinkedList<Integer> cola = new LinkedList<>();
		this.visitado.put(vertice, true);
		cola.add(vertice);
		
		while(!cola.isEmpty()) {
			Integer verticeActual = cola.poll();
			Iterator<Integer> itAdyacentes = this.grafo.obtenerAdyacentes(verticeActual);
			recorrido.add(verticeActual);
			
			while(itAdyacentes.hasNext()) {
				Integer adyacente = itAdyacentes.next();
				if(this.visitado.get(adyacente).equals(false)) {
					this.visitado.put(adyacente, true);
					cola.add(adyacente);
				}
			}
		}
	}
}