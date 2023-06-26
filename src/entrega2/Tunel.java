package entrega2;

public class Tunel {
	private Integer origen;
	private Integer destino;
	private Integer etiqueta;

	public Tunel(Integer origen, Integer destino, Integer etiqueta) {
		this.origen = origen;
		this.destino = destino;
		this.etiqueta = etiqueta;
	}

	public Integer getOrigen() {
		return origen;
	}

	public Integer getDestino() {
		return destino;
	}

	public Integer getEtiqueta() {
		return etiqueta;
	}
	
	public boolean equals(Object o) {
		try {
			Tunel otro = (Tunel) o;
			return (this.origen.equals(otro.getOrigen()) && this.destino.equals(otro.getDestino())) 
					|| 
				   (this.origen.equals(otro.getDestino()) && this.destino.equals(otro.getOrigen()));
		}
		catch (Exception e){
			System.out.println(e);
			return false;
		}
	}
}
