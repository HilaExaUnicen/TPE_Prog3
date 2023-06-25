package entrega2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;

import entrega1.Arco;
import entrega1.Grafo;
import entrega1.GrafoNoDirigido;

public class BuscadorRutas {
	private String csvEstaciones;
	private Grafo<?> estaciones;
	
	public BuscadorRutas(String csvEstaciones) {
		this.csvEstaciones = csvEstaciones;
		this.estaciones = new GrafoNoDirigido<>();
	}
	
	
	
	public BuscadorRutas(String csvEstaciones, Grafo<?> estaciones) {
		super();
		this.csvEstaciones = csvEstaciones;
		this.estaciones = estaciones;
	}



	public void inicializarEstacionesEnGrafo() {//Deberiamos encontrar una forma mas prolija de inicializarlo
		CSVReader lectorDocumento = new CSVReader(csvEstaciones);
		this.estaciones = lectorDocumento.read();
	}
	
	public void construirTunelesGreedy(Integer estacionOrigen) {
		//Se desean construir la menor cantidad de tuneles posibles
		//El criterio greedy deberia ser elegir una estacion origen e ir eligiendo la ruta mas cercana a esta.
		int kmsConstruidosTotal = 0;
		ArrayList<ArrayList<Integer>> rutasConstruidas = new ArrayList<>();
		ArrayList<Integer> s = new ArrayList<>(); //Guarda las estaciones ya seleccionadas (selected);
		Integer estacionActual = estacionOrigen;
		
		s.add(estacionOrigen);
		
		while(!(s.size() == this.estaciones.cantidadVertices())) {//Va a cortar cuando todas las estaciones hayan sido seleccionadas
			Integer destinoMasProximo = seleccion(estacionActual, s);
			
			if(destinoMasProximo != null) {
				ArrayList<Integer> nuevoTunel = new ArrayList<>(2);//Solo va a tener un origen y un destino
				s.add(destinoMasProximo);
				int kmsConstruidos = (int) this.estaciones.obtenerArco(estacionActual, destinoMasProximo).getEtiqueta();
				
				
				//Agregamos el nuevo tunel a la solucion
				nuevoTunel.add(estacionActual);
				nuevoTunel.add(destinoMasProximo);
				rutasConstruidas.add(nuevoTunel);
				
				//Sumamos los kilometros construidos a la solucion final
				kmsConstruidosTotal = kmsConstruidosTotal + kmsConstruidos;
				
				estacionActual = destinoMasProximo;
			}
		}
		
		imprimirSolucion("Greedy", rutasConstruidas, kmsConstruidosTotal);//Falta la metrica
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
	
	
	public void construirTunelesBacktracking() {
		ArrayList<ArrayList<Integer>> rutaMejorSolucion = new ArrayList<>();
		ArrayList<ArrayList<Integer>> rutaActual = new ArrayList<>();
		Integer kmsMejorSolucion = 0;

		
		Iterator<Integer> estaciones = this.estaciones.obtenerVertices();
		while(estaciones.hasNext()) {
			Integer estacion = estaciones.next();
			construirTunelesBacktracking(estacion, rutaActual, rutaMejorSolucion);
		}
		
		kmsMejorSolucion = this.getKmsMejorSolucion(rutaMejorSolucion);
		imprimirSolucion("Backtracking", rutaMejorSolucion, kmsMejorSolucion); //Falta la metrica
	}
	
	private void construirTunelesBacktracking(Integer estacion, ArrayList<ArrayList<Integer>> rutaActual, ArrayList<ArrayList<Integer>> rutaMejorSolucion) {
		Integer kmsSolucionActual = getKmsSolucionActual(rutaActual);
		Integer kmsMejorSolucion = getKmsMejorSolucion(rutaMejorSolucion);
		
		if(esEstadoFinal(rutaActual)) {//Estado final
			if(kmsSolucionActual < kmsMejorSolucion) {
				int aux = kmsSolucionActual;
				rutaMejorSolucion = new ArrayList<ArrayList<Integer>>(rutaActual);
				kmsMejorSolucion = aux;
			}
		}
		else {
			Iterator<Integer> adyacentes = this.estaciones.obtenerAdyacentes(estacion);
			while(adyacentes.hasNext()) {
				Integer estacionActual = adyacentes.next();
				ArrayList<Integer> origenDestino = new ArrayList<>();
				origenDestino.add(estacion);
				origenDestino.add(estacionActual);
				if(!rutaEstaEnSolucion(rutaActual, origenDestino)) {
					
					rutaActual.add(origenDestino);
					
					construirTunelesBacktracking(estacionActual, rutaActual, rutaMejorSolucion);
					
					rutaActual.remove(origenDestino);
				}
			}		
		}
	}
	
	private boolean rutaEstaEnSolucion(ArrayList<ArrayList<Integer>> rutaActual, ArrayList<Integer> origenDestino) {
		Integer origen = origenDestino.get(0);
		Integer destino = origenDestino.get(1);
		
		for(ArrayList<Integer> tunel : rutaActual) {
			if(tunel.contains(origen) && tunel.contains(destino)) {
				return true;
			}
		}
		
		return false;
	}



	private Integer getKmsMejorSolucion(ArrayList<ArrayList<Integer>> rutaMejorSolucion) {
		if(rutaMejorSolucion.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		else {
			int kms = 0;
			for(ArrayList<Integer> tunel : rutaMejorSolucion) {
				kms = kms + (int) this.estaciones.obtenerArco(tunel.get(0), tunel.get(1)).getEtiqueta();
			}
			return kms;
		}
	}
	
	private Integer getKmsSolucionActual(ArrayList<ArrayList<Integer>> rutaActual) {
		if(rutaActual.isEmpty()) {
			return 0;
		}
		
		int kms = 0;
		for(ArrayList<Integer> tunel : rutaActual) {
			kms = kms + getDistanciaArcoElementos(tunel);
		}
		return kms;
	}
	
	private int getDistanciaArcoElementos(ArrayList<Integer> tunel) {
		int distanciaArco = 0;
		distanciaArco = distanciaArco + (int) this.estaciones.obtenerArco(tunel.get(0), tunel.get(1)).getEtiqueta();
		return distanciaArco;
	}

	private boolean esEstadoFinal(ArrayList<ArrayList<Integer>> rutaActual) {
	    int numEstaciones = this.estaciones.cantidadVertices();
	    HashSet<Integer> estacionesConectadas = new HashSet<>();
	    for (ArrayList<Integer> tunel : rutaActual) {
	        estacionesConectadas.add(tunel.get(0));
	        estacionesConectadas.add(tunel.get(1));
	    }
	    return estacionesConectadas.size() == numEstaciones;
	}
	

	private void imprimirSolucion(String tecnicaUtilizada, ArrayList<ArrayList<Integer>> rutasConstruidas, int kmsConstruidosTotal) {
		System.out.println(tecnicaUtilizada);
		for(ArrayList<Integer> ruta: rutasConstruidas) {
			System.out.print(ruta + ", ");
		}
		System.out.println(" ");
		System.out.println(kmsConstruidosTotal + " kms");
		//syso(Metrica a decidir); 		
	}
	
	
	
}
