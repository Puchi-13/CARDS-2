import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TragaperrasVentana extends JDialog {
	private static final long serialVersionUID = 1L;
	private final CARDS2 main;
	private final JLabel[] slots = new JLabel[3];
	private final String[] simbolos = { "🍒", "🍉", "🍋", "🍇", "🍊", "💎", "🔷" };
	private final Random random = new Random();
	private final int COSTE = 100;
	private JButton girarBtn;

	public TragaperrasVentana(CARDS2 mainWindow) {
		super(mainWindow, "Tragaperras", true);
		this.main = mainWindow;

		setSize(440, 340);
		setLocationRelativeTo(mainWindow);
		getContentPane().setBackground(Color.BLACK);
		setLayout(new BorderLayout());

		JLabel titulo = new JLabel("🎰 TRAGAPERRAS 🎰", SwingConstants.CENTER);
		titulo.setFont(new Font("Monospaced", Font.BOLD, 22));
		titulo.setForeground(Color.YELLOW);
		titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		add(titulo, BorderLayout.NORTH);

		JPanel centro = new JPanel(null) {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(60, 60, 60));
				g2.setStroke(new BasicStroke(1));

				int offsetX = 20;
				int cuadroAncho = 125;
				int cuadroAlto = getHeight() - 77;

				// FONDOS BLANCOS
				g2.setColor(Color.WHITE);
				for (int i = 0; i < 3; i++) {
					g2.fillRect(offsetX + i * cuadroAncho, 10, cuadroAncho, cuadroAlto);
				}

				// BORDES Y LÍNEAS
				g2.setColor(new Color(60, 60, 60));
				g2.drawRect(offsetX, 10, cuadroAncho * 3, cuadroAlto);
				g2.drawLine(offsetX + cuadroAncho, 10, offsetX + cuadroAncho, 10 + cuadroAlto);
				g2.drawLine(offsetX + 2 * cuadroAncho, 10, offsetX + 2 * cuadroAncho, 10 + cuadroAlto);
			}
		};
		centro.setBackground(Color.BLACK);
		centro.setPreferredSize(new Dimension(400, 140));

		int offsetX = 20;
		int cuadroAncho = 125;
		int simboloAncho = 85;
		int simboloAlto = 60;

		for (int i = 0; i < 3; i++) {
			slots[i] = new JLabel("❔", SwingConstants.CENTER);
			slots[i].setFont(new Font("SansSerif", Font.BOLD, 48));
			slots[i].setForeground(Color.BLACK); // negro sobre blanco
			slots[i].setOpaque(false);

			int cuadroX = offsetX + i * cuadroAncho;
			int simboloX = cuadroX + (cuadroAncho - simboloAncho) / 2;
			int simboloY = 40;

			slots[i].setBounds(simboloX, simboloY, simboloAncho, simboloAlto);
			centro.add(slots[i]);
		}

		add(centro, BorderLayout.CENTER);

		girarBtn = new JButton("GIRAR (-" + COSTE + "€)");
		girarBtn.setFont(new Font("Monospaced", Font.BOLD, 14));
		girarBtn.setBackground(Color.DARK_GRAY);
		girarBtn.setForeground(Color.WHITE);
		girarBtn.setFocusPainted(false);
		girarBtn.setOpaque(true);
		girarBtn.setContentAreaFilled(true);
		girarBtn.addActionListener(e -> girar());

		JPanel sur = new JPanel();
		sur.setBackground(Color.BLACK);
		sur.add(girarBtn);
		add(sur, BorderLayout.SOUTH);

		setVisible(true);
	}

	private void girar() {
		if (main.getDinero() < COSTE) {
			JOptionPane.showMessageDialog(this, "No tienes suficiente dinero.");
			return;
		}

		main.sumarDinero(-COSTE);
		girarBtn.setEnabled(false);

		int[] tiempos = { 1000, 2000, 3000 };
		Timer[] timers = new Timer[3];
		String[] resultados = new String[3];

		for (int i = 0; i < 3; i++) {
			final int index = i;
			timers[i] = new Timer(75, e -> {
				String simbolo = simbolos[random.nextInt(simbolos.length)];
				slots[index].setText(simbolo);
				slots[index].setForeground(colorParaSimbolo(simbolo));
			});
			timers[i].start();

			Timer stopTimer = new Timer(tiempos[i], e -> {
				timers[index].stop();
				String finalSimbolo = simbolos[random.nextInt(simbolos.length)];
				slots[index].setText(finalSimbolo);
				slots[index].setForeground(colorParaSimbolo(finalSimbolo));
				resultados[index] = finalSimbolo;

				if (index == 2) {
					Timer eval = new Timer(100, ev -> {
						evaluarResultado(resultados);
						girarBtn.setEnabled(true);
					});
					eval.setRepeats(false);
					eval.start();
				}
			});
			stopTimer.setRepeats(false);
			stopTimer.start();
		}
	}

	private Color colorParaSimbolo(String simbolo) {
		return switch (simbolo) {
		case "🍒" -> Color.RED;
		case "🍉" -> new Color(0, 200, 0);
		case "🍋" -> Color.YELLOW;
		case "🍇" -> new Color(160, 32, 240);
		case "🍊" -> new Color(255, 140, 0);
		case "💎" -> new Color(135, 206, 250);
		case "🔷" -> new Color(0, 0, 139);
		default -> Color.WHITE;
		};
	}

	private void evaluarResultado(String[] res) {
		String a = res[0];
		String b = res[1];
		String c = res[2];

		int premio = calcularPremio(a, b, c);

		if (premio > 0) {
			main.sumarDinero(premio);
			JOptionPane.showMessageDialog(this, "¡Has ganado " + premio + "€!");
		} else {
			JOptionPane.showMessageDialog(this, "Nada... suerte la próxima.");
		}
	}

	private int calcularPremio(String a, String b, String c) {
		// 3 iguales
		if (a.equals(b) && b.equals(c))
			return premioPorSimbolo(a, true);
		// 2 iguales
		if (a.equals(b) || b.equals(c) || a.equals(c))
			return premioPorSimbolo(masRepetido(a, b, c), false);
		return 0;
	}

	private int premioPorSimbolo(String s, boolean triple) {
		return switch (s) {
		case "🍒" -> triple ? 300 : 100;
		case "🍉" -> triple ? 400 : 150;
		case "🍋" -> triple ? 500 : 200;
		case "🍇" -> triple ? 600 : 250;
		case "🍊" -> triple ? 700 : 300;
		case "💎" -> triple ? 900 : 400;
		case "🔷" -> triple ? 1200 : 600;
		default -> 0;
		};
	}

	private String masRepetido(String a, String b, String c) {
		if (a.equals(b))
			return a;
		if (b.equals(c))
			return b;
		return a;
	}
}
