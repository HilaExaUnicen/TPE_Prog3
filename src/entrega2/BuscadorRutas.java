package entrega2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;

import entrega1.Arco;
import entrega1.Grafo;
import entrega1.GrafoNoDirigido;

public class BuscadorRutas {
	private Grafo<?> estaciones;
	
	public BuscadorRutas(String csvEstaciones) {
		this.estaciones = this.inicializarEstacionesEnGrafo(csvEstaciones);
	}
	
	public Grafo<?> inicializarEstacionesEnGrafo(String csvEstaciones) {//Deberiamos encontrar una forma mas prolija de inicializarlo
		CSVReader lectorDocumento = new CSVReader(csvEstaciones);
		Grafo<?> estaciones = lectorDocumento.read();
		return estaciones;
	}
	
	public void construirTunelesGreedy() {
		//Se desean construir la menor cantidad de tuneles posibles
		//El criterio greedy deberia ser elegir una estacion origen e ir eligiendo la ruta mas cercana a esta.
		int kmsMejorSolucion = Integer.MAX_VALUE;
		ArrayList<Tunel> rutaActual = new ArrayList<>();
		ArrayList<Tunel> mejorRuta = new ArrayList<>();
		ArrayList<Integer> s = new ArrayList<>(); //Guarda las estaciones ya seleccionadas (selected);
		Iterator<Integer> itEstaciones = this.estaciones.obtenerVertices();
		
		while(itEstaciones.hasNext()) {
			Integer estacionActual = itEstaciones.next();
			
			s.add(estacionActual);
			
			while(!(s.size() == this.estaciones.cantidadVertices())) {//Va a cortar cuando todas las estaciones hayan sido seleccionadas
				Integer destinoMasProximo = seleccion(estacionActual, s);
						
				if(destinoMasProximo != null) {
					int kmsConstruidos = (int) this.estaciones.obtenerArco(estacionActual, destinoMasProximo).getEtiqueta();
					s.add(destinoMasProximo);
					Tunel nuevoTunel = new Tunel(estacionActual, destinoMasProximo, kmsConstruidos);
					rutaActual.add(nuevoTunel);
					
					estacionActual = destinoMasProximo;
				}
			}
			
			int kmsTotalesRutaActual = this.getKmsTotalesRuta(rutaActual);
			if(kmsTotalesRutaActual < kmsMejorSolucion) {
				kmsMejorSolucion = kmsTotalesRutaActual;
				mejorRuta = new ArrayList<>(rutaActual);
			}
			
			s.clear();
			rutaActual.clear();
		}
		
		imprimirSolucion("Greedy", mejorRuta, kmsMejorSolucion);//Falta la metrica
	}

	private int getKmsTotalesRuta(ArrayList<Tunel> rutasConstruidas) {
		int kms = 0;
		for(Tunel t: rutasConstruidas) {
			kms += (int) this.estaciones.obtenerArco(t.getOrigen(), t.getDestino()).getEtiqueta();
		}
		
		return kms;
	}

	private Integer seleccion(Integer origen, ArrayList<Integer> s) {
		//Seleccionamos la estacion mas proxima a origen(Todas las estaciones que todavia no se tomaron como solucion son adyacentes)
		Iterator<Integer> adyacentes = this.estaciones.obtenerAdyacentes(origen);
		Integer estacionMasProxima = null; //Estacion a retornar, si no encontro ninguna, retorna null
		int menorDistancia = Integer.MAX_VALUE;//La inicializamos en el mayor valor posible ya que si es 0 no va a encontrarse una.
		
		while(adyacentes.hasNext()) {
			Integer estacionActual = adyacentes.next();
			int distanciaEstacionActual = 0;
			if(!s.contains(estacionActual)) {
				Arco<?> arcoOrigenDestino = this.estaciones.obtenerArco(origen, estacionActual);
				distanciaEstacionActual = (int) arcoOrigenDestino.getEtiqueta();
				
				if(distanciaEstacionActual < menorDistancia) {
					menorDistancia = distanciaEstacionActual;
					estacionMasProxima = estacionActual;
				}
			}
		}
		
		return estacionMasProxima;
	}
	
	
//	public void construirTunelesBacktracking() {
//		ArrayList<ArrayList<Integer>> rutaMejorSolucion = new ArrayList<>();
//		ArrayList<ArrayList<Integer>> rutaActual = new ArrayList<>();
//		Integer kmsMejorSolucion = 0;
//
//		
//		Iterator<Integer> estaciones = this.estaciones.obtenerVertices();
//		while(estaciones.hasNext()) {
//			Integer estacion = estaciones.next();
//			construirTunelesBacktracking(estacion, rutaActual, rutaMejorSolucion);
//		}
//		
//		kmsMejorSolucion = this.getKmsMejorSolucion(rutaMejorSolucion);
//		imprimirSolucion("Backtracking", rutaMejorSolucion, kmsMejorSolucion); //Falta la metrica
//	}
//	
//	private void construirTunelesBacktracking(Integer estacion, ArrayList<ArrayList<Integer>> rutaActual, ArrayList<ArrayList<Integer>> rutaMejorSolucion) {
//		Integer kmsSolucionActual = getKmsSolucionActual(rutaActual);
//		Integer kmsMejorSolucion = getKmsMejorSolucion(rutaMejorSolucion);
//		
//		if(esEstadoFinal(rutaActual)) {//Estado final
//			if(kmsSolucionActual < kmsMejorSolucion) {
//				int aux = kmsSolucionActual;
//				rutaMejorSolucion = new ArrayList<ArrayList<Integer>>(rutaActual);
//				kmsMejorSolucion = aux;
//			}
//		}
//		else {
//			Iterator<Integer> adyacentes = this.estaciones.obtenerAdyacentes(estacion);
//			while(adyacentes.hasNext()) {
//				Integer estacionActual = adyacentes.next();
//				ArrayList<Integer> origenDestino = new ArrayList<>();
//				origenDestino.add(estacion);
//				origenDestino.add(estacionActual);
//				if(!rutaEstaEnSolucion(rutaActual, origenDestino)) {
//					
//					rutaActual.add(origenDestino);
//					
//					construirTunelesBacktracking(estacionActual, rutaActual, rutaMejorSolucion);
//					
//					rutaActual.remove(origenDestino);
//				}
//			}		
//		}
//	}
//	
//	private boolean rutaEstaEnSolucion(ArrayList<ArrayList<Integer>> rutaActual, ArrayList<Integer> origenDestino) {
//		Integer origen = origenDestino.get(0);
//		Integer destino = origenDestino.get(1);
//		
//		for(ArrayList<Integer> tunel : rutaActual) {
//			if(tunel.contains(origen) && tunel.contains(destino)) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//
//
//
//	private Integer getKmsMejorSolucion(ArrayList<ArrayList<Integer>> rutaMejorSolucion) {
//		if(rutaMejorSolucion.isEmpty()) {
//			return Integer.MAX_VALUE;
//		}
//		else {
//			int kms = 0;
//			for(ArrayList<Integer> tunel : rutaMejorSolucion) {
//				kms = kms + (int) this.estaciones.obtenerArco(tunel.get(0), tunel.get(1)).getEtiqueta();
//			}
//			return kms;
//		}
//	}
//	
//	private Integer getKmsSolucionActual(ArrayList<ArrayList<Integer>> rutaActual) {
//		if(rutaActual.isEmpty()) {
//			return 0;
//		}
//		
//		int kms = 0;
//		for(ArrayList<Integer> tunel : rutaActual) {
//			kms = kms + getDistanciaArcoElementos(tunel);
//		}
//		return kms;
//	}
//	
//	private int getDistanciaArcoElementos(ArrayList<Integer> tunel) {
//		int distanciaArco = 0;
//		distanciaArco = distanciaArco + (int) this.estaciones.obtenerArco(tunel.get(0), tunel.get(1)).getEtiqueta();
//		return distanciaArco;
//	}
//
//	private boolean esEstadoFinal(ArrayList<ArrayList<Integer>> rutaActual) {
//	    int numEstaciones = this.estaciones.cantidadVertices();
//	    HashSet<Integer> estacionesConectadas = new HashSet<>();
//	    for (ArrayList<Integer> tunel : rutaActual) {
//	        estacionesConectadas.add(tunel.get(0));
//	        estacionesConectadas.add(tunel.get(1));
//	    }
//	    return estacionesConectadas.size() == numEstaciones;
//	}
	

	private void imprimirSolucion(String tecnicaUtilizada, ArrayList<Tunel> rutasConstruidas, int kmsConstruidosTotal) {
		System.out.println(tecnicaUtilizada);
		for(Tunel tunel : rutasConstruidas) {
			System.out.print("[E" + tunel.getOrigen() + ", " + "E" + tunel.getDestino() +  "]");
		}
		System.out.println(" ");
		System.out.println(kmsConstruidosTotal + " kms");
		//syso(Metrica a decidir); 		
	}
	
	
	
}
