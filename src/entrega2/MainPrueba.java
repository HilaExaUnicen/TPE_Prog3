package entrega2;

public class MainPrueba {
	public static void main(String[] args) {
		String ruta = "C:/Users/Usuario/Documents/eclipse-workspace/tpe_prog3/datasets/dataset1.txt";
		
//		estaciones.agregarVertice(1);
//		estaciones.agregarVertice(2);
//		estaciones.agregarVertice(3);
//		estaciones.agregarVertice(4);
//		
//		estaciones.agregarArco(1, 2, 15);;
//		estaciones.agregarArco(1, 3, 20);
//		estaciones.agregarArco(1, 4, 30);
//		estaciones.agregarArco(2, 3, 15);
//		estaciones.agregarArco(2, 4, 25);
//		estaciones.agregarArco(3, 4, 50);
//		
		BuscadorRutas buscador = new BuscadorRutas(ruta);
		buscador.construirTunelesGreedy();
		System.out.println(" ");
//		buscador.construirTunelesBacktracking();
	}
}
