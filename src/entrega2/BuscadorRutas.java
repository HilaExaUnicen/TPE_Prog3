package entrega2;

import java.util.ArrayList;
import java.util.Iterator;

import entrega1.Arco;
import entrega1.Grafo;

public class BuscadorRutas {
	private Grafo<?> estaciones;
	
	public BuscadorRutas(String csvEstaciones) {
		this.estaciones = this.inicializarEstacionesEnGrafo(csvEstaciones);
	}
	
	public Grafo<?> inicializarEstacionesEnGrafo(String csvEstaciones) {
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
	
	
	private void imprimirSolucion(String tecnicaUtilizada, ArrayList<Tunel> rutasConstruidas, int kmsConstruidosTotal) {
		System.out.println(tecnicaUtilizada);
		for(Tunel tunel : rutasConstruidas) {
			System.out.print("[E" + tunel.getOrigen() + ", " + "E" + tunel.getDestino() +  "]");
		}
		System.out.println(" ");
		System.out.println(kmsConstruidosTotal + " kms");
		System.out.println("X metrica"); //TODO A implementar	
	}
	
	public void construirTunelesBacktracking() {
		ArrayList<Tunel> rutaActual = new ArrayList<>();
		ArrayList<Tunel> mejorRuta = new ArrayList<>();
		Iterator<Integer> itEstaciones = this.estaciones.obtenerVertices();
		
		while(itEstaciones.hasNext()) {
			Integer estacionOrigen = itEstaciones.next();
			backtracka(estacionOrigen, rutaActual, mejorRuta);
		}
		
		int kmsMejorSolucion = this.getKmsTotalesRuta(mejorRuta);
		
		//imprimirSolucion("Backtracking, mejorRuta, kmsMejorSolucion);
		
		
	}

	private void backtracka(Integer estacionOrigen, ArrayList<Tunel> rutaActual, ArrayList<Tunel> mejorRuta) {
		if(solucionActualTieneTodosLosElementos(rutaActual)) {
			
		}
		else {
			
		}
	}

	private boolean solucionActualTieneTodosLosElementos(ArrayList<Tunel> rutaActual) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
