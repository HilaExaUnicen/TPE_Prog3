package entrega1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {

	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	private List<List<Integer>> caminos;
	

	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		this.caminos = new ArrayList<>();
	}

	public List<List<Integer>> caminos() {
		ArrayList<Integer> caminoActual = new ArrayList<>();
		caminoActual.add(origen);
		int arcosRecorridos = 0;
		
		this.dfs(origen, caminoActual, arcosRecorridos);
		return caminos;
	}
	
    private void dfs(int vertice, List<Integer> caminoActual, int arcosRecorridos) {
        if (vertice == destino && arcosRecorridos <= lim) {
            caminos.add(new ArrayList<>(caminoActual));
            return;
        }

        if (arcosRecorridos >= lim) {
            return;
        }

        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
        
        while (adyacentes.hasNext()) {
            Integer siguienteVertice = adyacentes.next();

            if (!caminoActual.contains(siguienteVertice)) {
                caminoActual.add(siguienteVertice);
                dfs(siguienteVertice, caminoActual, arcosRecorridos + 1);
                caminoActual.remove(caminoActual.size() - 1);
            }
        }
    }
}








