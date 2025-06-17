import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;

public class MisSobresPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final Font fuenteBoton = new Font("Monospaced", Font.BOLD, 12);

	private JButton crearBotonEstilizado(String texto, java.awt.event.ActionListener accion) {
		JButton boton = new JButton(texto);
		boton.setUI(new BasicButtonUI());
		boton.setBackground(Color.BLACK);
		boton.setForeground(Color.WHITE);
		boton.setFont(fuenteBoton);
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		boton.setOpaque(true);
		boton.setContentAreaFilled(true);
		boton.addActionListener(accion);
		return boton;
	}

	public MisSobresPanel(CARDS2 mainWindow, List<Sobre> sobresComprados) {
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel titulo = new JLabel("MIS SOBRES", SwingConstants.CENTER);
		titulo.setFont(new Font("Monospaced", Font.BOLD, 28));
		titulo.setForeground(Color.DARK_GRAY);
		titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		add(titulo, BorderLayout.NORTH);

		JPanel listaPanel = new JPanel();
		listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
		listaPanel.setBackground(Color.BLACK);

		JScrollPane scroll = new JScrollPane(listaPanel);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		add(scroll, BorderLayout.CENTER);

		JButton volverBtn = crearBotonEstilizado("Volver al Menú Principal", e -> mainWindow.volverAlMenu());
		volverBtn.setPreferredSize(new Dimension(200, 40));

		JPanel sur = new JPanel();
		sur.setBackground(Color.BLACK);
		sur.add(volverBtn);
		add(sur, BorderLayout.SOUTH);

		actualizarLista(listaPanel, sobresComprados, mainWindow);
	}

	private void actualizarLista(JPanel listaPanel, List<Sobre> sobresComprados, CARDS2 mainWindow) {
		listaPanel.removeAll();

		if (sobresComprados.isEmpty()) {
			JLabel vacio = new JLabel("No has comprado sobres aún.");
			vacio.setForeground(Color.LIGHT_GRAY);
			vacio.setFont(new Font("Monospaced", Font.PLAIN, 14));
			vacio.setAlignmentX(Component.CENTER_ALIGNMENT);
			listaPanel.add(vacio);
		} else {
			for (int i = 0; i < sobresComprados.size(); i++) {
				Sobre sobre = sobresComprados.get(i);
				int index = i;

				JPanel fila = new JPanel(new BorderLayout());
				fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
				fila.setBackground(Color.BLACK);
				fila.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

				JLabel nombre = new JLabel(sobre.getNombre());
				nombre.setForeground(Color.WHITE);
				nombre.setFont(new Font("Monospaced", Font.BOLD, 14));

				JButton abrirBtn = crearBotonEstilizado("ABRIR", e -> {
					Carta carta = DatosCartas
							.obtenerCartaAleatoriaPorCalidad(obtenerCalidadAleatoria(sobre.getProbabilidades()));

					if (carta == null) {
						JOptionPane.showMessageDialog(this, "No hay cartas disponibles para esa calidad.");
						return;
					}

					mainWindow.getCartaGuardada().añadirCarta(carta);
					mainWindow.getCartaGuardada().guardarEnArchivo();
					mostrarCartaObtenida(carta, mainWindow);
					sobresComprados.remove(index);
					actualizarLista(listaPanel, sobresComprados, mainWindow);
					listaPanel.revalidate();
					listaPanel.repaint();
				});
				abrirBtn.setPreferredSize(new Dimension(100, 30));

				JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				derecha.setBackground(Color.BLACK);
				derecha.add(abrirBtn);

				fila.add(nombre, BorderLayout.WEST);
				fila.add(derecha, BorderLayout.EAST);
				fila.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(50, 50, 50)));

				listaPanel.add(fila);
			}
		}
	}

	private String obtenerCalidadAleatoria(Map<String, Double> probabilidades) {
		double r = new Random().nextDouble() * 100;
		double suma = 0;
		for (Map.Entry<String, Double> entry : probabilidades.entrySet()) {
			suma += entry.getValue();
			if (r <= suma) {
				return entry.getKey();
			}
		}
		return "COMÚN";
	}

	private void mostrarCartaObtenida(Carta carta, CARDS2 mainWindow) {
		JDialog ventanaCarta = new JDialog(mainWindow, "Carta Obtenida", true);
		ventanaCarta.setSize(400, 420);
		ventanaCarta.setLocationRelativeTo(this);
		ventanaCarta.setResizable(false);
		ventanaCarta.getContentPane().setBackground(Color.BLACK);
		ventanaCarta.setLayout(new BorderLayout());

		Color color = ColoresCalidad.obtener(carta.getCalidad());

		JTextArea ascii = new JTextArea(carta.getAscii());
		ascii.setFont(new Font("Courier New", Font.PLAIN, 8));
		ascii.setForeground(color);
		ascii.setBackground(Color.BLACK);
		ascii.setEditable(false);
		ascii.setFocusable(false);
		ascii.setMargin(new Insets(5, 10, 5, 10));
		ascii.setLineWrap(false);
		ascii.setWrapStyleWord(false);

		// ⬇️ Envolver en panel para centrar
		JPanel contenedor = new JPanel(new GridBagLayout());
		contenedor.setBackground(Color.BLACK);
		contenedor.add(ascii); // centrado por defecto en GridBagLayout

		JScrollPane scroll = new JScrollPane(contenedor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(null);
		scroll.getViewport().setBackground(Color.BLACK);

		JLabel info = new JLabel(carta.getResumenTitulo(), SwingConstants.CENTER);
		info.setFont(new Font("Monospaced", Font.BOLD, 16));
		info.setForeground(color);
		info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		ventanaCarta.add(scroll, BorderLayout.CENTER);
		ventanaCarta.add(info, BorderLayout.SOUTH);
		ventanaCarta.setVisible(true);
	}

	public static double calcularPrecioVenta(Carta carta) {
		Map<String, Integer> basePorCalidad = new HashMap<>();
		basePorCalidad.put("COMÚN", 10);
		basePorCalidad.put("POCO COMÚN", 25);
		basePorCalidad.put("ESPECIAL", 50);
		basePorCalidad.put("DINÁMICA", 100);
		basePorCalidad.put("FANÁTICO", 200);
		basePorCalidad.put("RADIANTE", 300);
		basePorCalidad.put("SOLAR", 500);
		basePorCalidad.put("LUNAR", 750);
		basePorCalidad.put("MÍTICA", 1000);
		basePorCalidad.put("ICONO", 1500);
		basePorCalidad.put("AURORA", 2000);
		basePorCalidad.put("FLASHBACK", 2500);
		basePorCalidad.put("NOMINADOS SOTS", 3000);
		basePorCalidad.put("ECLIPSE", 3500);
		basePorCalidad.put("SOTS", 4000);
		basePorCalidad.put("NOMINADOS SOTY", 5000);
		basePorCalidad.put("LEGADO SOTY", 6000);
		basePorCalidad.put("SOTY", 7000);
		basePorCalidad.put("SUPER FLASHBACK", 8000);
		basePorCalidad.put("ICONOS MOMENTS", 9000);
		basePorCalidad.put("ASCENDIDOS", 10000);
		basePorCalidad.put("OMEGA", 12000);
		basePorCalidad.put("CÓSMICA", 15000);

		int base = basePorCalidad.getOrDefault(carta.getCalidad().toUpperCase(), 10);
		return base + (carta.getMedia() / 100.0);
	}
}