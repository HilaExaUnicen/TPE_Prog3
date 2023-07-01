package entrega2;
//package entrega2;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//
//import entrega1.GrafoNoDirigido;
//
//public class Backtracking extends Algoritmo {
//	
//	public Backtracking(GrafoNoDirigido<?> estaciones) {
//		super(estaciones);
//	}
//
//	public void construirTuneles() {
//		Estado estado = new Estado();
//		Iterator<Integer> itEstaciones = this.estaciones.obtenerVertices();
//		
//		while(itEstaciones.hasNext()) {
//			Integer estacionOrigen = itEstaciones.next();
//			backtracka(estacionOrigen, estado);
//			this.metrica++;
//		}
//		
//		
//		super.imprimirSolucion("Backtracking", estado.getMejorRuta(), estado.getCantKmsMejorSolucion(), metrica);
//		
//		
//	}
//
//	private void backtracka(Integer estacionOrigen, Estado estado) {
////		if(estado.estacionesEstanConectadas(this.estaciones.obtenerVertices())) {
////			
////		}
////		else {
//			if(estado.esEstadoFinal(this.estaciones.obtenerVertices())) {
//				if(estado.getCantKmsRutaActual() < estado.getCantKmsMejorSolucion()) {
//					estado.setMejorRuta();
//					estado.setCantKmsMejorSolucion();
//				}
//			}
//			else {
//				Iterator<Integer> estacionesAdyacentes = this.estaciones.obtenerAdyacentes(estacionOrigen);
//				while(estacionesAdyacentes.hasNext()) {
//					Integer estAdyacente = estacionesAdyacentes.next();
//					Tunel nuevoTunel = new Tunel(estacionOrigen, estAdyacente, (int)this.estaciones.obtenerArco(estacionOrigen, estAdyacente).getEtiqueta());//Etiqueta deberia obtenerse de estado.
//					
//					if(!estado.rutaActualContieneTunel(nuevoTunel)) {
//						estado.addTunelRutaActual(nuevoTunel);
//						estado.sumarKmsRutaActual(nuevoTunel.getEtiqueta());
//						backtracka(estAdyacente, estado);
//						estado.removeTunelRutaActual();
//						estado.restarKmsRutaActual(nuevoTunel.getEtiqueta());
//					}
//					this.metrica++;
//				}
//			}
////		}
//	}
//		
//	private class Estado{
//		private ArrayList<Tunel> rutaActual = new ArrayList<>();
//		private ArrayList<Tunel> mejorRuta = new ArrayList<>();
//		private int cantKmsRutaActual;
//		private int cantKmsMejorSolucion;
//		//private int metricaCantIteraciones;
//		
//		public Estado() {
//			rutaActual = new ArrayList<>();
//			mejorRuta = new ArrayList<>();
//			cantKmsRutaActual = 0;
//			cantKmsMejorSolucion = Integer.MAX_VALUE;
//			//metricaCantIteraciones = 0;
//		}
//	
//		public boolean rutaActualContieneTunel(Tunel nuevoTunel) {
//			return this.rutaActual.contains(nuevoTunel);
//		}
//		
//		public void addTunelRutaActual(Tunel tunel) {
//			this.rutaActual.add(tunel);
//		}
//		
//		public void removeTunelRutaActual() {
//			this.rutaActual.remove(rutaActual.size()-1);
//		}
//
//		public ArrayList<Tunel> getMejorRuta() {
//			return new ArrayList<>(mejorRuta);
//		}
//
//		public void setMejorRuta() {
//			this.mejorRuta = new ArrayList<>(rutaActual);
//		}
//
////		public ArrayList<Tunel> getRutaActual() {
////			return rutaActual;
////		}
//
//		public int getCantKmsRutaActual() {
//			return cantKmsRutaActual;
//		}
//		
//		public void sumarKmsRutaActual(int kms) {
//			this.cantKmsRutaActual += kms;//TODO faltaria validacion > 0
//		}
//		
//		public void restarKmsRutaActual(int kms) {
//			this.cantKmsRutaActual -= kms;
//		}
//
//		public int getCantKmsMejorSolucion() {
//			return cantKmsMejorSolucion;
//		}
//		
//		public void setCantKmsMejorSolucion() {
//			int aux = this.cantKmsRutaActual;
//			this.cantKmsMejorSolucion = aux;
//		}
//
//		public boolean esEstadoFinal(Iterator<Integer> estaciones) {
//			while(estaciones.hasNext()) {
//				Integer estacion = estaciones.next();
//				if(!rutaActualTieneEstacion(estacion)) {
//					return false;
//				}
//				
//				metrica++;
//			}
//			
//			return true;
//		}
//		
//		public boolean rutaActualTieneEstacion(Integer estacion) {
//			for(Tunel t : rutaActual) {
//				if(t.getOrigen() == estacion || t.getDestino() == estacion) {
//					return true;
//				}
//				metrica++;
//			}
//			
//			return false;
//		}
//	}
//}

//LA OTRA VERSION

//package entrega2;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//
//import entrega1.Arco;
//import entrega1.GrafoNoDirigido;
//
//public class BacktrackingPorArcos extends Algoritmo {
//		
//		public BacktrackingPorArcos(GrafoNoDirigido<?> estaciones) {
//			super(estaciones);
//		}
//
//		public void construirTuneles() {
//			Estado estado = new Estado();
//			estado.inicializarGrafo();
//			LinkedList<Arco<Integer>> tuneles = this.estaciones.obtenerListaArcos();
//			LinkedList<Arco<Integer>> tunelesVisitados = new LinkedList<>();
//			backtracking(estado, tuneles, tunelesVisitados);
//			
//			imprimirSolucion("Backtracking", estado.getTunelesConstruidos(), estado.getCantKmsMejorSolucion(), 0);
//		}
//
//		private void backtracking(Estado estado, LinkedList<Arco<Integer>> tuneles, LinkedList<Arco<Integer>> tunelesVisitados) {
//			if(estado.estacionesEstanConectadas()) {
//				if(estado.getCantKmsRutaActual() < estado.getCantKmsMejorSolucion()) {
//					estado.actualizarMejorSolucion();
//					estado.actualizarCantKmsMejorSolucion();
//				}
//			}
//			else {
//				
//					
//				}
//		
//			
////				while(estado.hayTunelesDisponibles()) {
////					Arco<Integer> tunelPosible = estado.getTunelPosible();
////					
////					if(!estado.rutaActualContieneTunel(tunelPosible)) {
////						estado.agregarTunelRutaActual(tunelPosible);
////						estado.sumarCantKmsRutaActual(tunelPosible.getEtiqueta());
////						backtracking(estado);
////						estado.restarCantKmsRutaActual(tunelPosible.getEtiqueta());
////						estado.eliminarTunelRutaActual(tunelPosible);			
////					}
////					
////					
////				}
//			}
//		}
//			
//private class Estado {
//	private int cantKmsRutaActual;
//	private int cantKmsMejorSolucion;
//	private GrafoNoDirigido<Integer> tunelesRutaActual;
//	private GrafoNoDirigido<Integer> tunelesMejorSolucion;
//	private HashMap<Arco<Integer>, Boolean> tunelesPosibles;
//	
//	public Estado() {
//		this.cantKmsRutaActual = 0;
//		this.cantKmsMejorSolucion = Integer.MAX_VALUE;
//		this.tunelesRutaActual = new GrafoNoDirigido<>();
//		this.tunelesMejorSolucion = new GrafoNoDirigido<>();
//		this.tunelesPosibles = new HashMap<>();
//	}
//	
//
//
//	public void marcarTunelVisitado(Arco<Integer> tunelPosible) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	public boolean rutaActualContieneTunel(Arco<Integer> tunel) {
//		return this.tunelesRutaActual.existeArco(tunel.getVerticeOrigen(), tunel.getVerticeDestino());
//	}
//
//
//	public boolean hayTunelesDisponibles() {
//		for(Arco <Integer> a: this.tunelesPosibles.keySet()) {
//			if(this.tunelesPosibles.get(a) == false) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//
//
//	public Arco<Integer> getTunelPosible() {
//		for(Arco<Integer> tunel : this.tunelesPosibles.keySet()) {
//			if(tunelesPosibles.get(tunel) == false) {
//				this.tunelesPosibles.put(tunel, true);
//				return tunel;
//			}
//		}
//		
//		return null;//nullpointer seguro
//	}
//
//	public void agregarTunelRutaActual(Arco<Integer> tunel) {
//		Integer etiqueta = (Integer)tunel.getEtiqueta();
//		this.tunelesRutaActual.agregarArco(tunel.getVerticeOrigen(), tunel.getVerticeDestino(), etiqueta);
////		System.out.println(tunel.getVerticeOrigen() + tunel.getVerticeDestino());
//	}
//	
//	public void eliminarTunelRutaActual(Arco<Integer> tunel ) {
//		this.tunelesRutaActual.borrarArco(tunel.getVerticeOrigen(), tunel.getVerticeDestino());
//		
//	}
//
//	public boolean estacionesEstanConectadas() {//Pregunta a todas las estaciones si tienen al menos un arco
//		Iterator<Integer> it = this.tunelesRutaActual.obtenerVertices();
//		HashMap<Integer, Boolean> estacionesVisitadas = new HashMap<>();
//		while(it.hasNext()) {
//			estacionesVisitadas.put(it.next(), false);
//		}
//		
//		it = this.tunelesRutaActual.obtenerVertices();
//		
//		while(it.hasNext()) {
//			if(!dfs(it.next(), estacionesVisitadas)) {
//				return false;
//			}
//			
//			for(Integer est : estacionesVisitadas.keySet()) {
//				estacionesVisitadas.put(est, false);//Reiniciamos el hashmap
//			}
//		}
//		
//		return true;
//	}
//
//	private boolean dfs(Integer estacion, HashMap<Integer, Boolean> estacionesVisitadas) {
//		Iterator<Integer> it = this.tunelesRutaActual.obtenerAdyacentes(estacion);
//		estacionesVisitadas.put(estacion, true);
//		
//		while(it.hasNext()) {
//			Integer adyacente = it.next();
//			if(estacionesVisitadas.get(adyacente)==false) {//Si no fue visitada hace la recursion
//				dfs(adyacente, estacionesVisitadas);
//			}
//		}
//		
//		for(Integer est : estacionesVisitadas.keySet()) {
//			if(estacionesVisitadas.get(est) == false) {
//				return false;
//			}
//		}
//		
//		return true;
//	}
//
//	void inicializarGrafo() {
//		Iterator<Integer> it = estaciones.obtenerVertices();
//		while(it.hasNext()) {
//			this.tunelesRutaActual.agregarVertice(it.next());
//		}
//		
//		LinkedList<Arco<Integer>> arcos = estaciones.obtenerListaArcos();
//		for(Arco<Integer> a : arcos) {
//			this.tunelesPosibles.put(a, false);
//		}
//	}
//
//	public int getCantKmsRutaActual() {
//		return cantKmsRutaActual;
//	}
//	
//	public void sumarCantKmsRutaActual(int kms) {
//		this.cantKmsRutaActual += kms;
//	}
//	public void restarCantKmsRutaActual(int kms) {
//		this.cantKmsRutaActual -= kms;
//	}
//
//	public int getCantKmsMejorSolucion() {
//		return cantKmsMejorSolucion;
//	}
//	
//	public void actualizarCantKmsMejorSolucion() {
//		int aux = this.cantKmsRutaActual;
//		this.cantKmsMejorSolucion = aux;
//	}
//	
//	public void actualizarMejorSolucion() {
//	    GrafoNoDirigido<Integer> grafoClonado = new GrafoNoDirigido<>();
//
//	    // Copiar todos los vértices
//	    Iterator<Integer> vertices = this.tunelesRutaActual.obtenerVertices();
//	    while (vertices.hasNext()) {
//	        Integer vertice = vertices.next();
//	        grafoClonado.agregarVertice(vertice);
//	    }
//
//	    // Copiar todos los arcos
//	    Iterator<Arco<Integer>> arcos = this.tunelesRutaActual.obtenerArcos();
//	    while (arcos.hasNext()) {
//	        Arco<Integer> arco = arcos.next();
//	        Integer origen = arco.getVerticeOrigen();
//	        Integer destino = arco.getVerticeDestino();
//	        Integer etiqueta = arco.getEtiqueta();
//	        grafoClonado.agregarArco(origen, destino, etiqueta);
//	    }
//
//	    this.tunelesMejorSolucion = grafoClonado;
//	}
//
//	public ArrayList<Tunel> getTunelesConstruidos(){
//		ArrayList<Tunel> tuneles = new ArrayList<>();
//		Iterator<Arco<Integer>> it = this.tunelesMejorSolucion.obtenerArcos();
//		while(it.hasNext()) {
//			Arco<Integer> arco = it.next();
//			Tunel tunel = new Tunel(arco.getVerticeOrigen(), arco.getVerticeDestino(), arco.getEtiqueta());
//			if(!tuneles.contains(tunel)) {
//				tuneles.add(tunel);
//			}
//			
//		}
//		
//		return tuneles;
//	}
//
//}
//

