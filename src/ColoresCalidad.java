import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColoresCalidad {
	private static final Map<String, Color> mapa = new HashMap<>();

	static {
		mapa.put("COMÚN", Color.WHITE);
		mapa.put("POCO COMÚN", new Color(144, 238, 144)); // VERDE CLARO
		mapa.put("ESPECIAL", Color.GREEN);
		mapa.put("DINÁMICA", Color.BLUE);
		mapa.put("FANÁTICO", Color.MAGENTA);
		mapa.put("RADIANTE", Color.YELLOW);
		mapa.put("SOLAR", new Color(255, 215, 0)); // Amarillo/Naranja
		mapa.put("LUNAR", new Color(25, 25, 112)); // Azul oscuro
		mapa.put("MÍTICA", Color.ORANGE);
		mapa.put("ICONO", new Color(255, 255, 153)); // Amarillo claro
		mapa.put("AURORA", new Color(169, 169, 169)); // Gris
		mapa.put("FLASHBACK", new Color(255, 102, 102)); // Rojo claro
		mapa.put("NOMINADOS SOTS", new Color(135, 206, 250)); // Azul claro
		mapa.put("ECLIPSE", new Color(128, 0, 0)); // Negro/Naranja
		mapa.put("SOTS", new Color(30, 144, 255)); // Azul
		mapa.put("NOMINADOS SOTY", new Color(0, 0, 139)); // Azul oscuro
		mapa.put("LEGADO SOTY", new Color(0, 0, 139));
		mapa.put("SOTY", new Color(0, 0, 139));
		mapa.put("SUPER FLASHBACK", Color.RED);
		mapa.put("ICONOS MOMENTS", new Color(255, 255, 102));
		mapa.put("ASCENDIDOS", new Color(64, 224, 208)); // Turquesa
		mapa.put("OMEGA", Color.DARK_GRAY);
		mapa.put("CÓSMICA", new Color(153, 50, 204)); // Morado oscuro
	}

	public static Color obtener(String calidad) {
		return mapa.getOrDefault(calidad.toUpperCase(), Color.WHITE);
	}
}