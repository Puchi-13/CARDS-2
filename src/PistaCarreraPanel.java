import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.html.HTMLEditorKit;

public class PistaCarreraPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final int[] posiciones = new int[8];
	private final boolean[] terminado = new boolean[8];
	private final JLabel[] galgos = new JLabel[8];
	private final List<Integer> ordenLlegada = new ArrayList<>();
	private final Timer timer;
	private final Random random = new Random();
	private boolean resultadoMostrado = false;

	public PistaCarreraPanel(Color[] colores, int galgoApostado, int apuesta, double[] multiplicadores, CARDS2 main,
			Consumer<List<Integer>> callbackGuardarHistorial, JFrame ventanaCarrera) {
		setLayout(null);
		setPreferredSize(new Dimension(800, 340));
		setBackground(new Color(34, 139, 34)); // Verde césped

		int inicio = 740;
		for (int i = 0; i < 8; i++) {
			galgos[i] = new JLabel("🐕");
			galgos[i].setFont(new Font("SansSerif", Font.PLAIN, 24));
			galgos[i].setForeground(colores[i]);
			galgos[i].setBounds(inicio, 20 + i * 40, 40, 30);
			add(galgos[i]);
			posiciones[i] = inicio;
		}

		timer = new Timer(50, e -> {
			for (int i = 0; i < 8; i++) {
				if (terminado[i])
					continue;

				double mult = multiplicadores[i];
				double base = 2.5 / mult + 1.5;
				int avance = (random.nextDouble() < 0.85) ? (int) (base + random.nextInt(2)) : random.nextInt(2);
				posiciones[i] -= avance;
				galgos[i].setBounds(posiciones[i], 20 + i * 40, 40, 30);

				if (posiciones[i] <= 10) {
					terminado[i] = true;
					if (!ordenLlegada.contains(i))
						ordenLlegada.add(i);
				}
			}

			if (ordenLlegada.size() == 8 && !resultadoMostrado) {
				resultadoMostrado = true;
				callbackGuardarHistorial.accept(new ArrayList<>(ordenLlegada));
				ventanaCarrera.dispose();
				SwingUtilities.invokeLater(() -> mostrarResultado(galgoApostado, apuesta, multiplicadores, main));
			}
		});

		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Fondo de césped ya definido con setBackground

		// Líneas divisorias entre carriles
		g.setColor(new Color(200, 200, 200)); // gris claro
		for (int i = 1; i < 8; i++) {
			int y = 20 + i * 40 - 5;
			g.fillRect(0, y, getWidth(), 2);
		}

		// Meta (borde izquierdo)
		g.setColor(Color.WHITE);
		g.fillRect(5, 0, 4, getHeight());

		// Línea de salida (borde derecho)
		g.setColor(Color.RED);
		g.fillRect(getWidth() - 10, 0, 4, getHeight());
	}

	private void mostrarResultado(int galgoApostado, int apuesta, double[] multiplicadores, CARDS2 main) {
		int posicionFinal = ordenLlegada.indexOf(galgoApostado);
		double baseMult = multiplicadores[galgoApostado];
		double mult;

		if (posicionFinal == 0) {
			mult = baseMult;
		} else if (posicionFinal == 1) {
			mult = Math.max(1.3, baseMult * 0.65);
		} else if (posicionFinal == 2) {
			mult = Math.max(1.2, baseMult * 0.45);
		} else if (posicionFinal <= 4) {
			mult = Math.max(1.0, baseMult * 0.2);
		} else {
			mult = 0;
		}

		int ganancia = (int) Math.round(apuesta * mult);

		if (ganancia > 0) {
			main.sumarDinero(ganancia);
		}

		JDialog dialog = new JDialog((JFrame) null, "Resultados", true);
		dialog.setSize(500, 450);
		dialog.setLayout(new BorderLayout());
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent e) {
				main.volverAlMenu();
			}
		});

		JTextPane area = new JTextPane();
		area.setContentType("text/html");
		area.setEditorKit(new HTMLEditorKit());
		area.setBackground(Color.BLACK);
		area.setEditable(false);

		StringBuilder html = new StringBuilder();
		html.append(
				"<html><body style='background-color:#111111; color:#eeeeee; font-family:monospace; font-size:14px;'>");
		html.append("<div style='padding:15px; max-width:360px;'>");
		html.append("<h2 style='color:#ffd700;'>🏁 RESULTADOS DE LA CARRERA</h2><ul>");
		for (int i = 0; i < ordenLlegada.size(); i++) {
			int id = ordenLlegada.get(i);
			html.append("<li>").append(i + 1).append("º: Galgo ").append(id + 1)
					.append(id == galgoApostado ? " ⬅️" : "").append("</li>");
		}
		html.append("</ul><hr>");
		if (mult > 0) {
			html.append("<p style='color:#90ee90;'>✔ ¡Tu galgo quedó ").append(posicionFinal + 1)
					.append("º!<br>Multiplicador aplicado: x").append(String.format("%.2f", mult))
					.append("<br>Ganancia total: <b>").append(ganancia).append("€</b>.</p>");
		} else {
			html.append("<p style='color:#ff5555;'>✘ Tu galgo no ganó. Perdiste <b>").append(apuesta)
					.append("€</b>.</p>");
		}
		html.append("</div></body></html>");

		area.setText(html.toString());
		area.setMargin(new Insets(10, 10, 10, 10));
		area.setCaretPosition(0);

		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setPreferredSize(new Dimension(380, 200));
		scrollPane.setBorder(null);
		dialog.add(scrollPane, BorderLayout.CENTER);

		JButton cerrar = new JButton("Volver al Menú");
		cerrar.addActionListener(e -> {
			dialog.dispose();
			main.volverAlMenu();
		});
		JPanel footer = new JPanel();
		footer.setBackground(Color.BLACK);
		footer.add(cerrar);
		dialog.add(footer, BorderLayout.SOUTH);

		dialog.setVisible(true);
	}
}
