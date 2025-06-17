import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DatosCartas {

	public static List<List<Carta>> obtenerCartasPorCalidad() {
		List<List<Carta>> todasLasListas = new ArrayList<>();

		List<Carta> comunes = new ArrayList<>();
		List<Carta> pocoComunes = new ArrayList<>();
		List<Carta> especiales = new ArrayList<>();
		List<Carta> fanaticos = new ArrayList<>();
		List<Carta> dinamicas = new ArrayList<>();
		List<Carta> iconos = new ArrayList<>();
		List<Carta> radiantes = new ArrayList<>();
		List<Carta> solares = new ArrayList<>();
		List<Carta> lunares = new ArrayList<>();
		List<Carta> miticas = new ArrayList<>();
		List<Carta> auroras = new ArrayList<>();
		List<Carta> flashbacks = new ArrayList<>();
		List<Carta> nominadosSOTS = new ArrayList<>();
		List<Carta> eclipse = new ArrayList<>();
		List<Carta> sots = new ArrayList<>();
		List<Carta> nominadosSOTY = new ArrayList<>();
		List<Carta> legadoSOTY = new ArrayList<>();
		List<Carta> soty = new ArrayList<>();
		List<Carta> superFlashback = new ArrayList<>();
		List<Carta> iconosMoments = new ArrayList<>();
		List<Carta> ascendidos = new ArrayList<>();
		List<Carta> omega = new ArrayList<>();
		List<Carta> cosmicas = new ArrayList<>();
		List<Carta> cupones = new ArrayList<>();

		// ASCII son 45 = ASCIIART

		comunes.add(new Carta("COMÚN", 
				  "                   ...::...                  \r\n"
				+ "           .:-=*#%%@@%###%%###*=-:..         \r\n"
				+ "   .+*#%@@@@@@@@@@@@@%*=#%%%%%%%@@@@@@%%#*.  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@@@%@@%%%%@@@@@@@%%%%%:  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@@%%@%%%%@@@@@@@%%%%%%:  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@%@@%%%%@@@@@@@@%%%%%%:  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@%%%%%%@@@@@@@@%%%%%%%:  \r\n"
				+ "   :#@@@@@@@@@@@@@@%@@%%@%@@@@@@@@@%%%%%#%:  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@%%%%@@@@@@@@@%%%%%*+%:  \r\n"
				+ "   :#@@@@@@@@@@@@@@@%%%%@@@@@@@@@@%%%%*++%:  \r\n"
				+ "   :#@@@@@@@@@@@@@@%%%%@@@@@@@@@@%%%%+++*%:  \r\n"
				+ "   :#@@@@@@@@@@@@@%%%%@@@@@@@@@@%%%%++++*%:  \r\n"
				+ "   :#@@@@@@@@@@@@%%%%@@@@@@@@@@%%%%+++++#%:  \r\n"
				+ "   :#@@@@@@@@@@%%%%%@@@@@@@@@@@%%%++++**#%:  \r\n"
				+ "   :#@@@@@@@@@%%%%%@@@@@@@@@@%%%%++++****%:  \r\n"
				+ "   :#@@@@@@%%%%%%%@@@@@@@@@@@%%%+++*****#%:  \r\n"
				+ "   :#@@@@@@@%%%%%@@@@@@@@@@%%%#+++++*****%:  \r\n"
				+ "   :#@@@@@@@@%%%@@@@@@@@@@%%%#++++*******#:  \r\n"
				+ "   :#@@@@@@@@@%@@@@@@@@@@%%%#==+++++++++*#:  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@@@%%%%#====+++++++=+#.  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@@@%%%#=============+#.  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%#%%%%.  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%#%%.  \r\n"
				+ "   :#@@@@@@@@@@@@@@@@@%%%%%%%%%%%%#######%.  \r\n"
				+ "   :#@@@@@@@@@@@@%%@%%%%%%%%%%%%%#########.  \r\n"
				+ "   :#@@@@@@@@@@@@%%%%%%%%%%###############.  \r\n"
				+ "   :#@@@@@@@@@%%%%%%%%%%%%%############*##.  \r\n"
				+ "   :#@@@@@@%%%%%%%%%%*++*###########****##.  \r\n"
				+ "   :+#@@@%%%%%%%%%%#=****=+######******##*.  \r\n"
				+ "   ...:--=+**#%%%%%*==++==+#####**++=--:...  \r\n"
				+ "            ..:--=+*+----+*+=--:..           \r\n" + 
				  "                   ...--...                  ",
				"Pucci", 86, 51, 89, 90, 86, 89, 80));

		todasLasListas.add(comunes);
		todasLasListas.add(pocoComunes);
		todasLasListas.add(especiales);
		todasLasListas.add(fanaticos);
		todasLasListas.add(dinamicas);
		todasLasListas.add(iconos);
		todasLasListas.add(radiantes);
		todasLasListas.add(solares);
		todasLasListas.add(lunares);
		todasLasListas.add(miticas);
		todasLasListas.add(auroras);
		todasLasListas.add(flashbacks);
		todasLasListas.add(nominadosSOTS);
		todasLasListas.add(eclipse);
		todasLasListas.add(sots);
		todasLasListas.add(nominadosSOTY);
		todasLasListas.add(legadoSOTY);
		todasLasListas.add(soty);
		todasLasListas.add(superFlashback);
		todasLasListas.add(iconosMoments);
		todasLasListas.add(ascendidos);
		todasLasListas.add(omega);
		todasLasListas.add(cosmicas);
		todasLasListas.add(cupones);

		return todasLasListas;
	}

	public static Carta obtenerCartaAleatoriaPorCalidad(String calidad) {
		Map<String, Integer> mapaIndices = new HashMap<>();
		mapaIndices.put("COMÚN", 0);
		mapaIndices.put("POCO COMÚN", 1);
		mapaIndices.put("ESPECIAL", 2);
		mapaIndices.put("FANÁTICO", 3);
		mapaIndices.put("DINÁMICA", 4);
		mapaIndices.put("ICONO", 5);
		mapaIndices.put("RADIANTE", 6);
		mapaIndices.put("SOLAR", 7);
		mapaIndices.put("LUNAR", 8);
		mapaIndices.put("MÍTICA", 9);
		mapaIndices.put("AURORA", 10);
		mapaIndices.put("FLASHBACK", 11);
		mapaIndices.put("NOMINADO SOTS", 12);
		mapaIndices.put("ECLIPSE", 13);
		mapaIndices.put("SOTS", 14);
		mapaIndices.put("NOMINADO SOTY", 15);
		mapaIndices.put("LEGADO SOTY", 16);
		mapaIndices.put("SOTY", 17);
		mapaIndices.put("SUPER FLASHBACK", 18);
		mapaIndices.put("ICONO MOMENTS", 19);
		mapaIndices.put("ASCENDIDO", 20);
		mapaIndices.put("OMEGA", 21);
		mapaIndices.put("CÓSMICA", 22);
		mapaIndices.put("???", 23);

		Integer indice = mapaIndices.get(calidad.toUpperCase());
		if (indice == null)
			return null;

		List<List<Carta>> porCalidad = obtenerCartasPorCalidad();
		List<Carta> cartasDeCalidad = porCalidad.get(indice);

		if (cartasDeCalidad.isEmpty())
			return null;

		Random rand = new Random();
		return cartasDeCalidad.get(rand.nextInt(cartasDeCalidad.size()));
	}

	public static List<String> obtenerTodasLasCalidades() {
		return List.of("COMÚN", "POCO COMÚN", "ESPECIAL", "DINÁMICA", "FANÁTICO", "RADIANTE", "SOLAR", "LUNAR",
				"MÍTICA", "ICONO", "AURORA", "FLASHBACK", "NOMINADOS SOTS", "ECLIPSE", "SOTS", "NOMINADOS SOTY",
				"LEGADO SOTY", "SOTY", "SUPER FLASHBACK", "ICONOS MOMENTS", "ASCENDIDOS", "OMEGA", "CÓSMICA", "???");
	}

	public static List<Carta> obtenerCartasPorCalidad(String calidad) {
		int index = obtenerTodasLasCalidades().indexOf(calidad.toUpperCase());
		if (index == -1)
			return new ArrayList<>();
		return obtenerCartasPorCalidad().get(index);
	}
}
