package entrega1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DFSHila<T> {
	private Grafo<T> grafo;
	private Map<Integer, String> colores;
	private Map<Integer, Integer> tiempoDescubrimiento;
	private Map<Integer, Integer> tiempoFinalizacion;
	
	public DFSHila(Grafo<T> grafo) {
		this.grafo = grafo;
		this.colores = new HashMap<>();
		this.tiempoDescubrimiento = new HashMap<>();
		this.tiempoFinalizacion = new HashMap<>();
	}
	
	public List<Integer> dfsForest() {
		LinkedList<Integer> ordenDescubrimiento = new LinkedList<>();
		Iterator<Integer> itVertices = this.grafo.obtenerVertices();
		int tiempo;
		
		while(itVertices.hasNext()) {
			this.colores.put(itVertices.next(), "blanco");
		}
		
		tiempo = 0;
		
		for(Integer vertice: this.colores.keySet()) {
			if(colores.get(vertice).equalsIgnoreCase("blanco")) {
				dfs(vertice, tiempo, ordenDescubrimiento);
			}
		}
		
		return ordenDescubrimiento;
	}
	
	private void dfs(int vertice, int tiempo, LinkedList<Integer> orden) {
		this.colores.put(vertice, "amarillo");
		tiempo++;
		this.tiempoDescubrimiento.put(vertice, tiempo);
		orden.add(vertice);
		
		Iterator<Integer> itAdyacentes = this.grafo.obtenerAdyacentes(vertice);
		while(itAdyacentes.hasNext()) {
			Integer vAdyacente = itAdyacentes.next();
			if(this.colores.get(vAdyacente).equalsIgnoreCase("blanco")) {
				dfs(vAdyacente, tiempo, orden);
			}
		}
		
		this.colores.put(vertice, "negro");
		tiempo++;
		this.tiempoFinalizacion.put(vertice, tiempo);
	}
	
}