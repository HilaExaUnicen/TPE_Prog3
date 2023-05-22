package entrega1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {

	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	public List<List<Integer>> caminos() {
		List<List<Integer>> caminos = new ArrayList<>();
		ArrayList<Integer> current = new ArrayList<>();
		
		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(origen);
		while(adyacentes.hasNext()) {
			current.add(this.origen);
			this.dfs(adyacentes.next(), current, caminos);
			
			if(current.contains(this.origen) && current.contains(this.destino)) {
				caminos.add(current);
			}
			
			current.clear();
		}
		
		
		return caminos;
	}
	
	private void dfs(Integer vertice, ArrayList<Integer> current, List<List<Integer>> caminos) {
		if(!current.contains(origen)) {
			current.add(origen);
			
			Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(origen);
			while(adyacentes.hasNext()) {
			
				
				Integer verticeAdy = adyacentes.next();
				
				if(verticeAdy == this.destino) {
					current.add(verticeAdy);
					caminos.add(current);
					current.clear();
					return;
				}
				
				if((current.size()-1) > this.lim) {//-1 porque restamos el vertice origen.
					current.clear();
					return;
				}
				
				dfs(verticeAdy, current, caminos);
			}
			
		}
	}
}