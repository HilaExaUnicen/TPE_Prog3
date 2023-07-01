package entrega2;

import java.util.ArrayList;

import entrega1.GrafoNoDirigido;

public abstract class Algoritmo {
	protected int metrica;
	protected GrafoNoDirigido<Integer> estaciones;
	protected int kmsMejorSolucion;
	protected ArrayList<Tunel> mejorSolucion;
	private String nombre;
	
	
	public Algoritmo(GrafoNoDirigido<Integer> estaciones2, String nombre) {
		this.metrica = 0;
		this.estaciones = estaciones2;
		this.nombre = nombre;
		this.kmsMejorSolucion = Integer.MAX_VALUE;
		this.mejorSolucion = new ArrayList<>();
	}
	
	public abstract void construirTuneles();//Metodo publico que los algoritmos deben implementar para encontrar la solucion

	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<Tunel> getMejorSolucion(){
		ArrayList<Tunel> copia = new ArrayList<>(this.mejorSolucion);
		return copia;
	}

	public int getKmsMejorSolucion() {
		return kmsMejorSolucion;
	}
	
	public int getMetrica() {
		return metrica;
	}
	
	protected void imprimirSolucion(String tecnicaUtilizada, ArrayList<Tunel> rutasConstruidas, int kmsConstruidosTotal, int metrica) {
		System.out.println(tecnicaUtilizada);
		for(Tunel tunel : rutasConstruidas) {
			System.out.print("[E" + tunel.getOrigen() + ", " + "E" + tunel.getDestino() +  "]");
		}
		System.out.println(" ");
		System.out.println(kmsConstruidosTotal + " kms");
		System.out.println(metrica + " iteraciones");
		System.out.println(" ");
	}
}
