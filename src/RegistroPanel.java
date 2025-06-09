import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class RegistroPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final CARDS2 main;

	public RegistroPanel(CARDS2 mainWindow) {
		this.main = mainWindow;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel titulo = new JLabel("REGISTRO DE CARTAS", SwingConstants.CENTER);
		titulo.setFont(new Font("Monospaced", Font.BOLD, 28));
		titulo.setForeground(Color.DARK_GRAY);
		titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		add(titulo, BorderLayout.NORTH);

		List<String> calidades = DatosCartas.obtenerTodasLasCalidades();
		int columnas = 3;
		int filas = (int) Math.ceil(calidades.size() / (double) columnas);
		JPanel botonesPanel = new JPanel(new GridLayout(filas, columnas, 10, 10));
		botonesPanel.setBackground(Color.BLACK);

		Map<String, Set<Carta>> cartasJugador = mainWindow.getCartaGuardada().getCartasPorCalidad();

		for (String calidad : calidades) {
			List<Carta> cartasDisponibles = DatosCartas.obtenerCartasPorCalidad(calidad);
			int total = cartasDisponibles.size();
			int conseguidas = (int) cartasDisponibles.stream().filter(c -> cartasJugador
					.getOrDefault(calidad, new HashSet<>()).stream().anyMatch(j -> j.getNombre().equals(c.getNombre())))
					.count();

			String texto = total > 0
					? String.format("%s (%d%%)", calidad.toUpperCase(), (int) ((conseguidas * 100.0) / total))
					: String.format("%s (X)", calidad.toUpperCase()); // ← cambio aquí

			Color color = ColoresCalidad.obtener(calidad);

			JButton boton = new JButton(texto);
			boton.setFont(new Font("Monospaced", Font.PLAIN, 14));
			boton.setForeground(color);
			boton.setBackground(Color.BLACK);
			boton.setBorder(BorderFactory.createLineBorder(color, 2));
			boton.setOpaque(true);
			boton.setContentAreaFilled(true);
			boton.setFocusPainted(false);
			boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // ← se adapta a ancho

			boton.addActionListener(e -> mostrarCartasDeCalidad(calidad, cartasDisponibles,
					cartasJugador.getOrDefault(calidad, new HashSet<>())));

			botonesPanel.add(boton);
		}

		JScrollPane scroll = new JScrollPane(botonesPanel);
		scroll.setBorder(null);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll, BorderLayout.CENTER);

		JButton volver = new JButton("Volver al Menú Principal");
		volver.addActionListener(e -> main.volverAlMenu());
		JPanel sur = new JPanel();
		sur.setBackground(Color.BLACK);
		sur.add(volver);
		add(sur, BorderLayout.SOUTH);
	}

	private void mostrarCartasDeCalidad(String calidad, List<Carta> todas, Set<Carta> cartasJugador) {
		removeAll();
		setLayout(new BorderLayout());

		JLabel subtitulo = new JLabel("CALIDAD: " + calidad.toUpperCase(), SwingConstants.CENTER);
		subtitulo.setFont(new Font("Monospaced", Font.BOLD, 22));
		subtitulo.setForeground(ColoresCalidad.obtener(calidad));
		subtitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		add(subtitulo, BorderLayout.NORTH);

		JPanel lista = new JPanel();
		lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
		lista.setBackground(Color.BLACK);

		for (Carta carta : todas) {
			JPanel fila = new JPanel(new BorderLayout());
			fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
			fila.setBackground(Color.BLACK);
			fila.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

			boolean conseguida = cartasJugador.stream().anyMatch(c -> c.getNombre().equals(carta.getNombre()));

			JLabel nombre = new JLabel(conseguida ? carta.getNombre() : "???");
			nombre.setForeground(conseguida ? Color.WHITE : Color.GRAY);
			nombre.setFont(new Font("Monospaced", Font.BOLD, 14));

			JButton estadoBtn = new JButton(conseguida ? "CONSEGUIDA" : "NO CONSEGUIDA");
			estadoBtn.setPreferredSize(new Dimension(150, 30));
			estadoBtn.setBackground(Color.BLACK);
			estadoBtn.setForeground(conseguida ? Color.GREEN : Color.DARK_GRAY);
			estadoBtn.setFocusPainted(false);
			estadoBtn.setEnabled(false);

			JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			derecha.setBackground(Color.BLACK);
			derecha.add(estadoBtn);

			fila.add(nombre, BorderLayout.WEST);
			fila.add(derecha, BorderLayout.EAST);
			fila.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(50, 50, 50)));
			lista.add(fila);
		}

		if (todas.isEmpty()) {
			JLabel vacio = new JLabel("No hay cartas registradas para esta calidad.", SwingConstants.CENTER);
			vacio.setForeground(Color.LIGHT_GRAY);
			vacio.setFont(new Font("Monospaced", Font.ITALIC, 14));
			vacio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lista.add(vacio);
		}

		JScrollPane scroll = new JScrollPane(lista);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll, BorderLayout.CENTER);

		JButton volver = new JButton("Volver al Registro");
		volver.addActionListener(e -> main.mostrarRegistro());
		JPanel sur = new JPanel();
		sur.setBackground(Color.BLACK);
		sur.add(volver);
		add(sur, BorderLayout.SOUTH);

		revalidate();
		repaint();
	}
}
