import java.io.Serializable;

public class Carta implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String calidad;
	private final String ascii;
	private final String nombre;
	private final int media;
	private final int estudios;
	private final int hobbies;
	private final int popularidad;
	private final int bondad;
	private final int suerte;
	private final int momento;

	public Carta(String calidad, String ascii, String nombre, int media, int estudios, int hobbies, int popularidad,
			int bondad, int suerte, int momento) {
		this.calidad = calidad;
		this.ascii = ascii;
		this.nombre = nombre;
		this.media = media;
		this.estudios = estudios;
		this.hobbies = hobbies;
		this.popularidad = popularidad;
		this.bondad = bondad;
		this.suerte = suerte;
		this.momento = momento;
	}

	public String getCalidad() {
		return calidad;
	}

	public String getAscii() {
		return ascii;
	}

	public String getNombre() {
		return nombre;
	}

	public int getMedia() {
		return media;
	}

	public int getEstudios() {
		return estudios;
	}

	public int getHobbies() {
		return hobbies;
	}

	public int getPopularidad() {
		return popularidad;
	}

	public int getBondad() {
		return bondad;
	}

	public int getSuerte() {
		return suerte;
	}

	public int getMomento() {
		return momento;
	}

	public String getResumenTitulo() {
		return nombre + " - " + media + " - " + calidad;
	}
}
