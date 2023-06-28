package entrega2;

import java.util.ArrayList;

import entrega1.GrafoNoDirigido;

public abstract class Algoritmo {
	protected int metrica;
	protected GrafoNoDirigido<?> estaciones;
	
	public Algoritmo(GrafoNoDirigido<?> estaciones2) {
		this.metrica = 0;
		this.estaciones = estaciones2;
	}
	
	public abstract void construirTuneles();

	protected void imprimirSolucion(String tecnicaUtilizada, ArrayList<Tunel> rutasConstruidas, int kmsConstruidosTotal, int metrica) {
		System.out.println(tecnicaUtilizada);
		for(Tunel tunel : rutasConstruidas) {
			System.out.print("[E" + tunel.getOrigen() + ", " + "E" + tunel.getDestino() +  "]");
		}
		System.out.println(" ");
		System.out.println(kmsConstruidosTotal + " kms");
		System.out.println(metrica + " iteraciones"); //TODO A implementar	
		System.out.println(" ");
	}
}
