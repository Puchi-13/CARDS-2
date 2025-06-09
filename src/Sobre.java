import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

public class Sobre {
	private String nombre;
	private int precio;
	private String ascii;
	private Map<String, Double> probabilidades;
	private Map<String, Color> colores;

	public Sobre(String nombre, int precio, String ascii) {
		this.nombre = nombre;
		this.precio = precio;
		this.ascii = ascii;
		this.probabilidades = new LinkedHashMap<>();
		this.colores = new LinkedHashMap<>();
	}

	public void añadirProbabilidad(String calidad, double porcentaje) {
		probabilidades.put(calidad, porcentaje);
	}

	public void añadirColor(String calidad, Color color) {
		colores.put(calidad, color);
	}

	public String getNombre() {
		return nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public String getAscii() {
		return ascii;
	}

	public Map<String, Double> getProbabilidades() {
		return probabilidades;
	}

	public Map<String, Color> getColores() {
		return colores;
	}
}
