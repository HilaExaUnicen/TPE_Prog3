package entrega2;

import java.util.ArrayList;

import entrega1.Grafo;

public abstract class Algoritmo {
	protected int metrica;
	protected Grafo<?> estaciones;
	
	public Algoritmo(Grafo<?> estaciones) {
		this.metrica = 0;
		this.estaciones = estaciones;
	}
	
	public abstract void construirTuneles();

	protected void imprimirSolucion(String tecnicaUtilizada, ArrayList<Tunel> rutasConstruidas, int kmsConstruidosTotal) {
		System.out.println(tecnicaUtilizada);
		for(Tunel tunel : rutasConstruidas) {
			System.out.print("[E" + tunel.getOrigen() + ", " + "E" + tunel.getDestino() +  "]");
		}
		System.out.println(" ");
		System.out.println(kmsConstruidosTotal + " kms");
		System.out.println("X metrica"); //TODO A implementar	
		System.out.println(" ");
	}
}
