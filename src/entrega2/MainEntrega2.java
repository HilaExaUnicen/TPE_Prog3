package entrega2;

public class MainEntrega2 {
	public static void main(String[] args) {
		String ruta = "../tpe_prog3/datasets/dataset1.txt";//Con esta ruta deberia andar, si no copiar la ruta completa desde el archivo .txt
		CSVReader lector = new CSVReader(ruta);
		lector.read();
	}
}
