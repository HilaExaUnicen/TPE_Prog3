package entrega1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {

	private Grafo<?> grafo;
    private HashMap<Integer, Integer> tiemposDescubrimiento;
    private HashMap<Integer, Integer> tiemposFinalizacion;
    private HashMap<Integer, Color> colores;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
        this.tiemposDescubrimiento = new HashMap<>();
        this.tiemposFinalizacion = new HashMap<>();
        this.colores = new HashMap<>();
	}
	
	public List<Integer> dfsForest() {
        List<Integer> ordenDescubrimiento = new ArrayList<>();
        int tiempo = 0;
        
        Iterator<Integer> vertices = grafo.obtenerVertices();
        while (vertices.hasNext()) {
            int vertice = vertices.next();
            colores.put(vertice, Color.BLANCO);
        }
   
        vertices = grafo.obtenerVertices();
        while (vertices.hasNext()) {
            int vertice = vertices.next();
            if (colores.get(vertice) == Color.BLANCO) {
            	dfs(vertice, tiempo, ordenDescubrimiento);
            }
        }
        return ordenDescubrimiento;
    }
    
    private void dfs(int vertice, int tiempo, List<Integer> ordenDescubrimiento) {
        tiemposDescubrimiento.put(vertice, tiempo++);
        colores.put(vertice, Color.AMARILLO);
        ordenDescubrimiento.add(vertice);
        
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
        while (adyacentes.hasNext()) {
            int adyacente = adyacentes.next();
            if (colores.get(adyacente) == Color.BLANCO) {
            	dfs(adyacente, tiempo, ordenDescubrimiento);
            }
        }
        colores.put(vertice, Color.NEGRO);
        tiemposFinalizacion.put(vertice, tiempo++);
    }
    
    public enum Color {
        BLANCO,
        AMARILLO,
        NEGRO
    }

}