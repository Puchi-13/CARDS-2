import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CasinoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final CARDS2 main;

	public CasinoPanel(CARDS2 mainWindow) {
		this.main = mainWindow;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel titulo = new JLabel("CASINO", SwingConstants.CENTER);
		titulo.setFont(new Font("Monospaced", Font.BOLD, 28));
		titulo.setForeground(Color.YELLOW);
		titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		add(titulo, BorderLayout.NORTH);

		JPanel juegos = new JPanel(new GridLayout(3, 2, 20, 20));
		juegos.setBackground(Color.BLACK);
		juegos.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

		juegos.add(crearBoton("🎰 Tragamonedas", () -> mostrarTragamonedas()));
		juegos.add(crearBoton("🐕 Galgos", () -> mostrarGalgos()));
		juegos.add(crearBoton("🎡 Ruleta", () -> juegoNoImplementado()));
		juegos.add(crearBoton("🃏 Blackjack", () -> juegoNoImplementado()));
		juegos.add(crearBoton("💰 Coinflip", () -> juegoNoImplementado()));
		juegos.add(crearBoton("💣 Bomba", () -> juegoNoImplementado()));

		add(juegos, BorderLayout.CENTER);

		JButton volver = new JButton("Volver al Menú Principal");
		volver.addActionListener(e -> mainWindow.volverAlMenu());
		JPanel sur = new JPanel();
		sur.setBackground(Color.BLACK);
		sur.add(volver);
		add(sur, BorderLayout.SOUTH);
	}

	private JButton crearBoton(String texto, Runnable accion) {
		JButton boton = new JButton(texto);
		boton.setFont(new Font("Monospaced", Font.BOLD, 16));
		boton.setBackground(Color.DARK_GRAY);
		boton.setForeground(Color.WHITE);
		boton.setFocusPainted(false);
		boton.setOpaque(true);
		boton.setContentAreaFilled(true);
		boton.addActionListener(e -> accion.run());
		return boton;
	}

	private void mostrarTragamonedas() {
		new TragaperrasVentana(main);
	}

	private void mostrarGalgos() {
		main.getContentPane().removeAll();
		main.getContentPane().add(new GalgosPanel(main));
		main.revalidate();
		main.repaint();
	}

	private void juegoNoImplementado() {
		JOptionPane.showMessageDialog(this, "Este juego aún no está implementado.");
	}
}