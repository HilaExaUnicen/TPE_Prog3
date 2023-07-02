package entrega2;

import entrega1.GrafoNoDirigido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class Backtracking extends Algoritmo {

	    public Backtracking(GrafoNoDirigido<Integer> grafoEstaciones){
	        super(grafoEstaciones, "Backtracking");
	    }
	    
	    public void construirTuneles(){
	        LinkedList<Integer> estacionesNoVisitadas = new LinkedList<>();
	        this.inicializarListaEstacionesNoVisitadas(estacionesNoVisitadas);
	        Estado estado = new Estado();
	        Iterator<Integer> itEstaciones = estaciones.obtenerVertices();
	        
	        while(itEstaciones.hasNext()){
	            Integer estacion = itEstaciones.next();
	            estacionesNoVisitadas.remove(estacion);
	            backtracking(estado, estacion, null, estacionesNoVisitadas);
	            estacionesNoVisitadas.add(estacion);
	            
	            this.metrica++;
	        }
	        
	        this.imprimirSolucion(this.getNombre(), this.getMejorSolucion(), this.getKmsMejorSolucion(), this.getMetrica());
	    }
	    
	    
	
	    private void backtracking(Estado estado, Integer estacionActual, Integer estacionAnterior, LinkedList<Integer> estacionesNoVisitadas){
	    	estacionesNoVisitadas.remove(estacionActual);
	        if(estado.getDistanciaActual() < this.kmsMejorSolucion && estacionesNoVisitadas.isEmpty()){
	            this.kmsMejorSolucion = estado.getDistanciaActual();
	            this.mejorSolucion = new ArrayList<>(estado.getSolucionActual());
	            return;
	        }
	        else {

		        if(estacionAnterior != null){
		            backtracking(estado, estacionAnterior, null, new LinkedList<>(estacionesNoVisitadas));
		        }
		        
		        for (int estacionNoVisitada : estacionesNoVisitadas) {
		            Tunel nuevoTunel = estaciones.obtenerTunel(estacionActual, estacionNoVisitada);//obtiene el Arco especificado del grafo y lo devuelve como tunel;

		            if (estado.getDistanciaActual() + nuevoTunel.getEtiqueta() < this.kmsMejorSolucion) {//PODA
		                estado.agregarASolucionActual(nuevoTunel);
		                estado.incrementarDistanciaActual(nuevoTunel.getEtiqueta());//incrementarDistActual 
		                backtracking(estado, estacionNoVisitada, estacionActual, new LinkedList<>(estacionesNoVisitadas));
		                estado.decrementarDistanciaActual(nuevoTunel.getEtiqueta());//DecrementarDistActual
		                estado.eliminarDeSolucionActual();
		            }
		            
		            this.metrica++;
		        }
	        }
	        
	        this.metrica++;
	    }
	    
		private void inicializarListaEstacionesNoVisitadas(LinkedList <Integer> lista){
			Iterator<Integer> itEstaciones = estaciones.obtenerVertices();
	        while(itEstaciones.hasNext()){
	            Integer v = itEstaciones.next();
	            if(!lista.contains(v)) {
	            	lista.add(v);
	            }
	        }
        }
		

		//Estado backtracking
		private class Estado{
	        private ArrayList<Tunel> solucionActual;
	        private int distanciaActual;

			public Estado() {
				this.solucionActual = new ArrayList<>() ;
				this.distanciaActual = 0;
			}

	        public void eliminarDeSolucionActual() {
	        	this.solucionActual.remove(this.solucionActual.size()-1);
			}

			public void agregarASolucionActual(Tunel nuevoTunel) {
				this.solucionActual.add(nuevoTunel);
			}
			
			public void incrementarDistanciaActual(int kms) {
				this.distanciaActual += kms;
			}
			
			public void decrementarDistanciaActual(int kms) {
				this.distanciaActual -= kms;
			}

			//GETTERS Y SETTERS
	        public int getDistanciaActual() {
				return distanciaActual;
			}

			public ArrayList<Tunel> getSolucionActual() {
				ArrayList<Tunel> copia = new ArrayList<>(this.solucionActual);
				return copia;
			}			
		}
}




