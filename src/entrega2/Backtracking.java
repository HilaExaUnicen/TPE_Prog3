package entrega2;

import java.util.ArrayList;
import java.util.Iterator;

import entrega1.Grafo;
import entrega1.GrafoNoDirigido;

public class Backtracking extends Algoritmo {
	
	public Backtracking(Grafo<?> estaciones) {
		super(estaciones);
	}

	public void construirTuneles() {
		Estado estado = new Estado();
		Iterator<Integer> itEstaciones = this.estaciones.obtenerVertices();
		
		while(itEstaciones.hasNext()) {
			Integer estacionOrigen = itEstaciones.next();
			backtracka(estacionOrigen, estado);
		}
		
		
		super.imprimirSolucion("Backtracking", estado.getMejorRuta(), estado.getCantKmsMejorSolucion());
		
		
	}

	private void backtracka(Integer estacionOrigen, Estado estado) {
//		if(estado.estacionesEstanConectadas(this.estaciones.obtenerVertices())) {
//			
//		}
//		else {
			if(estado.esEstadoFinal(this.estaciones.obtenerVertices())) {
				if(estado.getCantKmsRutaActual() < estado.getCantKmsMejorSolucion()) {
					estado.setMejorRuta();
					estado.setCantKmsMejorSolucion();
				}
			}
			else {
				Iterator<Integer> estacionesAdyacentes = this.estaciones.obtenerAdyacentes(estacionOrigen);
				while(estacionesAdyacentes.hasNext()) {
					Integer estAdyacente = estacionesAdyacentes.next();
					Tunel nuevoTunel = new Tunel(estacionOrigen, estAdyacente, (int)this.estaciones.obtenerArco(estacionOrigen, estAdyacente).getEtiqueta());//Etiqueta deberia obtenerse de estado.
					
					if(!estado.rutaActualContieneTunel(nuevoTunel)) {
						estado.addTunelRutaActual(nuevoTunel);
						estado.sumarKmsRutaActual(nuevoTunel.getEtiqueta());
						backtracka(estAdyacente, estado);
						estado.removeTunelRutaActual();
						estado.restarKmsRutaActual(nuevoTunel.getEtiqueta());
					}
				}
			}
//		}
	}
		
	private class Estado{
		private ArrayList<Tunel> rutaActual = new ArrayList<>();
		private ArrayList<Tunel> mejorRuta = new ArrayList<>();
		private int cantKmsRutaActual;
		private int cantKmsMejorSolucion;
		private GrafoNoDirigido<?> tuneles;
		//private int metricaCantIteraciones;
		
		public Estado() {
			rutaActual = new ArrayList<>();
			mejorRuta = new ArrayList<>();
			cantKmsRutaActual = 0;
			cantKmsMejorSolucion = Integer.MAX_VALUE;
			tuneles = new GrafoNoDirigido<>();
			//metricaCantIteraciones = 0;
		}
		
		
		
		public boolean rutaActualContieneTunel(Tunel nuevoTunel) {
			return this.rutaActual.contains(nuevoTunel);
		}
		
		public void addTunelRutaActual(Tunel tunel) {
			this.rutaActual.add(tunel);
		}
		
		public void removeTunelRutaActual() {
			this.rutaActual.remove(rutaActual.size()-1);
		}

		public ArrayList<Tunel> getMejorRuta() {
			return new ArrayList<>(mejorRuta);
		}

		public void setMejorRuta() {
			this.mejorRuta = new ArrayList<>(rutaActual);
		}

//		public ArrayList<Tunel> getRutaActual() {
//			return rutaActual;
//		}

		public int getCantKmsRutaActual() {
			return cantKmsRutaActual;
		}
		
		public void sumarKmsRutaActual(int kms) {
			this.cantKmsRutaActual += kms;//TODO faltaria validacion > 0
		}
		
		public void restarKmsRutaActual(int kms) {
			this.cantKmsRutaActual -= kms;
		}

		public int getCantKmsMejorSolucion() {
			return cantKmsMejorSolucion;
		}
		
		public void setCantKmsMejorSolucion() {
			int aux = this.cantKmsRutaActual;
			this.cantKmsMejorSolucion = aux;
		}

		public boolean esEstadoFinal(Iterator<Integer> estaciones) {
			while(estaciones.hasNext()) {
				Integer estacion = estaciones.next();
				if(!rutaActualTieneEstacion(estacion)) {
					return false;
				}
			}
			
			return true;
		}
		
		public boolean rutaActualTieneEstacion(Integer estacion) {
			for(Tunel t : rutaActual) {
				if(t.getOrigen() == estacion || t.getDestino() == estacion) {
					return true;
				}
			}
			
			return false;
		}
	}
}
