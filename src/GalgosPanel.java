import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GalgosPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JButton[] historialBtns = new JButton[8];
	private final JRadioButton[] galgoBtns = new JRadioButton[8];
	private final int[][] historiales = new int[8][8];
	private final double[] multiplicadores = new double[8];
	private final Color[] coloresGalgo = { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN,
			Color.PINK, Color.YELLOW };
	private final ButtonGroup grupoSeleccion = new ButtonGroup();
	private final JTextField campoApuesta = new JTextField();
	private final JLabel labelMultiplicador = new JLabel("MULT: -");
	private final JButton btnCorrer = new JButton("¡QUE EMPIECE LA CARRERA!");
	private final CARDS2 main;
	private final JButton volverBtn = new JButton("Volver al menú principal");

	public GalgosPanel(CARDS2 main) {
		this.main = main;
		inicializarHistorial();
		recalcularMultiplicadores();

		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel titulo = new JLabel("     CARRERAS DE GALGOS", SwingConstants.CENTER);
		titulo.setFont(new Font("Monospaced", Font.BOLD, 24));
		titulo.setForeground(Color.YELLOW);
		titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

		JPanel centro = new JPanel(new GridLayout(8, 1, 10, 10));
		centro.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		centro.setBackground(Color.BLACK);

		JButton ayudaBtn = new JButton("❓");
		ayudaBtn.setFont(new Font("Monospaced", Font.BOLD, 16));
		ayudaBtn.setPreferredSize(new Dimension(40, 30));
		ayudaBtn.setBackground(Color.DARK_GRAY);
		ayudaBtn.setForeground(Color.WHITE);
		ayudaBtn.setFocusable(false);
		ayudaBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		ayudaBtn.setToolTipText("Ayuda sobre cómo funciona este panel");
		ayudaBtn.addActionListener(e -> mostrarAyuda());

		JPanel panelTitulo = new JPanel(new BorderLayout());
		panelTitulo.setBackground(Color.BLACK);
		panelTitulo.add(titulo, BorderLayout.CENTER);

		JPanel ayudaContainer = new JPanel(new BorderLayout());
		ayudaContainer.setBackground(Color.BLACK);
		ayudaContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20)); // margen derecha
		ayudaContainer.add(ayudaBtn, BorderLayout.EAST);

		panelTitulo.add(ayudaContainer, BorderLayout.EAST);
		add(panelTitulo, BorderLayout.NORTH);

		for (int i = 0; i < 8; i++) {
			JPanel fila = new JPanel(new BorderLayout());
			fila.setBackground(Color.DARK_GRAY);
			fila.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			galgoBtns[i] = new JRadioButton(" Galgo " + (i + 1));
			galgoBtns[i].setForeground(coloresGalgo[i]);
			galgoBtns[i].setFont(new Font("Monospaced", Font.BOLD, 16));
			galgoBtns[i].setBackground(Color.DARK_GRAY);
			grupoSeleccion.add(galgoBtns[i]);
			int finalI = i;
			galgoBtns[i].addActionListener(e -> actualizarMultiplicador(finalI));

			historialBtns[i] = new JButton("Carreras del 1 al 8");
			historialBtns[i].setFont(new Font("SansSerif", Font.PLAIN, 12));
			historialBtns[i].addActionListener(e -> mostrarHistorial(finalI));

			fila.add(galgoBtns[i], BorderLayout.WEST);
			fila.add(historialBtns[i], BorderLayout.EAST);
			centro.add(fila);
		}

		add(centro, BorderLayout.CENTER);

		JPanel sur = new JPanel(new GridLayout(4, 1, 5, 5));
		sur.setBackground(Color.BLACK);
		sur.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 100));

		campoApuesta.setFont(new Font("Monospaced", Font.PLAIN, 16));
		campoApuesta.setHorizontalAlignment(SwingConstants.CENTER);
		campoApuesta.setForeground(Color.GRAY);
		campoApuesta.setText("Introduce tu apuesta €");

		campoApuesta.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (campoApuesta.getText().equals("Introduce tu apuesta €")) {
					campoApuesta.setText("");
					campoApuesta.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (campoApuesta.getText().isEmpty()) {
					campoApuesta.setForeground(Color.GRAY);
					campoApuesta.setText("Introduce tu apuesta €");
				}
			}
		});

		campoApuesta.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				validarActivarBoton();
			}

			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				validarActivarBoton();
			}

			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				validarActivarBoton();
			}
		});

		labelMultiplicador.setForeground(Color.WHITE);
		labelMultiplicador.setHorizontalAlignment(SwingConstants.CENTER);
		labelMultiplicador.setFont(new Font("Monospaced", Font.BOLD, 16));

		btnCorrer.setEnabled(false);
		btnCorrer.setBackground(Color.DARK_GRAY);
		btnCorrer.setForeground(Color.WHITE);
		btnCorrer.setFont(new Font("Monospaced", Font.BOLD, 16));
		btnCorrer.addActionListener(e -> iniciarCarrera());

		volverBtn.setBackground(Color.GRAY);
		volverBtn.setForeground(Color.BLACK);
		volverBtn.setFont(new Font("Monospaced", Font.PLAIN, 14));
		volverBtn.addActionListener(e -> main.volverAlMenu());

		sur.add(campoApuesta);
		sur.add(labelMultiplicador);
		sur.add(btnCorrer);
		sur.add(volverBtn);

		add(sur, BorderLayout.SOUTH);
	}

	private void iniciarCarrera() {
		int galgoSeleccionado = -1;
		for (int i = 0; i < galgoBtns.length; i++) {
			if (galgoBtns[i].isSelected()) {
				galgoSeleccionado = i;
				break;
			}
		}
		if (galgoSeleccionado == -1)
			return;

		int apuesta;
		try {
			apuesta = Integer.parseInt(campoApuesta.getText().trim());
		} catch (NumberFormatException e) {
			return;
		}

		if (apuesta > 0 && main.getDinero() >= apuesta) {
			main.sumarDinero(-apuesta);
			JFrame ventana = new JFrame("Carrera en Progreso");
			PistaCarreraPanel pista = new PistaCarreraPanel(coloresGalgo, galgoSeleccionado, apuesta, multiplicadores,
					main, this::guardarResultadoCarrera, ventana); // Pasamos el frame
			ventana.add(pista);
			ventana.pack();
			ventana.setLocationRelativeTo(this);
			ventana.setVisible(true);
		}
	}

	private void guardarResultadoCarrera(List<Integer> ordenLlegada) {
		for (int galgo = 0; galgo < 8; galgo++) {
			int posicion = ordenLlegada.indexOf(galgo) + 1;
			System.arraycopy(historiales[galgo], 1, historiales[galgo], 0, 7);
			historiales[galgo][7] = posicion;
		}
		recalcularMultiplicadores();
	}

	private void mostrarHistorial(int galgoIndex) {
		new HistorialCarrerasDialog((JFrame) SwingUtilities.getWindowAncestor(this), galgoIndex,
				historiales[galgoIndex]);
	}

	private void actualizarMultiplicador(int index) {
		labelMultiplicador.setText("MULT: x" + multiplicadores[index]);
		validarActivarBoton();
	}

	private void validarActivarBoton() {
		boolean galgoSeleccionado = false;
		for (JRadioButton b : galgoBtns)
			if (b.isSelected())
				galgoSeleccionado = true;
		boolean cantidadValida = false;
		try {
			int cantidad = Integer.parseInt(campoApuesta.getText().trim());
			cantidadValida = cantidad > 0;
		} catch (NumberFormatException ignored) {
		}
		btnCorrer.setEnabled(galgoSeleccionado && cantidadValida);
	}

	private void inicializarHistorial() {

		for (int carrera = 0; carrera < 8; carrera++) {
			List<Integer> posiciones = new ArrayList<>();
			for (int i = 1; i <= 8; i++)
				posiciones.add(i);
			Collections.shuffle(posiciones);
			for (int galgo = 0; galgo < 8; galgo++) {
				historiales[galgo][carrera] = posiciones.get(galgo);
			}
		}
	}

	private void recalcularMultiplicadores() {
		for (int i = 0; i < 8; i++) {
			double mult = 1.5;
			for (int j = 0; j < 8; j++) {
				int pos = historiales[i][j];
				double peso = 1.0 + (j * 0.1);
				if (pos == 1)
					mult += 0.6 * peso;
				else if (pos == 2)
					mult += 0.45 * peso;
				else if (pos == 3)
					mult += 0.3 * peso;
				else if (pos == 4 || pos == 5)
					mult += 0.15 * peso;
				else if (pos == 6)
					mult += 0.05 * peso;
				else if (pos == 7)
					mult += 0.02 * peso;
			}
			if (mult < 1.5)
				mult = 1.5;
			if (historiales[i][7] == 1 && mult < 2.0)
				mult = 2.0;
			multiplicadores[i] = Math.round(mult * 100.0) / 100.0;
		}
	}

	private void mostrarAyuda() {
		JDialog ayudaDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Ayuda - Carreras de Galgos",
				true);
		ayudaDialog.setSize(500, 350);
		ayudaDialog.setLocationRelativeTo(this);
		ayudaDialog.setLayout(new BorderLayout());
		ayudaDialog.getContentPane().setBackground(Color.BLACK);

		JTextPane texto = new JTextPane();
		texto.setEditable(false);
		texto.setBackground(Color.BLACK);
		texto.setForeground(Color.WHITE);
		texto.setFont(new Font("Monospaced", Font.PLAIN, 14));
		texto.setMargin(new Insets(15, 20, 15, 20));
		texto.setText("""
				📘 Instrucciones para las Carreras de Galgos

				1. Selecciona uno de los 8 galgos.
				2. Introduce la cantidad de dinero a apostar.
				3. Observa el multiplicador (MULT) que depende de las últimas 8 carreras.
				4. Presiona "¡QUE EMPIECE LA CARRERA!" para comenzar.
				5. Si tu galgo queda bien posicionado, ganas una cantidad multiplicada.

				📊 Sobre el MULT:
				- Calculado según las 8 últimas posiciones.
				- Galgos con buenos resultados recientes tienen MULT más alto.
				- Galgos con malos resultados recientes tienen MULT más bajo.

				📂 Puedes ver el historial de cada galgo haciendo clic en "Carreras del 1 al 8".

				🐾
				""");

		JScrollPane scroll = new JScrollPane(texto);
		scroll.setBorder(null);
		scroll.getViewport().setBackground(Color.BLACK);
		ayudaDialog.add(scroll, BorderLayout.CENTER);

		JButton cerrar = new JButton("Cerrar");
		cerrar.setBackground(Color.DARK_GRAY);
		cerrar.setForeground(Color.WHITE);
		cerrar.setFont(new Font("Monospaced", Font.PLAIN, 13));
		cerrar.setFocusPainted(false);
		cerrar.addActionListener(e -> ayudaDialog.dispose());

		JPanel footer = new JPanel();
		footer.setBackground(Color.BLACK);
		footer.add(cerrar);
		ayudaDialog.add(footer, BorderLayout.SOUTH);

		ayudaDialog.setVisible(true);
	}

}
