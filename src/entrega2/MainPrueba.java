package entrega2;

import entrega1.Grafo;
import entrega1.GrafoNoDirigido;

public class MainPrueba {
	public static void main(String[] args) {
		Grafo<Integer> estaciones = new GrafoNoDirigido<>();
		String ruta = "/tpe_prog3/datasets/dataset1" + 0 + ".txt"; //dataset" + 0 + ".txt"
		
//		estaciones.agregarVertice(1);
//		estaciones.agregarVertice(2);
//		estaciones.agregarVertice(3);
//		estaciones.agregarVertice(4);
//		
//		estaciones.agregarArco(1, 2, 15);
//		estaciones.agregarArco(1, 3, 20);
//		estaciones.agregarArco(1, 4, 30);
//		estaciones.agregarArco(2, 3, 15);
//		estaciones.agregarArco(2, 4, 25);
//		estaciones.agregarArco(3, 4, 50);
//		
//		String xd = "";
//		BuscadorRutas buscador = new BuscadorRutas(xd, estaciones);
//		
//		buscador.construirTunelesGreedy(4);
//		System.out.println(" ");
//		buscador.construirTunelesBacktracking();
		
		BuscadorRutas buscador = new BuscadorRutas(ruta, estaciones);
		buscador.inicializarEstacionesEnGrafo();
		buscador.construirTunelesGreedy(2);
	}
}
