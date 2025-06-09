import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class DatosSobres {
	public static List<Sobre> obtenerSobres() {
		List<Sobre> sobres = new ArrayList<>();

		sobres.add(crear("Sobre Avispa", 50,
				new Object[][] { { "COMÚN", 65.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 18.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 6.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "RADIANTE", 0.1, ColoresCalidad.obtener("RADIANTE") } }));

		sobres.add(crear("Sobre Escorpión", 100,
				new Object[][] { { "COMÚN", 60.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 20.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "DINÁMICA", 5.0, ColoresCalidad.obtener("DINÁMICA") },
						{ "FANÁTICO", 4.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "SOLAR", 0.1, ColoresCalidad.obtener("SOLAR") } }));

		sobres.add(crear("Sobre Serpiente", 150,
				new Object[][] { { "COMÚN", 55.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 20.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "DINÁMICA", 5.0, ColoresCalidad.obtener("DINÁMICA") },
						{ "FANÁTICO", 9.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "LUNAR", 0.1, ColoresCalidad.obtener("LUNAR") } }));

		sobres.add(crear("Sobre Tigre", 200,
				new Object[][] { { "COMÚN", 62.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 18.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 4.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "DINÁMICA", 5.0, ColoresCalidad.obtener("DINÁMICA") },
						{ "MÍTICA", 0.1, ColoresCalidad.obtener("MÍTICA") } }));

		sobres.add(crear("Sobre Fénix", 300,
				new Object[][] { { "COMÚN", 60.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 15.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "DINÁMICA", 10.0, ColoresCalidad.obtener("DINÁMICA") },
						{ "FANÁTICO", 4.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "ICONO", 0.1, ColoresCalidad.obtener("ICONO") } }));

		sobres.add(crear("Sobre Leviatán", 400,
				new Object[][] { { "COMÚN", 58.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 20.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 6.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "MÍTICA", 4.0, ColoresCalidad.obtener("MÍTICA") },
						{ "DINÁMICA", 1.0, ColoresCalidad.obtener("DINÁMICA") },
						{ "AURORA", 0.1, ColoresCalidad.obtener("AURORA") } }));

		sobres.add(crear("Sobre Hydra", 500,
				new Object[][] { { "COMÚN", 60.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 18.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 6.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "DINÁMICA", 5.0, ColoresCalidad.obtener("DINÁMICA") },
						{ "FLASHBACK", 0.1, ColoresCalidad.obtener("FLASHBACK") } }));

		sobres.add(crear("Sobre Quimera", 1000,
				new Object[][] { { "COMÚN", 60.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 18.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 6.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "DINÁMICA", 5.0, ColoresCalidad.obtener("DINÁMICA") },
						{ "FLASHBACK", 0.1, ColoresCalidad.obtener("FLASHBACK") } }));

		sobres.add(crear("Sobre Dragón", 2000,
				new Object[][] { { "COMÚN", 59.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 19.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 6.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "ICONO", 5.0, ColoresCalidad.obtener("ICONO") },
						{ "ECLIPSE", 0.1, ColoresCalidad.obtener("ECLIPSE") } }));

		sobres.add(crear("Sobre Pegaso", 2000,
				new Object[][] { { "COMÚN", 61.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 16.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 5.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "ICONO", 6.0, ColoresCalidad.obtener("ICONO") },
						{ "DINÁMICA", 1.0, ColoresCalidad.obtener("DINÁMICA") },
						{ "SOTS", 0.1, ColoresCalidad.obtener("SOTS") } }));

		sobres.add(crear("Sobre Kraken", 3000,
				new Object[][] { { "COMÚN", 60.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 15.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 8.0, ColoresCalidad.obtener("FANÁTICO") },
						{ "MÍTICA", 6.9, ColoresCalidad.obtener("MÍTICA") },
						{ "NOMINADOS SOTY", 0.1, ColoresCalidad.obtener("NOMINADOS SOTY") } }));

		sobres.add(crear("Sobre Furia", 5000, new Object[][] { { "COMÚN", 58.0, ColoresCalidad.obtener("COMÚN") },
				{ "POCO COMÚN", 18.0, ColoresCalidad.obtener("POCO COMÚN") },
				{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
				{ "FANÁTICO", 7.9, ColoresCalidad.obtener("FANÁTICO") },
				{ "ICONO", 5.0, ColoresCalidad.obtener("ICONO") }, { "SOTY", 0.1, ColoresCalidad.obtener("SOTY") } }));

		sobres.add(crear("Sobre Titán", 10000,
				new Object[][] { { "COMÚN", 60.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 18.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 6.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "MÍTICA", 5.0, ColoresCalidad.obtener("MÍTICA") },
						{ "LEGADO SOTY", 0.1, ColoresCalidad.obtener("LEGADO SOTY") } }));

		sobres.add(crear("Sobre Centauro", 20000,
				new Object[][] { { "COMÚN", 60.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 20.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 4.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "ICONO", 5.0, ColoresCalidad.obtener("ICONO") },
						{ "SUPER FLASHBACK", 0.1, ColoresCalidad.obtener("SUPER FLASHBACK") } }));

		sobres.add(crear("Sobre Coloso", 30000, new Object[][] { { "COMÚN", 62.0, ColoresCalidad.obtener("COMÚN") },
				{ "POCO COMÚN", 17.0, ColoresCalidad.obtener("POCO COMÚN") },
				{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
				{ "FANÁTICO", 5.9, ColoresCalidad.obtener("FANÁTICO") },
				{ "ICONO", 4.0, ColoresCalidad.obtener("ICONO") }, { "AURORA", 1.0, ColoresCalidad.obtener("AURORA") },
				{ "ICONOS MOMENTS", 0.1, ColoresCalidad.obtener("ICONOS MOMENTS") } }));

		sobres.add(crear("Sobre Dios", 50000, new Object[][] { { "COMÚN", 60.0, ColoresCalidad.obtener("COMÚN") },
				{ "POCO COMÚN", 15.0, ColoresCalidad.obtener("POCO COMÚN") },
				{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
				{ "FANÁTICO", 6.9, ColoresCalidad.obtener("FANÁTICO") },
				{ "MÍTICA", 6.0, ColoresCalidad.obtener("MÍTICA") }, { "ICONO", 2.0, ColoresCalidad.obtener("ICONO") },
				{ "ASCENDIDOS", 0.1, ColoresCalidad.obtener("ASCENDIDOS") } }));

		sobres.add(crear("Sobre Dios Omega", 100000,
				new Object[][] { { "COMÚN", 59.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 18.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 7.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "ICONO", 4.0, ColoresCalidad.obtener("ICONO") },
						{ "ASCENDIDOS", 1.0, ColoresCalidad.obtener("ASCENDIDOS") },
						{ "OMEGA", 0.1, ColoresCalidad.obtener("OMEGA") } }));

		sobres.add(crear("Sobre Dios Cósmico", 1000000,
				new Object[][] { { "COMÚN", 60.0, ColoresCalidad.obtener("COMÚN") },
						{ "POCO COMÚN", 15.0, ColoresCalidad.obtener("POCO COMÚN") },
						{ "ESPECIAL", 10.0, ColoresCalidad.obtener("ESPECIAL") },
						{ "FANÁTICO", 6.9, ColoresCalidad.obtener("FANÁTICO") },
						{ "MÍTICA", 5.0, ColoresCalidad.obtener("MÍTICA") },
						{ "OMEGA", 3.0, ColoresCalidad.obtener("OMEGA") },
						{ "CÓSMICA", 0.1, ColoresCalidad.obtener("CÓSMICA") } }));

		return sobres;
	}

	private static Sobre crear(String nombre, int precio, Object[][] datos) {
		String ascii = "  _________\n /       /|\n+-------+ |\n| " + nombre.toUpperCase().replace("SOBRE ", "")
				+ " | |\n|_______|/";
		Sobre s = new Sobre(nombre, precio, ascii);
		for (Object[] fila : datos) {
			String calidad = (String) fila[0];
			double prob = ((Number) fila[1]).doubleValue();
			Color color = (Color) fila[2];
			s.añadirProbabilidad(calidad, prob);
			s.añadirColor(calidad, color);
		}
		return s;
	}
}
