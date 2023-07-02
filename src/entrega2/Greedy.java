package entrega2;

import java.util.ArrayList;
import java.util.Iterator;

import entrega1.Arco;
import entrega1.GrafoNoDirigido;

public class Greedy extends Algoritmo {

	public Greedy(GrafoNoDirigido<Integer> grafoEstaciones) {
		super(grafoEstaciones, "Greedy");
	}
	
	public void construirTuneles() {
		ArrayList<Tunel> rutaActual = new ArrayList<>();
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
				this.metrica++;
			}
			
			int kmsTotalesRutaActual = this.getKmsTotalesRuta(rutaActual);
			if(kmsTotalesRutaActual < kmsMejorSolucion) {
				kmsMejorSolucion = kmsTotalesRutaActual;
				mejorSolucion = new ArrayList<>(rutaActual);
			}
			
			s.clear();
			rutaActual.clear();
			this.metrica++;
		}
		
		imprimirSolucion(this.getNombre(), this.getMejorSolucion(), this.getKmsMejorSolucion(), this.getMetrica());//Falta la metrica
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
			
			this.metrica++;
		}
		
		return estacionMasProxima;
	}
}
