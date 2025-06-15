import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class MisCartasPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private final CARDS2 main;

	private static final String[] CALIDADES = { "COMÚN", "POCO COMÚN", "ESPECIAL", "DINÁMICA", "FANÁTICO", "RADIANTE",
			"SOLAR", "LUNAR", "MÍTICA", "ICONO", "AURORA", "FLASHBACK", "NOMINADOS SOTS", "ECLIPSE", "SOTS",
			"NOMINADOS SOTY", "LEGADO SOTY", "SOTY", "SUPER FLASHBACK", "ICONOS MOMENTS", "ASCENDIDOS", "OMEGA",
			"CÓSMICA", "???" };

	public MisCartasPanel(CARDS2 mainWindow, Map<String, Set<Carta>> cartasPorCalidad) {
		this.main = mainWindow;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel titulo = new JLabel("MIS CARTAS", SwingConstants.CENTER);
		titulo.setFont(new Font("Monospaced", Font.BOLD, 28));
		titulo.setForeground(Color.DARK_GRAY);
		titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		add(titulo, BorderLayout.NORTH);

		int columnas = 3;
		int filas = (int) Math.ceil(CALIDADES.length / (double) columnas);
		JPanel botonesCalidad = new JPanel(new GridLayout(filas, columnas, 10, 10));
		botonesCalidad.setBackground(Color.BLACK);

		for (String calidad : CALIDADES) {
			JButton boton = crearBotonCalidad(calidad, cartasPorCalidad);
			botonesCalidad.add(boton);
		}

		JScrollPane scroll = new JScrollPane(botonesCalidad);
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

	private JButton crearBotonCalidad(String calidad, Map<String, Set<Carta>> cartasPorCalidad) {
		Color color = ColoresCalidad.obtener(calidad);
		JButton boton = new JButton(calidad);
		boton.setFont(new Font("Monospaced", Font.PLAIN, 14));
		boton.setBackground(Color.BLACK);
		boton.setForeground(color);
		boton.setBorder(BorderFactory.createLineBorder(color, 2));
		boton.setFocusPainted(false);
		boton.addActionListener((ActionEvent e) -> mostrarCartasDeCalidad(calidad, cartasPorCalidad));
		return boton;
	}

	private void mostrarCartasDeCalidad(String calidad, Map<String, Set<Carta>> cartasPorCalidad) {
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

		Set<Carta> cartas = cartasPorCalidad.getOrDefault(calidad, new HashSet<>());

		if (cartas.isEmpty()) {
			JLabel vacio = new JLabel("No tienes cartas de esta calidad.", SwingConstants.CENTER);
			vacio.setForeground(Color.LIGHT_GRAY);
			vacio.setFont(new Font("Monospaced", Font.ITALIC, 14));
			vacio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lista.add(vacio);
		} else {
			for (Carta carta : cartas) {
				JPanel fila = new JPanel(new BorderLayout());
				fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
				fila.setBackground(Color.BLACK);
				fila.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

				Color color = ColoresCalidad.obtener(carta.getCalidad());
				JLabel nombre = new JLabel(carta.getNombre() + " - " + carta.getMedia());
				nombre.setForeground(color);
				nombre.setFont(new Font("Monospaced", Font.BOLD, 14));

				JButton verBtn = new JButton("Ver Carta");
				verBtn.addActionListener(e -> main.mostrarVentanaCarta(carta));
				verBtn.setPreferredSize(new Dimension(120, 30));

				JButton venderBtn = new JButton("VENDER");
				venderBtn.setPreferredSize(new Dimension(120, 30));
				venderBtn.addActionListener(e -> {
					int confirm = JOptionPane.showConfirmDialog(this,
							"¿Seguro que quieres vender esta carta por "
									+ String.format("%.2f", MisSobresPanel.calcularPrecioVenta(carta)) + "€?",
							"Confirmar Venta", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						double ganancia = MisSobresPanel.calcularPrecioVenta(carta);
						main.sumarDinero(ganancia);
						JOptionPane.showMessageDialog(this,
								"Has vendido la carta por " + String.format("%.2f", ganancia) + "€.");
						main.getCartaGuardada().getCartasPorCalidad().getOrDefault(carta.getCalidad(), new HashSet<>())
								.remove(carta);
						main.getCartaGuardada().guardarEnArchivo();
						mostrarCartasDeCalidad(carta.getCalidad(), main.getCartaGuardada().getCartasPorCalidad());
					}
				});

				JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				derecha.setBackground(Color.BLACK);
				derecha.add(verBtn);
				derecha.add(venderBtn);

				fila.add(nombre, BorderLayout.WEST);
				fila.add(derecha, BorderLayout.EAST);
				fila.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(50, 50, 50)));
				lista.add(fila);
			}
		}

		JScrollPane scroll = new JScrollPane(lista);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll, BorderLayout.CENTER);

		JButton volver = new JButton("Volver a Calidades");
		volver.addActionListener(e -> main.mostrarMisCartas());
		JPanel sur = new JPanel();
		sur.setBackground(Color.BLACK);
		sur.add(volver);
		add(sur, BorderLayout.SOUTH);

		revalidate();
		repaint();
	}

	public static class CartaGuardada implements Serializable {
		private static final long serialVersionUID = 1L;
		public final Map<String, Set<Carta>> cartas = new HashMap<>();

		public void añadirCarta(Carta carta) {
			cartas.computeIfAbsent(carta.getCalidad(), k -> new HashSet<>()).add(carta);
		}

		public Map<String, Set<Carta>> getCartasPorCalidad() {
			return cartas;
		}

		public void guardarEnArchivo() {
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cartas_guardadas.ser"))) {
				out.writeObject(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static CartaGuardada cargarDesdeArchivo() {
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("cartas_guardadas.ser"))) {
				return (CartaGuardada) in.readObject();
			} catch (IOException | ClassNotFoundException e) {
				return new CartaGuardada();
			}
		}
	}
}